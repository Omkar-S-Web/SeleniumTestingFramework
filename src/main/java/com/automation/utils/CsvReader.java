package com.automation.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    /**
     * Reads data from a CSV file and converts it into a 2D Object array
     * required by TestNG DataProviders.
     * @param filePath The path to the CSV file.
     * @return A 2D Object array containing the test data.
     */
   public static Object[][] getCsvData(String filePath) {
    List<String[]> data = new ArrayList<>();
    String line;
    String cvsSplitBy = ","; 

    // NOTE: Check if your CSV has a header row. If it does, you need to read 
    // and discard the first line (br.readLine();) before the while loop starts.

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        // Optional: If your CSV has a header row, uncomment the next line:
        // br.readLine(); 
        
        while ((line = br.readLine()) != null) {
            String[] row = line.split(cvsSplitBy);
            data.add(row);
        }
    } catch (IOException e) {
        // IMPORTANT: Print the stack trace so we can see if the file not found error occurs
        System.err.println("ERROR: Could not read CSV file at path: " + filePath);
        e.printStackTrace();
        return new Object[0][0]; 
    }

    // Convert List<String[]> to Object[][]
    Object[][] dataArray = new Object[data.size()][0];
    for (int i = 0; i < data.size(); i++) {
        dataArray[i] = data.get(i);
    }
    return dataArray;
}
}