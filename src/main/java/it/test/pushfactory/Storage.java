package it.test.pushfactory;

public interface Storage<T> {

	boolean isCompleted();

	void setCompleted(boolean completed);

	T getElement();

	void setElement(T element);

	
	
	
}
