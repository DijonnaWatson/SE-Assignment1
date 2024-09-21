package com.example.factorfinder;

public interface WriteResult {
  WriteResultStatus getStatus();

  public static enum WriteResultStatus {
    SUCCESS,
    FAILURE;
  }
}
