package com.example.factorfinder;
import java.util.List;

public interface InputConfig {
  @Override
  List<Integer> getInputData();
  String getFilePath(); // Method to get the file path for input data
}
