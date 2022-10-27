package com.batch.restapi;

import java.io.Serializable;

public class ResponseTotalCountDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6192060085816668010L;
	
	private Integer poolId;
	private Integer percentile;
	
	/**
	 * 
	 */
	public ResponseTotalCountDTO() {
	}
	
	/**
	 * @return the poolId
	 */
	public Integer getPoolId() {
		return poolId;
	}
	/**
	 * @param poolId the poolId to set
	 */
	public void setPoolId(Integer poolId) {
		this.poolId = poolId;
	}
	/**
	 * @return the percentile
	 */
	public Integer getPercentile() {
		return percentile;
	}
	/**
	 * @param percentile the percentile to set
	 */
	public void setPercentile(Integer percentile) {
		this.percentile = percentile;
	}
}
