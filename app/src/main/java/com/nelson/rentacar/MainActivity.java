package com.nelson.rentacar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.nelson.rentacar.Data.Database;
import com.nelson.rentacar.Modele.Session;
import com.nelson.rentacar.Modele.Voitures;

import java.util.ArrayList;
import java.util.List;

import static com.nelson.rentacar.Modele.Voitures.*;

public class MainActivity extends AppCompatActivity {

    private Button btnadd;
    private ListView lvvoitures;
    ArrayAdapter<Voitures> arrayAdapter;
    Context context=MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        Load();
        SetEvents();

    }
    //Methode pour initialiser les valeurs
    void Init(){
        lvvoitures=findViewById(R.id.lvvoitures);
    }
    //methode pour executer les actions utilisateur
    void SetEvents(){
        lvvoitures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Voitures obj=new Voitures();
                obj=arrayAdapter.getItem(i);
                Session.setCurrentVoitures(obj);
                startActivity(new Intent(MainActivity.this,Addvoitures.class));
                Toast.makeText(MainActivity.this,"Modification effectué",Toast.LENGTH_SHORT).show();
            }

        });

        lvvoitures.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos=i;
                Toast.makeText(context,"Action effectué",Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(context)
                        .setTitle("Confirmation")
                        .setMessage("Voulez-vous vraiment supprimer?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Voitures obj = new Voitures();
                                obj=arrayAdapter.getItem(pos);
                                delete(MainActivity.this,obj.getId());

                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                                finish();
                                //Load();
                            }
                        })

                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        //Permet d'empecher l'utilisateur de clicquer en dehors de dialogue
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }
    //Methode pour afficher les donnees
    void Load(){
        List<Voitures> Liste=new ArrayList<>();
        Liste= selectall(MainActivity.this);

        arrayAdapter = new ArrayAdapter<Voitures>(this,
                android.R.layout.simple_list_item_activated_1,
                Liste);
        lvvoitures.setAdapter(arrayAdapter);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addvoitures:
                Intent intent= new Intent(context,Addvoitures.class);
                startActivity(intent);
                finish();
                //Toast.makeText(context,"test sur le menu",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Viderliste:
                Voitures.deleteAll(context);
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
                return true;
            case R.id.aide:
                Intent intent1= new Intent(context,Aide.class);
                startActivity(intent1);
                finish();
                Toast.makeText(context,"test sur le menu",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.quit:
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}