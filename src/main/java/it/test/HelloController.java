package it.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.test.pushfactory.FactoryPush;
import it.test.pushfactory.impl.BaseData;

@RestController
public class HelloController {
	@Autowired
	FactoryPush<PojoToTest> fp;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}


	@GetMapping("/{param}/{param1}/{param2}/{param3}")
	public String index1(@PathVariable String param, @PathVariable String param1, @PathVariable String param2, @PathVariable String param3) {
	
		fp.pushData(new BaseData("example",null,param));
		
		System.out.println(fp.getPartially());
		
		fp.pushData(new BaseData("example1",null,param1));
		
		System.out.println(fp.getPartially());
		fp.pushData(new BaseData("example2",null,param2));
		
		System.out.println(fp.getPartially());
		fp.pushData(new BaseData("example3",null,param3));
		
		System.out.println(fp.getAndComplete());
		return "Greetings from Spring Boot!";
	}

}