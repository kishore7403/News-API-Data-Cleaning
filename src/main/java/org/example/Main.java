package org.example;

public class Main {
    public static void main(String[] args) {
        String jsonResponse=null;
        String[] keywords = {"Canada", "University", "Dalhousie",
                "Halifax", "Canada Education", "Moncton",
                "hockey", "Fredericton", "celebration"};

        //------------Code A--------------
        ExtractionEngine extractionInstance= new ExtractionEngine();
        for (String keyword: keywords) {

            if(jsonResponse==null){
                jsonResponse=extractionInstance.extractData(keyword)+"\n";
            }
            else {
                jsonResponse = jsonResponse + extractionInstance.extractData(keyword) + "\n";
            }
        }
//      System.out.println(jsonResponse);

        //-----------Code B-------------
        ProcessingEngine dataProcessing = new ProcessingEngine();
        String processedResponse= dataProcessing.processJSON(jsonResponse);
        //System.out.println(processedResponse);
        dataProcessing.createFiles(processedResponse);

        //-----------Code C-----------------
        TransformationEngine t= new TransformationEngine();
        t.openEachFiles();
        t.pushToMongo();
    }
    }
