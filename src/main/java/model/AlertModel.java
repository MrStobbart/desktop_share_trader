package model;

import objects.Alert;
import objects.Share;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AlertModel extends Observable{

    private boolean alertShown = false;
    private DbConnector dbConnector;

    public AlertModel(){
        dbConnector = new DbConnector();
    }

    public void checkShareAlert(int userId){

        List<Alert> alerts = getAllAlertsOfUser(userId);
        List<Share> shares = getAllShares(alerts);

        for (Alert alert : alerts) {

            Share share = findShare(shares, alert.getShareCode());

            if(share != null && !alertShown){

                double sharePrice = share.getPrice() / 100;
                if (share.getPrice() < alert.getMin()) {
                    alertShown = true;

                    String message = "The share-price of the company " + share.getCompanyName() + " exceeded your alert minimum of £" + alert.getMin() +
                            ".\nIt is now at £" + sharePrice + ".";
                    JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.PLAIN_MESSAGE);
                }

                if(share.getPrice() > alert.getMax()){
                    alertShown = true;
                    String message = "The share-price of the company " + share.getCompanyName() + " exceeded your alert maximum of £" + alert.getMin() +
                            ".\nIt is now at £" + sharePrice + ".";
                    JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.PLAIN_MESSAGE);
                }
            }

        }

    }

    private List<Alert> getAllAlertsOfUser(int userId){

        List<Alert> alerts = new ArrayList<>();
        ResultSet resultSet;
        String sql = "SELECT * FROM SHARE_ALERTS " +
                     "WHERE USER_ID=\""+ userId + "\"";

        dbConnector.connect();
        resultSet = dbConnector.query(sql);

        try{

            while(resultSet.next()){

                Alert alert = new Alert();
                alert.setUserId(userId);
                alert.setShareCode(resultSet.getString("SHARE_CODE"));
                alert.setMin(resultSet.getInt("MIN"));
                alert.setMax(resultSet.getInt("MAX"));

                alerts.add(alert);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }


        dbConnector.closeConnection();

        return alerts;

    }

    private List<Share> getAllShares(List<Alert> alerts){

        List<Share> shares = new ArrayList<>();
        ResultSet resultSet;
        StringBuilder sql = new StringBuilder("SELECT * FROM SHARES ");

        for (int i = 0; i < alerts.size(); i++) {

            String shareCode = alerts.get(i).getShareCode();
            String sqlWhere;

            if(i == 0){
                sqlWhere = "WHERE SHARE_CODE=\"" + shareCode + "\" ";
            } else{
                sqlWhere = "OR SHARE_CODE=\"" + shareCode + "\" ";

            }
            sql.append(sqlWhere);

        }

        System.out.println("This is the sql statement: " + sql);

        dbConnector.connect();
        resultSet = dbConnector.query(sql.toString());

        try{

            while(resultSet.next()){

                Share share = new Share();
                share.setShareCode(resultSet.getString("SHARE_CODE"));
                share.setCompanyName(resultSet.getString("COMPANY_NAME"));
                share.setPrice(resultSet.getInt("PRICE"));
                share.setVolume(resultSet.getInt("VOLUME"));

                shares.add(share);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        dbConnector.closeConnection();

        return shares;
    }

    private Share findShare(List<Share> shares, String shareCode){
        for(Share share: shares) {
            if(share.getShareCode().equals(shareCode)) {
                return share;
            }
        }
        return null;
    }

}
