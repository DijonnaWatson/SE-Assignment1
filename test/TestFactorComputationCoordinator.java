
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.factorfinder.FactorComputationCoordinator;
import com.example.factorfinder.FactorComputeRequest;
import com.example.factorfinder.FactorComputeResult;
import com.example.factorfinder.FactorComputeResult.ComputeResultStatus;
import com.example.factorfinder.FileInputConfig;
import com.example.factorfinder.FileOutputConfig;


public class TestFactorComputationCoordinator {
  @Test
  public void testFactorComputationCoordinator() {
    // Mock the FactorComputationCordinator API interface
    FactorComputationCordinator apiMock =
        Mockito.mock(FactorComputationCoordinator.class);

    // Mock of the Implemented InputConfig and OutputConfig, and
    // FactorComputeRequest
    FileInputConfig inputConfigMock = Mockito.mock(FileInputConfig.class);
    FileOutputConfig outputConfigMock = Mockito.mock(FileOutputConfig.class);
    FactorComputeRequest requestMock =
        new FactorComputeRequest(inputConfigMock, outputConfigMock, ',');

    // Mock the result
    FactorComputeResult resultMock =
        Mockito.mock(FactorComputeResult.class);

    // create a mock compute that uses the mock request and returns a mock
    // result
    when(apiMock.compute(requestMock)).thenReturn(resultMock);

    // create a getStatus to return SUCCESS status when the mock result calls
    // getStatus()
    // when i change SUCCESS to FAILURE, it does display the correct output
    // message for failure
    ComputeResultStatus successStatus = FactorComputeResult.SUCCESS.getStatus();
    when(resultMock.getStatus()).thenReturn(successStatus);

    // Pass the mock api and request to the prototype
    prototype(apiMock, requestMock);

    // Verify the interactions
    verify(apiMock).compute(requestMock);
  }

  // I did change the prototype params, idk if that's ok, but the test works now
  // so..
  public void prototype(
      FactorComputationCoordinator apiToCall, FactorComputeRequest request) {
    // Call the compute method using the apiToCall instance with the mocked
    // request
    FactorComputeResult result = apiToCall.compute(request);

    // Check if the result indicates success and print a message
    if (result.getStatus().isSuccess()) {
      System.out.println("Prototype Status retrieval successful.");
    } else {
      System.out.println("Prototype Status retrieval failed.");
    }
  }
}
