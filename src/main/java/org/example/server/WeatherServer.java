package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherServer {

        private static WeatherServer _instance = new WeatherServer();
        private String city;

        public String startServer(String[] args) throws IOException {

            StringBuilder result = new StringBuilder();
            URL url = new URL("http://api.openweathermap.org/data/2.5"+"/weather?q=Madrid&appid=9c5f6d38ad527fb351aa369d6ca3a073");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                for (String line; (line = reader.readLine()) != null;) {
                    result.append(line);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Ha ocurrido un error");
            }
            System.out.println(result.toString());
            return result.toString();
        }

        public static WeatherServer getInstance() {
            return _instance;
        }

        public String computeDefaultResponse(String JsonObject){
            String outputLine =
                    "HTTP/1.1 200 OK\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<!DOCTYPE html>"
                            + "<html>"
                            + "<head>"
                            + "<meta charset=\"UTF-8\">"
                            + "<title>Title of the document</title>\n"
                            + "</head>"
                            + "<body>"
                            + JsonObject
                            + "</body>"
                            + "</html>";
            return outputLine;
        }

        static int getPort() {
            if (System.getenv("PORT") != null) {
                return Integer.parseInt(System.getenv("PORT"));
            }
            return 35000;
        }
    }

