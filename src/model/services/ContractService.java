package model.services;

import model.entities.Contract;

public class ContractService {
	private Contract contract;

	public ContractService(Contract contract) {
		this.contract = contract;
	}
	
	@Override
	public String toString() {
		
		Double totalContract = contract.getInstallments().stream().mapToDouble(x -> x.getValue()).sum();
		totalContract = totalContract != null ? totalContract : 0.0; 
		
		Double amountPaid = contract.getInstallments().stream().filter(x -> x.getStatus() == true)
			.mapToDouble(x -> x.getValue()).sum();
		amountPaid = amountPaid != null ? amountPaid : 0.0; 
		
		Double outstandingAmount = contract.getInstallments().stream()
			.filter(x -> x.getStatus() == false).mapToDouble(x -> x.getValue()).sum();
		outstandingAmount = outstandingAmount != null ? outstandingAmount : 0.0; 
		
		StringBuilder strb = new StringBuilder();
		
		return strb
			.append("Informations Contract\n")
			.append("Total contract value: ")
			.append(String.format("%.2f", totalContract) + "\n")
			.append("Total amount paid: ")
			.append(String.format("%.2f", amountPaid)  + "\n")
			.append("Total outstanding amount: ")
			.append(String.format("%.2f", outstandingAmount))
			.toString();
	}
}
