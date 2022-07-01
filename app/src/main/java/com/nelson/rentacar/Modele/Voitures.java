package com.nelson.rentacar.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.nelson.rentacar.Data.Database;

import java.util.ArrayList;
import java.util.List;

public class Voitures {

    public Voitures(long id, String marque, String annee,  String modele,String numeroImma ,String transmission, String montant, String couleur) {
        this.id = id;
        this.marque = marque;
        this.annee = annee;
        this.modele=modele;
        this.numeroImma=numeroImma;
        this.transmission=transmission;
        this.montant=montant;
        this.couleur=couleur;
    }

    private long id;
    private String marque,annee,modele,numeroImma,transmission,montant,couleur;

    public Voitures() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNumeroImma() {
        return numeroImma;
    }

    public void setNumeroImma(String numeroImma) {
        this.numeroImma = numeroImma;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public  static String getScript() {
        String script = "CREATE TABLE Voitures(id INTEGER PRIMARY KEY AUTOINCREMENT,";
        script += "marque Text,";
        script += "annee Text,";
        script += "modele Text,";
        script += "numeroimma Text,";
        script += "transmission Text,";
        script += "montant Text,";
        script += "couleur Text);";
        return script;
    }
    //query preciser sur quelle table
    //raquerey pas besoins de preciser sur quelle table
    //cursor dictionnaire de donnee
    //Le curseur//Renvoie la valeur de la colonne demandée sous forme de tableau d'octets
    //=================Insertion==========================
    public static long Insert(Context context, Voitures obj){
        long l=0;
        Database db=new Database(context);
        db.open();
        ContentValues valeur=new ContentValues();
        valeur.put("marque",obj.getMarque());
        valeur.put("annee",obj.getAnnee());
        valeur.put("modele",obj.getModele());
        valeur.put("numeroimma",obj.getNumeroImma());
        valeur.put("transmission",obj.getTransmission());
        valeur.put("montant",obj.getMontant());
        valeur.put("couleur",obj.getCouleur());
        db.getBdd().insert("Voitures", null,valeur);
        Cursor cursor=db.getBdd().rawQuery("SELECT MAX(id) from Voitures",null);
        if (cursor.moveToNext()){
            l=cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return l;
    }
    //ContentValues est une classe  qui associe une valeur à une clé String.
    //=================Update==========================
    public static void update(Context context,Voitures obj){
        Database database=new Database(context);
        database.open();
        ContentValues values=new ContentValues();
        values.put("marque",obj.getMarque());
        values.put("annee",obj.getAnnee());
        values.put("modele",obj.getModele());
        values.put("numeroimma",obj.getNumeroImma());
        values.put("transmission",obj.getTransmission());
        values.put("montant",obj.getMontant());
        values.put("couleur",obj.getCouleur());
        database.getBdd().update("Voitures", values, "id=?",new String[]{String.valueOf(obj.getId())});
        database.close();
    }
    //=================Delete==========================
    public static void delete(Context context, long id){
        Database db= new Database(context);
        db.open();
        db.getBdd().delete("Voitures","id=?",new String []{String.valueOf(id)});
    }
    public static void deleteAll(Context context) {
        Database db= new Database(context);
        db.open();
        db.getBdd().delete("Voitures",null,null);
    }


    private static void moveTaskToBack(boolean b) {
    }

    public static List<Voitures> selectall(Context context){
        Database db=new Database(context);
        db.open();
        String query="SELECT id,marque, annee,modele,numeroimma,transmission,montant,couleur FROM Voitures";
        Cursor cursor=db.getBdd().rawQuery(query,null);
        List<Voitures> list=new ArrayList<>();
        while (cursor.moveToNext()){
            Voitures obj= new Voitures();
            obj.setId(cursor.getLong(0));
            obj.setMarque(cursor.getString(1));
            obj.setAnnee(cursor.getString(2));
            obj.setModele(cursor.getString(3));
            obj.setNumeroImma(cursor.getString(4));
            obj.setTransmission(cursor.getString(5));
            obj.setMontant(cursor.getString(6));
            obj.setCouleur(cursor.getString(7));
            list.add(obj);
        }
        cursor.close();
        db.close();
        return list;
    }

    public String toString() {

        return "Marque :" +this.marque+"\nAnnee : "+this.annee+"\nModele : ["+this.modele+"] \nNumero Immatriculation {"+this.numeroImma+"} \nTransmission :"+this.transmission+"\nMontant :"+this.montant+" \nCouleur: ("+this.couleur+")";
    }

}
