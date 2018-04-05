package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class ShareholdersModel extends Observable{

    DbConnector dbConnector;
    TableModel tableModel;

    public ShareholdersModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
    }


    public void loadShareholders(){


        ResultSet resultSet;
        int rowCount = 30;
        String[] columnNames = {"Name"};
        Object[][] dataGrid = new Object[rowCount][columnNames.length];


        resultSet = queryShareholdersFromDatabase();


        int row = 0;
        int col = 0;

        try {
            while (resultSet.next() && row <= rowCount) {
                dataGrid[row][col] = resultSet.getString("NAME");

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
            notifyObservers("Shareholders");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbConnector.closeConnection();
    }


    private ResultSet queryShareholdersFromDatabase() {

        dbConnector.connect();

        String sql = "SELECT * FROM SHAREHOLDERS";

        return dbConnector.query(sql);
    }

}
