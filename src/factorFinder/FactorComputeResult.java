package factorfinder;

public interface FactorComputeResult {
  static FactorComputeResult SUCCESS = () -> ComputeResultStatus.SUCCESS;
  static FactorComputeResult FAILURE = () -> ComputeResultStatus.FAILURE;

  ComputeResultStatus getStatus();

  public static enum ComputeResultStatus {
    SUCCESS(true),
    FAILURE(false);

    private final boolean success;

    private ComputeResultStatus(boolean success) {
      this.success = success;
    }

    public boolean isSuccess() {
      return success;
    }
  }
}

