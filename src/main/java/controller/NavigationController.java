package controller;

import enums.MainActions;
import enums.NavigationActions;
import viewCont.NavigationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class NavigationController extends Observable implements ActionListener {

    private NavigationView navigationView;


    @Override
    public void actionPerformed(ActionEvent event){

        if(event.getActionCommand() == NavigationActions.SHARE_INFORMATION.name()){

            setChanged();
            notifyObservers(MainActions.SHOW_SHARE_INFORMATION);
        }
    }

    public void addView(NavigationView navigationView){
        this.navigationView = navigationView;
    }
}
