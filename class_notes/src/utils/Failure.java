package utils;

public class Failure<T> implements Either<T>{
	private Throwable err;

	public Failure(Throwable err){
		this.err = err;
	}
	@Override
	public boolean isSuccess() {
		return false;
	}

	@Override
	public boolean isFailure() {
		return true;
	}

	@Override
	public Throwable getError() {
		return err;
	}

	@Override
	public T getObject() {
		return null;
	}

}
