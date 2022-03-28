package it.test.pushfactory;

import java.io.Serializable;
import java.util.Map;

public interface Data extends Serializable {

	public void setAction(String action);

	public String getAction();

	public Map<String, Object> getHeader();

	public void setHeader(Map<String, Object> header);

	public Object getPayload();

	public void setPayload(Object payload);

}
