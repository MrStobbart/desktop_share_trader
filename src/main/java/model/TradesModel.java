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

    public void loadTable(String shareCode){

        if(shareCode != null){

            ResultSet resultSet;
            int rowCount = 30;
            String[] columnNames = {"Buyer", "Seller", "Volume", "Price", "Total Price", "Date and time"};
            Object[][] dataGrid = new Object[rowCount][columnNames.length];

            dbConnector.connect();

            String sql = "SELECT * FROM share_trader_local.TRADES " +
                         "WHERE SHARE_CODE=\"" + shareCode + "\"";
            resultSet = dbConnector.query(sql);

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

                    col = 0;
                    row++;
                }


                tableModel.setColumnNames(columnNames);
                tableModel.setData(dataGrid);


                setChanged();
                notifyObservers(tableModel);
            } catch(SQLException e){
                e.printStackTrace();
            }

            dbConnector.closeConnection();
        }
    }
}
