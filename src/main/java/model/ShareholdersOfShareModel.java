package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class ShareholdersOfShareModel extends Observable{

    DbConnector dbConnector;
    TableModel tableModel;

    public ShareholdersOfShareModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
    }


    public void loadShareholders(String shareCode){

        if(shareCode != null) {

            ResultSet resultSet;
            int rowCount = 30;
            String[] columnNames = {"Shareholder", "Volume", "Total value"};
            Object[][] dataGrid = new Object[rowCount][columnNames.length];


            resultSet = queryShareholdersFromDatabase(shareCode);


            int row = 0;
            int col = 0;

            try {
                while (resultSet.next() && row <= rowCount) {
                    dataGrid[row][col] = resultSet.getString("NAME");
                    col++;

                    int volume = resultSet.getInt("VOLUME");
                    dataGrid[row][col] = volume;
                    col++;

                    int price = resultSet.getInt("PRICE");
                    dataGrid[row][col] = "£" + String.format("%.2f", (double) price * (double) volume / 100 / 1000) + "K";


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
                notifyObservers("Share: " + shareCode);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            dbConnector.closeConnection();
        }
    }


    private ResultSet queryShareholdersFromDatabase(String shareCode) {

        dbConnector.connect();

        String sql = "SELECT sh.NAME, x.VOLUME, s.PRICE FROM share_trader_local.SHARE_SHAREHOLDER_XREF x " +
                "LEFT JOIN SHARES s on s.SHARE_CODE = x.SHARE_CODE " +
                "LEFT JOIN SHAREHOLDERS sh on sh.ID = x.SHAREHOLDER_ID " +
                "WHERE x.SHARE_CODE=\""+ shareCode + "\" " +
                "ORDER BY x.VOLUME desc";

        return dbConnector.query(sql);
    }

}
