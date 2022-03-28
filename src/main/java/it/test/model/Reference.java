package it.test.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cod", scope = Reference.class)
@Getter
@Setter
public class Reference {

	private String cod;
	private String superCod;

	public String getCode() {
		return StringUtils.join(cod, "|", superCod, ";");
	}

	private String base;
}
