package it.test;

public class PojoToTest {

	private String example;
	
	private String example1;
	
	private String example2;
	
	private String example3;

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getExample1() {
		return example1;
	}

	public void setExample1(String example1) {
		this.example1 = example1;
	}

	public String getExample2() {
		return example2;
	}

	public void setExample2(String example2) {
		this.example2 = example2;
	}

	public String getExample3() {
		return example3;
	}

	public void setExample3(String example3) {
		this.example3 = example3;
	}

	@Override
	public String toString() {
		return "PojoToTest [example=" + example + ", example1=" + example1 + ", example2=" + example2 + ", example3="
				+ example3 + "]";
	}
	
	
	
}
