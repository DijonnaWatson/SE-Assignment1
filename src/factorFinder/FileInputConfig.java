package factorfinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInputConfig implements InputConfig {
  private String filePath;

  public FileInputConfig(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public List<Integer> getInputData() {
    return readDataFromFile(filePath);
  }

  @Override
  public String getFilePath() {
    return filePath;
  }

  private List<Integer> readDataFromFile(String path) {
    List<Integer> data = new ArrayList<>();
    try {
      // Read all lines from the file
      List<String> lines = Files.readAllLines(Paths.get(path));
      // Convert each line to an integer and add to the list
      for (String line : lines) {
        try {
          data.add(Integer.parseInt(
              line.trim())); // Trim whitespace and parse integer
        } catch (NumberFormatException e) {
          System.err.println("Skipping all invalid integer: " + line);
        }
      }
    } catch (IOException e) {
      System.err.println("Unable to read from file " + e.getMessage());
    }
    return data;
  }
}
