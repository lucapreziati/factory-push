package it.test.pushfactory.impl;

import java.util.HashMap;
import java.util.Map;

import it.test.pushfactory.Data;

public class BaseData implements Data {

	private static final long serialVersionUID = 5122397310840700676L;

	public BaseData() {
	}

	public BaseData(String action, Map<String, Object> header, Object payload) {
		super();
		this.action = action;
		this.header = header;
		this.payload = payload;
	}

	protected String action;
	protected Map<String, Object> header = new HashMap<>();
	protected Object payload;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Map<String, Object> getHeader() {
		return header;
	}

	public void setHeader(Map<String, Object> header) {
		this.header = header;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
