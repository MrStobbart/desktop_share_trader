package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class ShareTradesModel extends Observable{

    DbConnector dbConnector;
    TableModel tableModel;

    public ShareTradesModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
    }

    public void loadTrades(String shareCode){

        if(shareCode != null){

            ResultSet resultSet;
            int rowCount = 30;
            String[] columnNames = {"Buyer", "Seller", "Volume", "Price", "Total Price", "Date and time", "Broker"};
            Object[][] dataGrid = new Object[rowCount][columnNames.length];


            resultSet = queryShareTradesFromDatabase(shareCode);


            int row = 0;
            int col = 0;

            try{
                while (resultSet.next() && row <= rowCount){
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
                    col++;

                    dataGrid[row][col] = resultSet.getString("NAME");

                    col = 0;
                    row++;
                }


                tableModel.setColumnNames(columnNames);
                tableModel.setData(dataGrid);


                // Notify observers with tableModel
                setChanged();
                notifyObservers(tableModel);

                // Notify observers with share code as title
                setChanged();
                notifyObservers(shareCode);
            } catch(SQLException e){
                e.printStackTrace();
            }

            dbConnector.closeConnection();
        }
    }


    private ResultSet queryShareTradesFromDatabase(String shareCode) {

        dbConnector.connect();

        String sql = "SELECT t.BUYER, t.SELLER, t.VOLUME, t.PRICE, t.DATE_TIME, b.NAME FROM share_trader_local.TRADES t " +
                "LEFT JOIN BROKERS b on b.ID = t.BROKER_ID " +
                "WHERE SHARE_CODE=\"" + shareCode + "\"";
        return dbConnector.query(sql);
    }

}
