package model;

import controller.MainController;
import enums.AuthResults;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class AuthModel extends Observable{

    private DbConnector dbConnector;

    public AuthModel(){
        dbConnector = new DbConnector();
    }

    public void login(String username, String password){

        System.out.println("Login with " + username + " and " + password);

        ResultSet resultSet;
        String sql = "select * from users " +
                     "where username = '" + username + "' " +
                     "and password = '" + password + "'";

        dbConnector.connect();
        resultSet = dbConnector.query(sql);

        try{

            // This returns true when data was returned from the db
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                int userId = resultSet.getInt("ID");
                MainController.setUserId(userId);

                setChanged();
                notifyObservers(AuthResults.SUCCESSFUL);
            }else{
                setChanged();
                notifyObservers(AuthResults.WRONG_CREDENTIALS);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        dbConnector.closeConnection();
    }

    public void signUp(String username, String password){

        System.out.println("SignUp with " + username + " and " + password);
        if(usernameAvailable(username)){

            String sqlStatement = "INSERT INTO users (username, password, permissions)" +
                                  "VALUES (\"" + username + "\", \"" + password + "\", 0)";
            dbConnector.connect();
            dbConnector.update(sqlStatement);
            System.out.println("User created");

            setChanged();
            notifyObservers(AuthResults.ACCOUNT_CREATED);
        }else{
            System.out.println("Username not available");

            setChanged();
            notifyObservers(AuthResults.USERNAME_NOT_FREE);
        }

    }

    private boolean usernameAvailable(String username){

        ResultSet resultSet;

        dbConnector.connect();
        resultSet = dbConnector.query("select * from users where username = '" + username + "'");

        try {
            if(!resultSet.isBeforeFirst()){
                // Username available
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
