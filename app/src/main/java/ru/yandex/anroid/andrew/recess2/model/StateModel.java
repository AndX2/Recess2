package ru.yandex.anroid.andrew.recess2.model;

/**
 * Created by savos on 26.04.2016.
 */
public class StateModel {

    private int state;
    private static StateModel instance;

    private StateModel(){
    }

    public static synchronized StateModel getInstance(){
        if (instance == null) instance = new StateModel();
        return instance;
    }


}
