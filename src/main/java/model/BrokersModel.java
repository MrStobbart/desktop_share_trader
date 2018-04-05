package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class BrokersModel extends Observable{

    private DbConnector dbConnector;
    private TableModel tableModel;
    private Object[][] dataGrid;

    public BrokersModel(){
        dbConnector = new DbConnector();
        tableModel = new TableModel();
    }

    public void loadTable(){


        ResultSet resultSet;
        int rowCount = 30;
        String[] columnNames = {"Id", "Name", "Service Quality Grade", "Specialist Domain", "Mail", "Phone"};
        dataGrid = new Object[rowCount][columnNames.length];

        resultSet = queryBrokersFromDatabase();

        int row = 0;
        int col = 0;

        try{
            while (resultSet.next() && row <= rowCount){
                dataGrid[row][col] = resultSet.getString("ID");
                col++;

                dataGrid[row][col] = resultSet.getString("NAME");
                col++;

                dataGrid[row][col] = resultSet.getString("SERVICE_QUALITY_GRADE");
                col++;

                dataGrid[row][col] = resultSet.getString("SPECIALIST_DOMAIN");
                col++;

                dataGrid[row][col] = resultSet.getString("MAIL");
                col++;

                dataGrid[row][col] = resultSet.getString("PHONE");

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

    private ResultSet queryBrokersFromDatabase(){
        dbConnector.connect();

        String sql = "SELECT * FROM BROKERS " +
                "ORDER BY SERVICE_QUALITY_GRADE desc";
        return dbConnector.query(sql);

    }

}
