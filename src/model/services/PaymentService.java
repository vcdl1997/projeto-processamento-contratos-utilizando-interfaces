package model.services;

import java.util.Date;

import model.entities.Contract;

public interface PaymentService {
	void generateInstallment(int amountInstallments, Contract contract);
	void makePayment(Date expirationDate, Contract contract);
}
