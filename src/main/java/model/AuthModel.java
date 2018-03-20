package model;

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
        String sqlStatement = "select * from users " +
                       "where username = '" + username + "' " +
                       "and password = '" + password + "'";

        dbConnector.connect();
        resultSet = dbConnector.query(sqlStatement);

        try{

            // This returns true when data was returned from the db
            if(resultSet.isBeforeFirst()){
                System.out.println("true");
                notifyObservers(true);
            }else{
                System.out.println("false");
                notifyObservers(false);
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
            notifyObservers(true);
        }else{
            System.out.println("Username not available");
            notifyObservers(false);
        }
        // TODO do database things
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
