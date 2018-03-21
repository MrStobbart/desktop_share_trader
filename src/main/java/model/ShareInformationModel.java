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
        String[] columnNames = {"Trading code", "Company", "Price", "Volume", "Company value"};
        Object[][] dataGrid = new Object[rowCount][columnNames.length];

        dbConnector.connect();

        String sql = "SELECT * FROM share_trader_local.SHARES  Order by COMPANY_NAME";
        resultSet = dbConnector.query(sql);

        int row = 0;
        int col = 0;

        try{
            while (resultSet.next() && row <= rowCount){
                dataGrid[row][col] = resultSet.getString("TRADING_CODE");
                col++;

                dataGrid[row][col] = resultSet.getString("COMPANY_NAME");
                col++;

                int price = resultSet.getInt("PRICE");
                dataGrid[row][col] = Double.toString((double) price / (double)100) + "€";
                col++;

                int volume = resultSet.getInt("VOLUME");
                dataGrid[row][col] = volume;
                col++;

                dataGrid[row][col] = (double) price * (double) volume / (double) 100;

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


