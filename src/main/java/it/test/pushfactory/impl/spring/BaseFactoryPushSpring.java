package it.test.pushfactory.impl.spring;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import it.test.pushfactory.BaseFactory;
import it.test.pushfactory.Data;
import it.test.pushfactory.impl.BaseFactoryPush;

public class BaseFactoryPushSpring<T> extends BaseFactoryPush<T> {

	@Autowired
	private ApplicationContext ac;

	@Override
	protected void _pushData(Data baseData) {

		Map<String, BaseFactory> baseFactories = ac.getBeansOfType(BaseFactory.class);

		for (Entry<String, BaseFactory> factoryVisitor : baseFactories.entrySet()) {

			if (factoryVisitor.getValue().myAction(baseData.getAction())) {
				setPartially((T) factoryVisitor.getValue().merge(getPartially(), baseData));
			}
		}

	}

}
