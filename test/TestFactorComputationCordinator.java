package com.example.factorfinder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestFactorComputationCordinator {
  @Test
  public void testFactorComputationCordinator() {
    // Mock the FactorComputationCordinator API interface
    FactorComputationCordinator apiMock =
        Mockito.mock(FactorComputationCordinator.class);

    // Mock the InputConfig, OutputConfig, FactorComputeRequest, and
    // FactorComputeResult
    InputConfig inputConfigMock = Mockito.mock(InputConfig.class);
    OutputConfig outputConfigMock = Mockito.mock(OutputConfig.class);

    // create the request using the mocks
    FactorComputeRequest requestMock =
        new FactorComputeRequest(inputConfigMock, outputConfigMock, ',');

    // Mock the result
    FactorComputeResult resultMock = Mockito.mock(FactorComputeResult.class);

    // Configure the mock behavior - when compute is called, return the mocked
    // result
    when(apiMock.compute(requestMock)).thenReturn(resultMock);

    // Define what happens when result.getStatus().isSuccess() is called
    when(resultMock.getStatus().isSuccess()).thenReturn(true);

    // Used the mocked API in the test
    prototype(apiMock);

    // Verify the interactions
    verify(apiMock).compute(requestMock); // Verifies that compute was called
                                          // with the mocked request
  }

  public void prototype(FactorComputationCordinator apiToCall) {
    // For now, use an anonymous inner class - other approaches might be to set
    // this to null, use
    //  a mock object, of make InputConfig a class rather than an interface. All
    //  of those accomplish the same goal: the client is going to get input
    //  information from somewhere, it could be a List<Integer>, a single int, a
    //  csv file with integers, a database table with integers, etc - that's
    //  going to be an implementation detail
    InputConfig inputConfig = new InputConfig() {
      private List<Integer> inputData =
          Arrays.asList(1, 2, 3, 4, 5); // placeholder random ints in an array

      @Override
      public List<Integer> getInputData() {
        return inputData; // Return the list of integers
      }

      @Override
      public String getFilePath() {
        // Provide a valid implementation
        return ""; // Return empty string
      }
    };

    // An example of just using null to indicate 'we haven't decided yet, that's
    // for the implementation'
    OutputConfig outputConfigMock = Mockito.mock(OutputConfig.class);;

    // This is also a case where we could make ComputeRequest an interface and
    // just use an anonymous inner class - that lets us punt on how the default
    // delimiter will be specified. On the other hand, an overloaded constructor
    // isn't that terrible. If you've spotted that this would be an excellent
    // place to apply the Builder pattern, you're right! This sample code is
    // written without the design patterns we've started to cover, but feel free
    // to go back to refactor your own code if you notice that it can be
    // improved with any of the topics we cover throughout the semester. IRL,
    // development teams often allocate 10-25% of their time to this sort of
    // refactoring - it's often called "paying down technical debt"
    FactorComputeRequest request =
        new FactorComputeRequest(inputConfigMock, outputConfigMock, ',');

    // Now we've assembled all the pieces that we know, based on the system
    // specification description, that we're going to need - an input, output,
    // and delimiter All that's left is to actually run the computation, and
    // check that it worked:
    FactorComputeResult result = apiToCall.compute(request);

    // Here's an example of using an enum to wrap a boolean success value. This
    // gives us the option of providing more detailed
    // failure information later, even if we don't know what it should be now.
    // Another advantage of having API design be its own deliberate step
    // is that it sets aside time to really polish the APIs - everything else in
    // the project is going to depend on this code, so it's worth taking the
    // extra few minutes to set up an enum, and then give it a wrapped boolean -
    // when you actually start building the implementation, you'll appreciate
    // your Past Self for making life easy for Future Self
    if (result.getStatus().isSuccess()) {
      System.out.println("Yay!");
    }
  }
}
