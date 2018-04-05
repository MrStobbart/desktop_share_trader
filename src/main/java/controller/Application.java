package controller;

public class Application{
    public static String user = "test";

    public static void main (String[] args){
        user = "toast";

        MainController mainController = new MainController();
    }
}
