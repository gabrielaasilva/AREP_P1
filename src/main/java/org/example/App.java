package org.example;

import org.example.server.WeatherServer;

public class App
{
    public static void main( String[] args) {
        try {
            WeatherServer.getInstance().startServer(args);
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }


    }
}
