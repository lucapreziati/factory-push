package it.test.pushfactory.impl;

import it.test.pushfactory.BaseFactory;
import it.test.pushfactory.Data;

public abstract class AbstractBaseFactory<T> implements BaseFactory<T> {

	@Override
	public T merge(T source, Data d) {
		if (!myAction(d.getAction())) {
			return source;
		}

		return _merge(source, d);
	}

	public abstract T _merge(T source, Data d);

}
