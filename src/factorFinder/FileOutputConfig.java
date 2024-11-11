package com.example.factorfinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileOutputConfig implements OutputConfig {
  private String filePath;

  public FileOutputConfig(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void writeOutputData(List<String> data) {
    writeDataToFile(filePath, data);
  }

  @Override
  public String getFilePath() {
    return filePath;
  }

  private void writeDataToFile(String path, List<String> data) {
    try {
      // Write the data to the specified file
      Files.write(Paths.get(path), data);
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }
}
