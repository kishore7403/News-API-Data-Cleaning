package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExtractionEngine {
    private static HttpURLConnection connection;
    public String extractData(String keyword) {
        BufferedReader reader;
        String jsonResponceAsString="";
        StringBuilder jsonResponce=new StringBuilder();
        try {
            String link=String.format("https://newsapi.org/v2/top-headlines?q=%s&apiKey=ebc0be5b95a24c1a81ba0be1044fa78e",keyword);
            URL url =new URL(link);
            connection=(HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status =connection.getResponseCode();

            if(status>299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((jsonResponceAsString=reader.readLine())!=null){
                    jsonResponce.append(jsonResponceAsString);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((jsonResponceAsString=reader.readLine())!=null){
                    jsonResponce.append(jsonResponceAsString);
                }
                reader.close();
            }


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            connection.disconnect();
        }

        jsonResponceAsString=jsonResponce.toString();

        return jsonResponceAsString;    // <-----------json responce as sting
    }

}