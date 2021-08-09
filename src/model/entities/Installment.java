package model.entities;

import java.util.Date;

public class Installment {
	private Date expirationDate;
	private Double value;
	private Boolean status;
	
	public Installment(Date expirationDate, Double value) {
		this.expirationDate = expirationDate;
		this.value = value;
		this.status = false;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public Double getValue() {
		return value;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
