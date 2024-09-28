package com.example.factorfinder;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FactorDataStoreImpl implements FactorDataStore {
  @Override
  public List<Integer> read(InputConfig inputConfig) {
    List<Integer> data = new ArrayList<>();

    // Assuming InputConfig has a method to get file path
    String filePath = inputConfig.getFilePath(); // Add method in InputConfig

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        data.add(
            Integer.parseInt(line.trim())); // Assuming each line is an integer
      }
    } catch (IOException e) {
      System.err.println("Error reading from file: " + e.getMessage());
    }

    return data;
  }

  @Override
  public WriteResult appendSingleResult(
      OutputConfig outputConfig, List<String> results) {
    String filePath = outputConfig.getFilePath(); // Add method in OutputConfig

    try (BufferedWriter writer =
             new BufferedWriter(new FileWriter(filePath, true))) {
      for (String result : results) {
        writer.write(result);
        writer.newLine();
      }
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
      return WriteResult.FAILURE; // Return failure status if writing fails
    }

    return WriteResult.SUCCESS;
  }
}

