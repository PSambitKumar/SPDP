package com.spdp.bean;

public class RICircleBeans {

	private Long riCircleId;

	private String riCircleName;

	public Long getRiCircleId() {
		return riCircleId;
	}

	public void setRiCircleId(Long riCircleId) {
		this.riCircleId = riCircleId;
	}

	public String getRiCircleName() {
		return riCircleName;
	}

	public void setRiCircleName(String riCircleName) {
		this.riCircleName = riCircleName;
	}

	@Override
	public String toString() {
		return "RICircleBeans [riCircleId=" + riCircleId + ", riCircleName=" + riCircleName + "]";
	}
	
	
	
}
