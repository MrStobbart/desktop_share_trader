package model;

import java.util.Observable;

public class AuthModel extends Observable{

    public void login(String name, String password){

        notifyObservers(true);
    }
}
