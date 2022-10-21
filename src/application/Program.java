package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("*** DADOS DE ALUGUEIS DE CARROS ***");
		System.out.println("---------------------------------------------------------------");
		System.out.print("INFORME O MODELO DO CARRO: ");
		String model = sc.nextLine();
		System.out.print("INFORME A DATA DA RETIRADA(dd/MM/yyyy HH:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("INFORME A DATA DA DEVOLUÇÃO(dd/MM/yyyy HH:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("INFORME O PREÇO POR HORA: R$");
		double pricePerHour = sc.nextDouble();
		System.out.print("INFORME O PREÇO POR DIA: R$");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(carRental);
		
		System.out.println("");
		System.out.println("FATURA: ");
		System.out.println("---------------------------------------------------------------");
		System.out.println("MODELO DO CARRO ALUGADO: " + model.toUpperCase());
		System.out.println("PAGAMENTO BASICO: " + String.format("R$%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("IMPOSTO: " + String.format("R$%.2f", carRental.getInvoice().getTax()));
		System.out.println("PAGAMENTO TOTAL: " + String.format("R$%.2f", carRental.getInvoice().totalPayment()));
		
		sc.close();
	}

}
