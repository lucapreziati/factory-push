package it.test.example;

import org.springframework.stereotype.Service;

import it.test.PojoToTest;
import it.test.pushfactory.Data;
import it.test.pushfactory.impl.AbstractBaseFactory;

@Service
public class Example1 extends AbstractBaseFactory<PojoToTest> {

	@Override
	public PojoToTest _merge(PojoToTest source, Data d) {
		if (source == null) {
			source = new PojoToTest();
		}

		if (d.getPayload() instanceof String) {
			source.setExample1((String) d.getPayload());
		}

		return source;
	}

	@Override
	public String getMyAction() {
		return "example1";
	}

}
