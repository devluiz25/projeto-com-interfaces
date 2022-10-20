package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("*** DADOS DE ALUGUEIS DE CARROS ***");
		System.out.print("INFORME O MODELO DO CARRO: ");
		String carModel = sc.nextLine();
		System.out.print("INFORME A DATA E HORA DA RETIRADA(dd/MM/yyyy HH:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("INFORME A DATA E HORA DA DEVOLUÇÃO(dd/MM/yyyy HH:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("INFORME O PREÇO POR HORA U$: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("INFORME O PREÇO POR DIA U$: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("FATURA: ");
		System.out.println("PAGAMENTO BASICO: " + String.format("U$%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("IMPOSTO: " + String.format("U$%.2f", cr.getInvoice().getTax()));
		System.out.println("PAGAMENTO TOTAL: " + String.format("U$%.2f", cr.getInvoice().getTotalPayment()));
		
		sc.close();
	}

}
