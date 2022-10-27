package com.batch.restapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8830593648962212598L;
	
	private Integer poolId;
	private List<Integer> poolValues = new ArrayList<>();
	
	/**
	 * 
	 */
	public RequestDTO() {
		
	}

	/**
	 * @param poolId
	 * @param poolValues
	 */
	public RequestDTO(Integer poolId, List<Integer> poolValues) {
		this.poolId = poolId;
		this.poolValues = poolValues;
	}

	/**
	 * @param poolId the poolId to set
	 */
	public void setPoolId(Integer poolId) {
		this.poolId = poolId;
	}
	
	/**
	 * @return the poolId
	 */
	public Integer getPoolId() {
		return poolId;
	}

	/**
	 * @return the poolValues
	 */
	public List<Integer> getPoolValues() {
		return poolValues;
	}

	/**
	 * @param poolValues the poolValues to set
	 */
	public void setPoolValues(List<Integer> poolValues) {
		this.poolValues = poolValues;
	}

	@Override
	public String toString() {
		return "RequestDTO [poolId=" + poolId + ", poolValues=" + poolValues + "]";
	}
}
