package com.example.factorfinder;

public class FactorComputationCoordinatorImpl
    implements FactorComputationCordinator {
  private final FactorComputeEngine
      computeEngine; // Reference to the compute engine

  public FactorComputationCoordinatorImpl(FactorComputeEngine computeEngine) {
    this.computeEngine = computeEngine;
  }
  @Override
  public FactorComputeResult compute(FactorComputeRequest request) {
    // Extract the number from input config
    int number = request.getInputConfig().getNumber();

    // Perform the computation using the compute engine
    int[] factors = computeEngine.getFactors(number);

    // Create and return the result
    return new FactorComputeResult(factors);
  }
