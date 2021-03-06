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

        if(event.getActionCommand().equals(NavigationActions.SHARE_INFORMATION.name())){

            setChanged();
            notifyObservers(MainActions.SHOW_SHARE_INFORMATION);
        }

        if(event.getActionCommand().equals(NavigationActions.TRADES.name())){

            setChanged();
            notifyObservers(MainActions.SHOW_TRADES);
        }

        if(event.getActionCommand().equals(NavigationActions.BROKERS.name())){

            setChanged();
            notifyObservers(MainActions.SHOW_BROKERS);
        }

        if(event.getActionCommand().equals(NavigationActions.SHAREHOLDERS.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_SHAREHOLDERS);
        }

        if(event.getActionCommand().equals(NavigationActions.EXIT.name())){
            setChanged();
            notifyObservers(MainActions.EXIT_APPLICATION);
        }
    }

    public void setView(NavigationView navigationView){
        this.navigationView = navigationView;
    }
}
