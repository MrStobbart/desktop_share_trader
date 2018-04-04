package controller;

import enums.BrokersActions;
import enums.MainActions;
import model.BrokersModel;
import viewCont.BrokersView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BrokersController extends Observable implements ActionListener {

    private BrokersModel brokersModel;
    private BrokersView brokersView;

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(BrokersActions.BACK.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_NAVIGATION);

        }else if(e.getActionCommand().equals(BrokersActions.RECOMMENDATIONS.name())){
            String recommendationField = brokersView.getRecommendationField();
            System.out.println("Recommendation field: " + recommendationField);
            brokersView.sortTable(recommendationField);

        }else if(e.getActionCommand().equals(BrokersActions.TRADE_RECORD.name())){
            setChanged();
            notifyObservers(MainActions.SHOW_BROKER_TRADES);
        }

    }

    public void setView(BrokersView brokersView){
        this.brokersView = brokersView;
    }

    public void setModel(BrokersModel brokersModel){
        this.brokersModel = brokersModel;
    }

    public void showView() {
        brokersModel.loadTable();
    }
}
