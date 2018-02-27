package api;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.world.CommunityAreaByDateRange;
import model.world.Company;
import model.world.CompanyByDateRange;
import model.world.CompanyTaxi;
import model.world.DateTimeRange;
import model.world.DistanceRange;
import model.world.InfoTaxiRange;
import model.world.Service;
import model.world.ServicesValuePayed;
import model.world.Taxi;

/**
 * Basic API for testing the functionality of the TaxiTrip manager
 */
public interface ITaxiTripsManager {
	
	//1C
	/**
	 * Dada la direcci�n del json que se desea cargar, se generan VO's, estructuras y datos necesarias
	 * @param direccionJson, ubicaci�n del json a cargar
	 * @return true si se lo logr� cargar, false de lo contrario
	 */
	public boolean cargarSistema(String[] direccionJson);
	//1A
	/**
	 * Generar	una	Cola con	 todos	los	servicios	de	 taxi	que	se	prestaron	en	un	periodo	de	
	 * tiempo	 dado	 por	 una	 fecha/hora	 inicial	 y	 una	 fecha/hora	 final	 de	 consulta.	 
	 * El	 inicio	 y	terminacion	del	servicio	debe	estar	incluido dentro	del	periodo	de	consulta.	
	 * Los	servicios deben	mostrarse	en	orden	cronologico	de	su	fecha/hora	inicial.	
	 */
	public IQueue<Service> darServiciosEnPeriodo(DateTimeRange rango);

	//2A
	/**
	 * Buscar el taxi de una compa��a dada que m�s servicios inici� en un periodo de tiempo
	 * dado por una fecha/hora inicial y una fecha/hora final de consulta.
	 */
	public Taxi darTaxiConMasServiciosEnCompaniaYRango(DateTimeRange rango, String company);

	//3A
	/**
	 * Buscar la informaci�n completa de un taxi, a partir de su identificador, en un periodo
	 * de tiempo dado por una fecha/hora inicial y una fecha/hora final de consulta. Incluye el
	 * nombre de su compa��a y los valores totales de plata ganada, de servicios prestados, de
	 * distancia recorrida y de tiempo total de servicios.
	 */
	public InfoTaxiRange darInformacionTaxiEnRango(String id, DateTimeRange rango);

	//4A
	/**
	 * Retornar una lista de rangos de distancia recorrida, en la que se encuentran todos los
	 * servicios de taxis servidos por las compa��as, en una fecha dada y en un rango de horas
	 * especificada. La informaci�n debe estar ordenada por la distancia recorrida, as� la primera 
	 * posici�n de la lista tiene a su vez una lista con todos los servicios cuya distancia recorrida
	 * esta entre [0 y 1) milla. En la segunda posici�n, los recorridos entre [1 y 2) millas, y as�
	 * sucesivamente.
	 */
	public LinkedList<DistanceRange>darListaRangosDistancia(String fecha, String horaInicial, String horaFinal);

	//1B
	/**
	 * Mostrar	la	informaci�n	de	las	compa��as	de	taxi	consistente	en:	El	total	de	compa��as	
	 * que	tienen	al	menos	un	taxi	inscrito	y	el	total	de	taxis	que	prestan	servicio	para	al	menos	
	 * una	 compa��a.	 Adicionalmente,	 generar	 la	 lista	 alfab�tica	 de	 compa��as	 a	 las	 cuales	
	 * aparecen	 inscritos	 los	 servicios	 de	 taxi	 de	 la	 fuente	 de	 datos	 de	 consulta.	 Por	 cada	
	 * compa��a	debe	informarse	su	nombre	y	el	n�mero	de	taxis	que	tiene	registrados
	 */
	public LinkedList<Company> darCompaniasTaxisInscritos();

	//2B
	/**
	 * Buscar el taxi de una compa��a dada que mayor facturaci�n ha generado en un
	 * periodo de tiempo dado por una fecha/hora inicial y una fecha/hora final de consulta.
	 * @param rango
	 * @param nomCompania
	 * @return VOTaxi
	 */
	public Taxi darTaxiMayorFacturacion(DateTimeRange rango, String nomCompania);

	//3B
	/**
	 * Buscar la informaci�n completa de una zona de la ciudad en un periodo de tiempo
	 * dado por una fecha/hora inicial y una fecha/hora final de consulta. El n�mero total de
	 * servicios que se recogieron en la zona de consulta y terminaron en otra zona y el valor
	 * total pagado por los usuarios; el n�mero total de servicios que se recogieron en otra zona
	 * y terminaron en la zona de consulta y el valor total pagado por los usuarios, y el total de
	 * servicios que iniciaron y terminaron en la misma zona de consulta y el valor total pagado
	 * por los usuarios.
	 * @param rango
	 * @param idZona
	 * @return VOServiciosValorPagado[]
	 */
	public ServicesValuePayed[] darServiciosZonaValorTotal(DateTimeRange rango, String idZona);

	//4B

	/**
	 * Retornar una lista con todas las zonas de la ciudad (ordenadas por su identificador).
	 * Cada zona debe tener el total de servicios iniciados en dicha zona en un rango de fechas.
	 * Por ejemplo, la primera posici�n de la lista tiene todos los servicios de la primer zona, en
	 * dicha posici�n, se tiene una lista de fechas (ordenadas cronol�gicamente) con el total de
	 * servicios asociados a dicha fecha.
	 */
	public 	LinkedList<CommunityAreaByDateRange> darZonasServicios (DateTimeRange rango);



	//2C
	/**
	 * Identificar el top X de compa��as que m�s servicios iniciaron en un periodo de tiempo
	 * dado por una fecha/hora inicial y una fecha/hora final. El valor X es un dato de consulta. El
	 * resultado debe mostrar el Top X de compa��as ordenadas por el n�mero de servicios de
	 * mayor a menor. Por cada compa��a debe informarse su nombre y su n�mero de servicios
	 * de respuesta.
	 */
	public LinkedList<CompanyByDateRange> companiasMasServicios(DateTimeRange rango, int n);

	//3C
	/**
	 * Buscar el taxi m�s rentable de cada compa��a. El taxi m�s rentable de una compa��a
	 * es aquel cuya relaci�n de plata ganada y distancia recorrida en los servicios prestados es mayor.
	 */
	public LinkedList<CompanyTaxi> taxisMasRentables();

	//4C
	/**
	 * Dada la gran cantidad de datos que requiere el proyecto, se desea poder compactar
	 * informaci�n asociada a un taxi particular. Para ello usted debe guardar en una pila todos
	 * los servicios generados por el taxi en orden cronol�gico, entre una hora inicial y una hora
	 * final, en una fecha determinada.
	 */
	public IStack <Service> darServicioResumen(String taxiId, String horaInicial, String horaFinal, String fecha, int maxMillas);




}
