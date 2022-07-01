package com.nelson.rentacar.Modele;

import java.util.ArrayList;
import java.util.List;

public class Session {

    public static Voitures currentVoitures;
    public static List<Voitures> listVoitures= new ArrayList<>();

    public static List<Voitures> getListVoitures() {
        return listVoitures;
    }

    public static void setListVoitures(List<Voitures> listVoitures) {
        Session.listVoitures = listVoitures;
    }

    public static Voitures getCurrentVoitures() {
        return currentVoitures;
    }

    public static void setCurrentVoitures(Voitures currentVoitures) {
        Session.currentVoitures = currentVoitures;
    }

}
