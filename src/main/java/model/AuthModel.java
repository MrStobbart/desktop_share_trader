package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

public class AuthModel extends Observable{

    public void login(String username, String password){

        // TODO do database things
        System.out.println("Login with " + username + " and " + password);
        DbConnector dbConnector = new DbConnector();
        dbConnector.connect();
        ResultSet resultSet = dbConnector.query("select * from users where username = '" + username + "' and password = '" + password + "'");

        try{

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

        // TODO do database things
        System.out.println("SignUp with " + username + " and " + password);
        notifyObservers(true);
    }
}
