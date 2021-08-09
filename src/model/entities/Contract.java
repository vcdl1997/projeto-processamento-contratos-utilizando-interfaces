package model.entities;

import java.util.Date;
import java.util.List;

public class Contract {
	private Integer number;
	private Date date;
	private Double value;
	private List<Installment> installments;
	
	public Contract(Integer number, Date date, Double value) {
		this.number = number;
		this.date = date;
		this.value = value;
	}

	public Integer getNumber() {
		return number;
	}

	public Date getDate() {
		return date;
	}
	
	public Double getValue() {
		return value;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}	
}	
