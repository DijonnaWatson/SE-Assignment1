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
  public List<Integer> read(FileInputConfig inputConfig) {
    List<Integer> data =
        new ArrayList<>(); // List to hold the integers read from the file
    // validation of the file path
    if (inputConfig.getFilePath() != null) {
      // Saving the file path from the input configuration into a variable
      String filePath = inputConfig.getFilePath();

      // try-catch block for reading every line in the file
      try (BufferedReader reader =
               new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
          data.add(Integer.parseInt(
              line.trim())); // Assuming each line is an integer
        }
      } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
      }

      return data;
    } else {
      System.out.println("The input filepath is Null!");
      return null;
    }
  }

  @Override
  public WriteResult appendSingleResult(
      FileOutputConfig outputConfig, List<String> results) {
    // validation of the file path
    if (outputConfig.getFilePath() != null | !results.isEmpty()) {
      String filePath =
          outputConfig.getFilePath(); //  Saving the file path from the output
                                      //  configuration

      // try-catch block for reading every line in the file
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
    } else {
      System.out.println("Error!! Either the output file path is null or the "
                         + "list of results is empty ");
      return WriteResult.FAILURE;
    }
  }
}
