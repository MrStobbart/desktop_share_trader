package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class BrokerTradesModel extends Observable{

    DbConnector dbConnector;
    TableModel tableModel;

    public BrokerTradesModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
    }


    public void loadTrades(String brokerId){

        if(brokerId != null) {

            ResultSet resultSet;
            int rowCount = 30;
            String[] columnNames = {"Share", "Buyer", "Seller", "Volume", "Price", "Total Price", "Date and time"};
            Object[][] dataGrid = new Object[rowCount][columnNames.length];


            resultSet = queryBrokerTradesFromDatabase(brokerId);


            int row = 0;
            int col = 0;

            try {
                while (resultSet.next() && row <= rowCount) {
                    dataGrid[row][col] = resultSet.getString("SHARE_CODE");
                    col++;

                    dataGrid[row][col] = resultSet.getString("BUYER");
                    col++;

                    dataGrid[row][col] = resultSet.getString("SELLER");
                    col++;

                    int volume = resultSet.getInt("VOLUME");
                    dataGrid[row][col] = volume;
                    col++;

                    int price = resultSet.getInt("PRICE");
                    dataGrid[row][col] = "£" + String.format("%.2f", (double) price / 100);
                    col++;

                    dataGrid[row][col] = "£" + String.format("%.2f", (double) price * (double) volume / 100 / 1000) + "K";
                    col++;

                    dataGrid[row][col] = resultSet.getString("DATE_TIME");

                    col = 0;
                    row++;
                }


                tableModel.setColumnNames(columnNames);
                tableModel.setData(dataGrid);


                // Notify observers with tableModel
                setChanged();
                notifyObservers(tableModel);

                // Notify observers with broker name as title
                setChanged();
                notifyObservers(getBrokerNameFromId(brokerId));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            dbConnector.closeConnection();
        }
    }


    private ResultSet queryBrokerTradesFromDatabase(String brokerId) {

        dbConnector.connect();

        String sql = "SELECT SHARE_CODE, BUYER, SELLER, VOLUME, PRICE, DATE_TIME FROM share_trader_local.TRADES " +
                "WHERE BROKER_ID=" + brokerId;
        return dbConnector.query(sql);
    }

    private String getBrokerNameFromId(String brokerId){

        String name = brokerId;
        dbConnector.connect();

        String sql = "SELECT b.NAME FROM BROKERS b " +
                "WHERE ID=" + brokerId;

        ResultSet resultSet = dbConnector.query(sql);
        try {
            resultSet.next();
            name =  resultSet.getString("NAME");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }
}
