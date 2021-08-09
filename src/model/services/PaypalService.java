package model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class PaypalService implements PaymentService{
	
	private static final Double SIMPLE_INTEREST = 0.01;
	private static final Double TAX_PAYMENT = 0.02;

	
	@Override
	public void generateInstallment(int amountInstallments, Contract contract) {
		List<Installment> installments = new ArrayList<>();
		Date initialDate = contract.getDate();
		
		for(int i=1; i<=amountInstallments; i++) {
			Double valueInstallment = contract.getValue() / amountInstallments;
			valueInstallment += valueInstallment * SIMPLE_INTEREST * i;
			Double tax = valueInstallment * TAX_PAYMENT;
			
			valueInstallment += tax;
			
			Calendar calendar = Calendar.getInstance(); 
			calendar.setTime(initialDate); 
			calendar.add(Calendar.MONTH, 1);
			
			initialDate = calendar.getTime();
			
			installments.add(new Installment(initialDate, valueInstallment));
		}
		
		contract.setInstallments(installments);
	}

	
	@Override
	public void makePayment(Date expirationDate, Contract contract) {
		if(contract.getInstallments().size() == 0) return;
		
		for(Installment installment : contract.getInstallments()) {
			if(installment.getExpirationDate().equals(expirationDate)) {
				installment.setStatus(true);
			}
		}
	}
	
}
