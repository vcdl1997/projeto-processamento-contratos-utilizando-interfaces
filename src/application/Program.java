package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Enter contract data");
		System.out.print("Number: ");
		Integer number = scan.nextInt();
		
		scan.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(scan.nextLine());
		
		System.out.print("Contract value: ");
		Double value = scan.nextDouble();
		
		Contract contract = new Contract(number, date, value);
		
		System.out.println("Enter number of installments: ");
		int installments = scan.nextInt();
		
		PaypalService paypalService = new PaypalService();
		paypalService.generateInstallment(installments, contract);
		
		System.out.println("Installments: ");
		for(Installment installment :  contract.getInstallments()) {
		StringBuilder strb = new StringBuilder("");
		
			strb
				.append(sdf.format(installment.getExpirationDate()))
				.append(" - ")
				.append(String.format("%.2f", installment.getValue()))
				.toString();
			
			System.out.println(strb);
		}
		
		ContractService contractService = new ContractService(contract);
		System.out.println(contractService.toString() + "\n");
		
		scan.nextLine();
		System.out.println("Enter the date of the installment you want to pay: ");
		Date payment = sdf.parse(scan.nextLine());
		
		paypalService.makePayment(payment, contract);
		System.out.println(contractService.toString() + "\n");
		
		scan.close();
	}

}
