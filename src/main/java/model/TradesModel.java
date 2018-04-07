package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class TradesModel extends Observable{

    DbConnector dbConnector;
    TableModel tableModel;

    public TradesModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
    }

    public void loadTrades(){


        ResultSet resultSet;
        int rowCount = 30;
        String[] columnNames = {"Share code", "Buyer", "Seller", "Volume", "Price", "Total Price", "Date and time", "Broker"};
        Object[][] dataGrid = new Object[rowCount][columnNames.length];


        resultSet = queryTradesFromDatabase();


        int row = 0;
        int col = 0;

        try{
            while (resultSet.next() && row <= rowCount){
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
                col++;

                dataGrid[row][col] = resultSet.getString("BROKER");

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
            notifyObservers("Trades");
        } catch(SQLException e){
            e.printStackTrace();
        }

        dbConnector.closeConnection();
    }


    private ResultSet queryTradesFromDatabase() {

        dbConnector.connect();


        String sql = "SELECT t.SHARE_CODE, s_buyer.NAME as BUYER, s_seller.NAME as SELLER, t.VOLUME, t.PRICE, t.DATE_TIME, b.NAME as BROKER FROM share_trader_local.TRADES t " +
                "LEFT JOIN BROKERS b on b.ID = t.BROKER_ID " +
                "LEFT JOIN SHAREHOLDERS s_buyer on s_buyer.ID = t.BUYER " +
                "LEFT JOIN SHAREHOLDERS s_seller on s_seller.ID = t.SELLER " +
                "ORDER BY DATE_TIME desc";
        return dbConnector.query(sql);
    }

}
