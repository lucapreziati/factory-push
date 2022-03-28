package it.test.pushfactory.impl;

import it.test.pushfactory.Data;
import it.test.pushfactory.FactoryPush;

public abstract class BaseFactoryPush<T> implements FactoryPush<T> {

	protected ThreadLocal<BaseStorage<T>> threadLocal = ThreadLocal.withInitial(() -> new BaseStorage<T>());

	@Override
	public T getPartially() {
		return threadLocal.get().getElement();
	}

	protected void setPartially(T element) {
		threadLocal.get().setElement(element);
	}

	@Override
	public T get() {
		threadLocal.get().setCompleted(true);
		return threadLocal.get().getElement();
	}

	@Override
	public T getAndComplete() {
		threadLocal.get().setCompleted(true);
		T element = threadLocal.get().getElement();
		complete();
		return element;
	}

	@Override
	public void pushData(Data baseData) {
		if (isCompleted()) {
			throw new RuntimeException("Cannot add any other information, get or complete are called");
		}
		_pushData(baseData);

	}

	@Override
	public void complete() {
		threadLocal.set(new BaseStorage<T>());
	}

	@Override
	public boolean isCompleted() {
		return threadLocal.get().isCompleted();

	}

	protected abstract void _pushData(Data baseData);
}
