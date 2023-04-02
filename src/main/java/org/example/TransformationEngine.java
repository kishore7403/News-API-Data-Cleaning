package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;

public class TransformationEngine {

    public void replaceStringInFile(String filePath, String oldString, String newString) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", content = "";
            while ((line = reader.readLine()) != null) {
                content += line + "\n";
            }
            reader.close();
            content = content.replaceAll(oldString, newString);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String cleanFileContent(String news){
        String regex = "\\[[^\\]]*\\]";   // remove final characters
        String cleanedNews = news.replaceAll(regex, "");
        cleanedNews=cleanedNews.replaceAll("\\\\r\\\\n","");
        return cleanedNews;
    }

    public void pushToMongo() {
        {
            File directory = new File("C:\\Users\\AVuser\\Desktop\\newsAPI");
            File[] files = directory.listFiles();
            MongoClient client = MongoClients.create("mongodb+srv://kishore:1234@cluster0.q4gs6.mongodb.net/test");
            MongoDatabase db = client.getDatabase("myMongoNews");
            MongoCollection col = db.getCollection("news");


            for (File file : files) {
                if (file.isFile() && file.getName().contains(".txt")) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getName()));
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            String[] titleAndContent = line.split("---");
                            System.out.print(titleAndContent[0]+"------------------");
                            System.out.println(titleAndContent[1]);
                            Document sampleDoc = new Document("title",titleAndContent[0]).append("content",titleAndContent[1] );
                            col.insertOne(sampleDoc);
                        }
                    } catch (IOException e) {
                        System.err.format("IOException");
                    }
                }
            }
        }
    }
    public void openEachFiles() {
        {
            File directory = new File("C:\\Users\\AVuser\\Desktop\\newsAPI");
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile() && file.getName().contains(".txt")) {
                    String fileContent = "";
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getName()));
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            fileContent += line + System.lineSeparator();
                        }
                        bufferedReader.close();

                        // Cleaning
                        String cleanedContent=cleanFileContent(fileContent);
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getName()));
                        bufferedWriter.write(cleanedContent);
                        bufferedWriter.close();

                    } catch (IOException e) {
                        System.err.format("IOException");
                    }
                }
            }
        }

    }
}
