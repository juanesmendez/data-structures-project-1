package view;

import java.time.LocalDateTime;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LocalDateTime fecha1 = LocalDateTime.of(2017, 10, 30, 10, 40);
		System.out.println("fecha1: "+fecha1.toString());
		LocalDateTime fecha2 = LocalDateTime.of(2017, 10, 30, 10, 40);
		System.out.println("fecha2: "+fecha2.toString());
		if(fecha1.isAfter(fecha2)) {
			System.out.println("Hola");
		}
		
		if(fecha1.compareTo(fecha2) < 0) {
			System.out.println("Hola 2");
		}
		
	}

}
