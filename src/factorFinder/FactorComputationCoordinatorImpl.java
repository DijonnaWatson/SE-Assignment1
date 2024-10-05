package com.example.factorfinder;

import java.util.List;

public class FactorComputationCoordinatorImpl implements FactorComputationCordinator {
    private final FactorComputeEngineImpl computeEngine;
    private final FactorDataStoreImpl dataStore;

    public FactorComputationCoordinatorImpl(FactorComputeEngineImpl computeEngine, FactorDataStoreImpl dataStore) {
        this.computeEngine = computeEngine;
        this.dataStore = dataStore;
    }

    @Override
    public FactorComputeResult compute(FactorComputeRequest request) {
        FileInputConfig inputConfig = request.getInputConfig();
        List<Integer> inputData = (List<Integer>) dataStore.read(inputConfig);

        if (inputData == null || inputData.isEmpty()) {
            return FactorComputeResult.FAILURE;
        }

        try {
            for (Integer number : inputData) {
                List<String> formattedFactors = computeEngine.getFormattedFactors(number);
                FileOutputConfig outputConfig = request.getOutputConfig();

                if (outputConfig != null) {
                    WriteResult writeResult = dataStore.appendSingleResult(outputConfig, formattedFactors);
                    if (writeResult.getStatus() != WriteResult.WriteResultStatus.SUCCESS) {
                        return FactorComputeResult.FAILURE;
                    }
                }
            }
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return FactorComputeResult.FAILURE;
        }

        return FactorComputeResult.SUCCESS;
    }
}
