package com.nelson.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nelson.rentacar.Modele.Session;
import com.nelson.rentacar.Modele.Voitures;

public class Addvoitures extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText marque, annee,modele,numeroimma,transmission,montant;
    private ImageView image;
    private Spinner couleur;
    private Button btnvalider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvoitures);
        image=findViewById(R.id.image);
        marque=findViewById(R.id.editmarque);
        annee=findViewById(R.id.editannee);
        modele=findViewById(R.id.editmodele);
        numeroimma=findViewById(R.id.editnumeroimma);
        transmission=findViewById(R.id.edittransmission);
        montant=findViewById(R.id.editmontant);
        couleur=findViewById(R.id.spcouleur);
        btnvalider=findViewById(R.id.btnvalider);
        LoadValues();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        btnvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Voitures obj=new Voitures();
                if(Session.getCurrentVoitures()!=null){
                    obj= Session.getCurrentVoitures();
                }
                obj.setMarque(marque.getText().toString());
                obj.setAnnee(annee.getText().toString().toUpperCase());
                obj.setModele(modele.getText().toString());
                obj.setNumeroImma(numeroimma.getText().toString());
                obj.setTransmission(transmission.getText().toString());
                obj.setMontant(montant.getText().toString());
                obj.setCouleur(couleur.getSelectedItem().toString());

                if(obj.getId()>0){
                    // Session.getListPersonne().set((int) obj.getId()-1,obj);
                    Voitures.update(Addvoitures.this,obj);
                } else {

                    Long id=Voitures.Insert(Addvoitures.this,obj);
                    if (id>0) {
                        Toast.makeText(Addvoitures.this,"Insertion reussite",Toast.LENGTH_LONG);
                    }
                    //  Session.getListPersonne().add(obj);
                }


                startActivity(new Intent(Addvoitures.this,MainActivity.class));
            }
        });

    }
    //Methode pour Afficher
    void LoadValues(){
        Voitures obj=new Voitures();
        if (Session.getCurrentVoitures()!=null){
            obj= Session.getCurrentVoitures();
            marque.setText(obj.getMarque());
            annee.setText(obj.getAnnee());
            modele.setText(obj.getModele());
            numeroimma.setText(obj.getNumeroImma());
            transmission.setText(obj.getTransmission());
            montant.setText(obj.getMontant());
           // couleur.setText(obj.getCouleur());
        }

    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
        }
    }
}