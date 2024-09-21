package com.example.factorFinder;

public interface WriteResult {
	WriteResultStatus getStatus();

	public static enum WriteResultStatus {
		SUCCESS, FAILURE;
	}
}
