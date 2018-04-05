package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class ShareholderTradesModel extends Observable{

    DbConnector dbConnector;
    TableModel tableModel;

    public ShareholderTradesModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
    }

    public void loadTrades(String shareholderId){

        if(shareholderId != null){

            ResultSet resultSet;
            int rowCount = 30;
            String[] columnNames = {"Share code", "Buyer", "Seller", "Broker", "Volume", "Price", "Total Price", "Date and time"};
            Object[][] dataGrid = new Object[rowCount][columnNames.length];


            resultSet = queryShareTradesFromDatabase(shareholderId);


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

                    dataGrid[row][col] = resultSet.getString("BROKER");
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

                // Notify observers with share code as title
                setChanged();
                notifyObservers("Shareholder: " + getShareholderNameFromId(shareholderId));
            } catch(SQLException e){
                e.printStackTrace();
            }

            dbConnector.closeConnection();
        }
    }


    private ResultSet queryShareTradesFromDatabase(String shareholderId) {

        dbConnector.connect();

        String sql = "SELECT s.NAME as BUYER, sh.NAME as SELLER, t.SHARE_CODE, t.VOLUME, t.PRICE, t.DATE_TIME, b.NAME as BROKER FROM TRADES t " +
                "LEFT JOIN SHAREHOLDERS s on s.ID = t.BUYER " +
                "LEFT JOIN SHAREHOLDERS sh on sh.ID = t.SELLER " +
                "LEFT JOIN BROKERS b on b.ID = t.BROKER_ID " +
                "WHERE t.BUYER=\"" + shareholderId + "\" or t.SELLER=\"" + shareholderId + "\"";
        return dbConnector.query(sql);
    }

    private String getShareholderNameFromId(String shareholderId){

        String name = shareholderId;
        dbConnector.connect();

        String sql = "SELECT s.NAME FROM SHAREHOLDERS s " +
                "WHERE ID=" + shareholderId;

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
