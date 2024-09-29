package com.example.factorfinder;
import java.util.List;

public interface OutputConfig {
  void writeOutputData(List<String> data);
  String getFilePath(); // Method to get the file path for output data
}
