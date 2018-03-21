package model;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class ShareInformationModel extends Observable{

    private DbConnector dbConnector;
    private ShareInformationTable shareInformationTable;

    public ShareInformationModel(){
        dbConnector = new DbConnector();
        shareInformationTable = new ShareInformationTable();
        loadTable();
    }

    public void loadTable(){

        ResultSet resultSet;
        int rowCount = 30;
        String[] columnNames = {"Share code", "Company", "Price", "Volume", "Company value"};
        Object[][] dataGrid = new Object[rowCount][columnNames.length];

        dbConnector.connect();

        String sql = "SELECT * FROM share_trader_local.SHARES  Order by COMPANY_NAME";
        resultSet = dbConnector.query(sql);

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


            shareInformationTable.setColumnNames(columnNames);
            shareInformationTable.setData(dataGrid);


            setChanged();
            notifyObservers(shareInformationTable);
        } catch(SQLException e){
            e.printStackTrace();
        }

        dbConnector.closeConnection();

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

    class ShareInformationTable extends AbstractTableModel {
        private String[] columnNames;
        private Object[][] data;


        public Object[][] getData() {
            return data;
        }

        public void setData(Object[][] data) {
            this.data = data;
        }

        public String[] getColumnNames() {
            return columnNames;
        }

        public void setColumnNames(String[] columnNames) {
            this.columnNames = columnNames;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

    }
}


