package utils;

public class Success<T> implements Either<T>{
	private T obj;
	
	public Success(T obj){
		this.obj = obj;
	}
	@Override
	public boolean isSuccess() {
		return true;
	}

	@Override
	public boolean isFailure() {
		return false;
	}

	@Override
	public Throwable getError() {
		return null;
	}

	@Override
	public T getObject() {
		return obj;
	}

}
