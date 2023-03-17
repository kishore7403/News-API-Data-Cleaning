package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    private static HttpURLConnection connection;
    public static void main(String[] args) {

        BufferedReader reader;
        String line;
        StringBuilder responseContent=new StringBuilder();

        try {
            URL url =new URL("https://newsapi.org/v2/top-headlines?q=university&apiKey=ebc0be5b95a24c1a81ba0be1044fa78e");
            connection=(HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status =connection.getResponseCode();
            System.out.println(status);

            if(status>299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line=reader.readLine())!=null){
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line=reader.readLine())!=null){
                    responseContent.append(line);
                }
                reader.close();
            }

            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            connection.disconnect();
        }
    }
}