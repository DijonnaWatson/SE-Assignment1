package factorfinder;

public interface WriteResult {
  static WriteResult SUCCESS = () -> WriteResultStatus.SUCCESS;
  static WriteResult FAILURE = () -> WriteResultStatus.FAILURE;

  WriteResultStatus getStatus();

  public static enum WriteResultStatus {
    SUCCESS(true),
    FAILURE(false);

    private final boolean success;

    private WriteResultStatus(boolean success) {
      this.success = success;
    }

    public boolean isSuccess() {
      return success;
    }
  }
}
