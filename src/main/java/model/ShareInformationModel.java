package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class ShareInformationModel extends Observable{

    private DbConnector dbConnector;
    private TableModel tableModel;

    public ShareInformationModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
        loadTable();
    }

    public void loadTable(){

        ResultSet resultSet;
        int rowCount = 30;
        String[] columnNames = {"Share code", "Company", "Price", "Volume", "Company value"};
        Object[][] dataGrid = new Object[rowCount][columnNames.length];

        resultSet = queryShareInformationFromDatabase();

        int row = 0;
        int col = 0;

        try{
            while (resultSet.next() && row <= rowCount){
                dataGrid[row][col] = resultSet.getString("SHARE_CODE");
                col++;

                dataGrid[row][col] = resultSet.getString("COMPANY_NAME");
                col++;

                int price = resultSet.getInt("PRICE");
                dataGrid[row][col] = "£" + String.format("%.2f", (double) price / 100);
                col++;

                int volume = resultSet.getInt("VOLUME");
                dataGrid[row][col] = volume;
                col++;

                dataGrid[row][col] = "£" + String.format("%.2f", (double) price * (double) volume / 100 / 1000000) + "M";

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

    private ResultSet queryShareInformationFromDatabase() {
        dbConnector.connect();

        String sql = "SELECT * FROM share_trader_local.SHARES  Order by COMPANY_NAME";
        return dbConnector.query(sql);
    }

    public void setShareAlert(String shareCode, int min, int max){

        if(!(shareCode == null)){

            // TODO somehow get the userid here
            String sql = "INSERT INTO SHARE_ALERTS (SHARE_CODE, USER_ID, MIN, MAX)" +
                         "VALUES (\"" + shareCode + "\", 1, " + min + ", " + max + ")";

            dbConnector.connect();
            dbConnector.update(sql);
            dbConnector.closeConnection();
        }
    }

}


