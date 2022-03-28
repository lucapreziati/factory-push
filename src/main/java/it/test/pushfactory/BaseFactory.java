package it.test.pushfactory;


public interface BaseFactory<T> {

	T merge(T source, Data d);

	
	default boolean myAction(String action) {
		return getMyAction().equals(action);
	}

	public String getMyAction();

}
