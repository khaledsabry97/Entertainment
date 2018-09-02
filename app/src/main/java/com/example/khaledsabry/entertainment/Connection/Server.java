package com.example.khaledsabry.entertainment.Connection;

/**
 * this class that contains every web link will be used in this app
 * to retrive it later every time the app runs from the server
 */
public class Server {
    private static Server instance = null;
    private String top250Imdb = "https://www.imdb.com/chart/top";



















    /*------------------------------------------------------------------------------------------------------------------*/

    public String getTop250Imdb() {
        return top250Imdb;
    }

    public void setTop250Imdb(String top250Imdb) {
        this.top250Imdb = top250Imdb;
    }

    private Server()
    {

    }


    public static Server getInstance()
    {
        if(instance == null)
            instance = new Server();
        return instance;
    }




}
