package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessingEngine {
    public String processJSON(String extractionResponse) {

        Pattern pattern = Pattern.compile("\"title\":\"(.*?)\".*?\"content\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(extractionResponse);
        String title = "", content = "";
        String processedInfo = "";

        while (matcher.find()) {
            title = matcher.group(1);
            content = matcher.group(2);
            processedInfo = processedInfo+ title + "---" + content + "\n";
        }

        return processedInfo;
    }

    public void createFiles(String processedInfo) {
        String[] newsArticles = processedInfo.split("\n");
        int fileCount = 1, itemCount = 0;
        for (String news : newsArticles) {
            try {
                if (itemCount % 5 == 0) {
                    String fileName = "file" + fileCount + ".txt";
                    File file = new File(fileName);
                    file.createNewFile();
                    fileCount++;
                }
                String fileName = "file" + (fileCount - 1) + ".txt";
                FileWriter writer = new FileWriter(fileName, true);
                writer.write(news + "\n");
                writer.close();

                itemCount++;

            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }
    }
}

