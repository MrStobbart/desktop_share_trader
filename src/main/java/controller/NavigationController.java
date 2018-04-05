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

        if(event.getActionCommand() == NavigationActions.TRADES.name()){

            setChanged();
            notifyObservers(MainActions.SHOW_SHARE_TRADES);
        }

        if(event.getActionCommand() == NavigationActions.BROKERS.name()){

            setChanged();
            notifyObservers(MainActions.SHOW_BROKERS);
        }

        if(event.getActionCommand() == NavigationActions.SHAREHOLDERS.name()){
            setChanged();
            notifyObservers(MainActions.SHOW_SHAREHOLDERS);
        }

        if(event.getActionCommand() == NavigationActions.EXIT.name()){
            setChanged();
            notifyObservers(MainActions.EXIT_APPLICATION);
        }
    }

    public void setView(NavigationView navigationView){
        this.navigationView = navigationView;
    }
}
