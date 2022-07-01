package com.nelson.rentacar.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database {

    private DatabaseHandler databasehandler;
    private SQLiteDatabase Bdd;
    public  Database(Context context){
        databasehandler=new DatabaseHandler(context,"DB_Voitures",null,1);
    }
    //getWritableDatabase une classe d'assistance pour gerer la creation de bases de donneee et la gestion des versions
    public void open(){
        Bdd=databasehandler.getWritableDatabase();
    }
    public void close(){
        Bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return Bdd;
    }
}
