package view;

import java.util.Scanner;

import controller.Controller;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;
import model.world.*;

public class TaxiTripsManagerView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		while(!fin)
		{
			//imprime menu
			printMenu();

			//opcion req
			int option = sc.nextInt();

			switch(option)
			{
			//1C cargar informacion dada
			case 1:

				//imprime menu cargar
				printMenuCargar();

				//opcion cargar
				int optionCargar = sc.nextInt();

				//directorio json
				String linkJson = "";
				switch (optionCargar)
				{
				//direccion json pequeno
				case 1:

					linkJson = TaxiTripsManager.DIRECCION_SMALL_JSON;
					break;

					//direccion json mediano
				case 2:

					linkJson = TaxiTripsManager.DIRECCION_MEDIUM_JSON;
					break;

					//direccion json grande
				case 3:

					linkJson = TaxiTripsManager.DIRECCION_LARGE_JSON;
					break;
				}

				//Memoria y tiempo
				long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				long startTime = System.nanoTime();

				//Cargar data
				boolean cargo = false;
				cargo = Controller.cargarSistema(linkJson);
				if(cargo) {
					System.out.println("SE HA CARGADO EL SISTEMA EXITOSAMENTE.");
					System.out.println("SE ARMO EL DISEÑO DEL SISTEMA CON TODAS SUS LISTAS Y RELACIONES ENTRE CLASES. CADA CLASE ORDENADA POR CRITERIOS DISTINTOS.");
					System.out.println();
				}
				
				
				//Tiempo en cargar
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/(1000000);

				//Memoria usada
				long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				System.out.println("Tiempo en cargar: " + duration + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");

				break;

				//1A	
			case 2:

				//fecha inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq1A = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq1A = sc.next();

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq1A = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq1A = sc.next();

				//VORangoFechaHora
				DateTimeRange rangoReq1A = new DateTimeRange(fechaInicialReq1A, fechaFinalReq1A, horaInicialReq1A, horaFinalReq1A);

				//Se obtiene la queue dada el rango
				IQueue<Service> colaReq1A = Controller.darServiciosEnRango(rangoReq1A);
				
				
				//TODO 
				//Recorra la cola y muestre cada servicio en ella
				for(Service s:colaReq1A) {
					System.out.println("Service ID: "+s.getTripId()+"\t\tTrip Start Date: "+s.getTripStart()); //Check if it's only necessary to print these attributes or to use the toString()
				}
				break;

			case 3: //2A

				//comany
				System.out.println("Ingrese el nombre de la compañia");
				String companyReq2A = sc.nextLine();
				companyReq2A = sc.nextLine();
				System.out.println("NOMBRE COMPAÑIA: "+companyReq2A);
				//fecha inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq2A = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq2A = sc.next();

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq2A = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq2A = sc.next();

				//VORangoFechaHora
				DateTimeRange rangoReq2A = new DateTimeRange(fechaInicialReq2A, fechaFinalReq2A, horaInicialReq2A, horaFinalReq2A);
				Taxi taxiReq2A = Controller.darTaxiConMasServiciosEnCompaniaYRango(rangoReq2A, companyReq2A);

				
				//TODO
				//Muestre la info del taxi
				System.out.println(taxiReq2A.toString());
				break;

			case 4: //3A

				//comany
				System.out.println("Ingrese el id del taxi");
				String idTaxiReq3A = sc.next();

				//fecha inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq3A = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq3A = sc.next();

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq3A = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq3A = sc.next();

				//VORangoFechaHora
				DateTimeRange rangoReq3A = new DateTimeRange(fechaInicialReq3A, fechaFinalReq3A, horaInicialReq3A, horaFinalReq3A);
				InfoTaxiRange taxiReq3A = Controller.darInformacionTaxiEnRango(idTaxiReq3A, rangoReq3A);

				//TODO
				//Muestre la info del taxi
				System.out.println();
				System.out.println("INFORMACION TAXI:");
				System.out.println();
				if(taxiReq3A!=null) {
					System.out.println(taxiReq3A.toString());
				}else {
					System.out.println("No se encontro el taxi en el sistema.");
					System.out.println();
				}

				break;

			case 5: //4A

				//fecha 
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaReq4A = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq4A = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq4A = sc.next();

				LinkedList<DistanceRange> listaReq4A = Controller.darListaRangosDistancia(fechaReq4A, horaInicialReq4A, horaFinalReq4A);

				//TODO
				
				//Recorra la lista y por cada VORangoDistancia muestre los servicios
				System.out.println();
				for(DistanceRange distRange:listaReq4A) {
					System.out.println(distRange.toString());
					System.out.println();
					for(Service s: distRange.getServicesInRange()) {
						System.out.println(s.toString());
						System.out.println();
					}
					System.out.println();
					System.out.println();
				}
				break;

			case 6: //1B
				LinkedList<Company> lista=Controller.darCompaniasTaxisInscritos();
				
				System.out.println("Number of companies with at least 1 taxi: "+lista.size());
				
				for (Company l:lista)
				{
					
					System.out.println("Company name: "+l.getName()+"\t\t Amount of Taxis: "+l.getTaxis().size());
				}
				//TODO
				//Mostrar la informacion de acuerdo al enunciado
				break;

			case 7: //2B
				//fecha inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq2B = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq2B = sc.next();

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq2B = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq2B = sc.next();

				//Compania
				System.out.println("Ingrese el nombre de la compa�ia");
				String compania2B=sc.next();

				//VORangoFechaHora
				DateTimeRange rangoReq2B = new DateTimeRange(fechaInicialReq2B, fechaFinalReq2B, horaInicialReq2B, horaFinalReq2B);

				Taxi taxi=Controller.darTaxiMayorFacturacion(rangoReq2B, compania2B);

				//TODO
				//Mostrar la informacion del taxi obtenido

				break;

			case 8: //3B
				//Compania
				System.out.println("Ingrese el id de la zona");
				String zona3B=sc.next();

				//fecha inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq3B = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq3B = sc.next();

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq3B = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq3B = sc.next();

				DateTimeRange rango3B= new DateTimeRange(fechaInicialReq3B, fechaFinalReq3B, horaInicialReq3B, horaFinalReq3B);

				ServicesValuePayed[] resp=Controller.darServiciosZonaValorTotal(rango3B, zona3B);
				//TODO
				//Mostrar la informacion de acuerdo al enunciado
				break;

			case 9: //4B
				//fecha inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq4B = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq4B = sc.next();

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq4B = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq4B = sc.next();
				DateTimeRange rango4B= new DateTimeRange(fechaInicialReq4B, fechaFinalReq4B, horaInicialReq4B, horaFinalReq4B);

				LinkedList<CommunityAreaByDateRange> lista4B= Controller.darZonasServicios(rango4B);
				//TODO
				//Mostrar la informacion de acuerdo al enunciado

				break;

			case 10: //2C
				System.out.println("Ingrese el n�mero n de compa�ias");
				int n2C=sc.nextInt();

				//fecha inicial
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaInicialReq2C = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq2C = sc.next();

				//fecha final
				System.out.println("Ingrese la fecha final (Ej : 2017-02-01)");
				String fechaFinalReq2C = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq2C = sc.next();

				DateTimeRange rango2C= new DateTimeRange(fechaInicialReq2C, fechaFinalReq2C, horaInicialReq2C, horaFinalReq2C);

				LinkedList<CompanyByDateRange> lista2C= Controller.companiasMasServicios(rango2C, n2C);
				//TODO
				//Mostrar la informacion de acuerdo al enunciado				
				
				System.out.println();
				System.out.println("TOP "+n2C+" COMPAÑIAS:");
				System.out.println();
				for(CompanyByDateRange c:lista2C) {
					System.out.println(c.toString());
					System.out.println();
				}
				System.out.println();
				break;

			case 11: //3C
				LinkedList<CompanyTaxi> lista3C=Controller.taxisMasRentables();
				
				//TODO
				//Mostrar la informacion de acuerdo al enunciado				
				for(CompanyTaxi ct:lista3C) {
					System.out.println(ct.toString());
					System.out.println();
				}
				
				break;

			case 12: //4C

				//id taxi
				System.out.println("Ingrese el id del taxi");
				String idTaxi4C=sc.next();
				
				System.out.println("Ingrese el valor maximo de la suma de millas recorridas por servicio resumen:");
				int n4C = sc.nextInt();
				//fecha 
				System.out.println("Ingrese la fecha inicial (Ej : 2017-02-01)");
				String fechaReq4C = sc.next();

				//hora inicial
				System.out.println("Ingrese la hora inicial (Ej: 09:00:00.000)");
				String horaInicialReq4C = sc.next();

				//hora final
				System.out.println("Ingrese la hora final (Ej: 09:00:00.000)");
				String horaFinalReq4C = sc.next();

				IStack <Service> resp4C=Controller.darServicioResumen(idTaxi4C, horaInicialReq4C, horaFinalReq4C, fechaReq4C, n4C);
				//TODO
				//Mostrar la informacion de acuerdo al enunciado				
				for(Service s: resp4C) {
					System.out.println(s.toString());
					System.out.println();
				}
				
				
				break;

			case 13: //salir
				fin=true;
				sc.close();
				break;

			}
		}
	}

	private static void printMenu() //
	{
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Proyecto 1----------------------");
		System.out.println("Cargar data (1C):");
		System.out.println("1. Cargar toda la informacion dada una fuente de datos (small,medium, large).");

		System.out.println("\nParte A:\n");
		System.out.println("2.Obtenga una lista con todos los servicios de taxi ordenados cronologicamente por su fecha/hora inicial, \n"
				+ " que se prestaron en un periodo de tiempo dado por una fecha/hora inicial y una fecha/hora final de consulta. (1A) ");
		System.out.println("3.Dada una compania y un rango de fechas y horas, obtenga  el taxi que mas servicios inicio en dicho rango. (2A)");
		System.out.println("4.Consulte la compania, dinero total obtenido, servicios prestados, distancia recorrida y tiempo total de servicios de un taxi dados su id y un rango de fechas y horas. (3A)");
		System.out.println("5.Dada una fecha y un rango de horas, obtenga una lista de rangos de distancia en donde cada pocision contiene los servicios de taxis cuya distancia recorrida pertence al rango de distancia. (4A)\n");

		System.out.println("Parte B: \n");
		System.out.println("6. Obtenga el numero de companias que tienen al menos un taxi inscrito y el numero total de taxis que trabajan para al menos una compania. \n"
				+ "Luego, genera una lista (en orden alfabetico) de las companias a las que estan inscritos los servicios de taxi. Para cada una, indique su nombre y el numero de taxis que tiene registrados. (1B)");
		System.out.println("7. Dada una compania, una fecha/hora inicial y una fecha/hora final, buscar el taxi de la compania que mayor facturacion gener� en el tiempo dado. (2B)");
		System.out.println("8. Dada una zona de la ciudad, una fecha/hora inicial y una fecha/hora final, dar la siguiente informacion: (3B) \n"
				+ "   -Numero de servicios que iniciaron en la zona dada y terminaron en otra zona, junto con el valor total pagado por los usuarios. \n"
				+ "   -Numero de servicios que iniciaron en otra zona y terminaron en la zona dada, junto con el valor total pagado por los usuarios. \n"
				+ "   -Numero de servicios que iniciaron y terminaron en la zona dada, junto con el valor total pagado por los usuarios.");
		System.out.println("9. Dado un rango de fechas, obtener la lista de todas las zonas, ordenadas por su identificador. Para cada zona, dar la lista de fechas dentro del rango (ordenadas cronol�gicamente) \n "
				+ "y para cada fecha, dar el numero de servicios que se realizaron en dicha fecha. (4B)");


		System.out.println("\nParte C: \n");
		System.out.println("10. Dado un numero n, una fecha/hora inicial y una fecha/hora final, mostrar las n companias que mas servicios iniciaron dentro del rango. La lista debe estar ordenada descendentemente \n por el numero de servicios. Para cada compania, dar el nombre y el numero de servicios (2C)");
		System.out.println("11. Para cada compania, dar el taxi mas rentable. La rentabilidad es dada por la relacion entre el dinero ganado y la distancia recorrida. (3C)");
		System.out.println("12. Dado un taxi, dar el servicio resumen, resultado de haber comprimido su informacion segun el enunciado. (4C) ");
		System.out.println("13. Salir");
		System.out.println("Type the option number for the task, then press enter: (e.g., 1):");

	}

	private static void printMenuCargar()
	{
		System.out.println("-- Que fuente de datos desea cargar?");
		System.out.println("-- 1. Small");
		System.out.println("-- 2. Medium");
		System.out.println("-- 3. Large");
		System.out.println("-- Type the option number for the task, then press enter: (e.g., 1)");
	}

}
