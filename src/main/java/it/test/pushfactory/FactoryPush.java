package it.test.pushfactory;

public interface FactoryPush<T> {

	/**
	 * Get Partially added information permitting other modification
	 * 
	 * @return
	 */
	T getPartially();

	/**
	 * Get Object information blocking other modification
	 * 
	 * @return
	 */
	T get();

	
	/**
	 * Add other modification to data
	 * 
	 * @return
	 */
	public void pushData(Data baseData);

	/**
	 * return if other modification are possible
	 * 
	 * @return
	 */
	boolean isCompleted();

	/**
	 * do not permit anymore other modification
	 * 
	 * @return
	 */
	void complete();

	/**
	 * Get Object information recreating the object: have to be called as last call
	 * 
	 * @return
	 */
	T getAndComplete();


}
