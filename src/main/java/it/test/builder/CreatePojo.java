package it.test.builder;

public class CreatePojo {
	
	public Pojo  doPojo() {
		
		String name=extractName();
		//Generally here there is a lot of code and the next variable is related in some manner to the previous one: in my experience more or less 100 lines.
		
		String surname=extractSurname();
		//Generally here there is a lot of code and the next variable is related in some manner to the previous one: in my experience more or less 100 lines.
		
		Integer value=extractValue();
		//Generally here there is a lot of code and the next variable is related in some manner to the previous one: in my experience more or less 100 lines.
		
		return Pojo.builder().name(name).surname(surname).value(value).build();
		
		
	}

	private Integer extractValue() {
		return 0;
	}

	private String extractSurname() {
		return "Surname";
	}

	private String extractName() {
		return "name";
	}

}
