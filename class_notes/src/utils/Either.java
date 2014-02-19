package utils;

public interface Either<T> {
	public boolean isSuccess();
	public boolean isFailure();
	public Throwable getError();
	public T getObject();
}
