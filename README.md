# NewsAPI Data Extraction and Processing

This repository contains a set of Java programs that extract data from the NewsAPI, process the extracted data, and transform it before storing it in a MongoDB database.

## Prerequisites

- Java Development Kit (JDK)
- MongoDB
- [NewsAPI developer account](https://newsapi.org/)

## Program Flow

The program consists of three main components that are executed sequentially:

1. **Code A: Extraction Engine Implementation**
   - This program extracts news data from the NewsAPI based on specific search keywords.
   - The extracted data is then passed to the Data-Processing Engine.

2. **Code B: Data-Processing Engine Implementation**
   - This program receives the raw data captured by the Extraction Engine.
   - It writes the news contents and titles to separate files.
   - Each file contains up to 5 news articles.
   - The files are then passed to the Transformation Engine.

3. **Code C: Transformation Engine Implementation**
   - This program automatically cleans and transforms the data stored in the files.
   - The cleaned and transformed data is uploaded to a MongoDB database called "myMongoNews".
