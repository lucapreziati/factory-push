package it.test.pushfactory.impl;

import it.test.pushfactory.Storage;

public class BaseStorage<T> implements Storage<T> {

	protected boolean completed=false;
	protected T element;
	
	@Override
	public boolean isCompleted() {
		return completed;
	}
	
	@Override
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	@Override
	public T getElement() {
		return element;
	}
	
	@Override
	public void setElement(T element) {
		this.element = element;
	}
	
	
}
