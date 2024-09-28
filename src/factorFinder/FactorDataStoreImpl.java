package com.example.factorfinder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FactorDataStoreImpl implements FactorDataStore {
  @Override
  public List<Integer> read(InputConfig inputConfig) {
    List<Integer> data = new ArrayList<>();

  
    String filePath = inputConfig.getFilePath(); // Add method in InputConfig

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        data.add(
            Integer.parseInt(line.trim())); // each should be an int
      }
    } catch (IOException e) {
      System.err.println("Error reading from file: " + e.getMessage());
    }

    return data;
  }

  @Override
  public WriteResult appendSingleResult(
      OutputConfig outputConfig, List<String> results) {
    String filePath = outputConfig.getFilePath(); // also added this in output config (input config has a similar method)

    try (BufferedWriter writer =
             new BufferedWriter(new FileWriter(filePath, true))) {
      for (String result : results) {
        writer.write(result);
        writer.newLine();
      }
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
      return WriteResult.FAILURE; // if writing fails will return failure
    }

    return WriteResult.SUCCESS;
  }
}
