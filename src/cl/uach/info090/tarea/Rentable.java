package cl.uach.info090.tarea;

import java.util.Date;
/**
 * La clase Rentable es una interfaz que se encarga de establecer los metodos
 * isRented, rentMe y returnMe para que otras clases la puedan sobreescribir.
 * 
 * @author Diego Troncoso Jara.
 *
 */
public interface Rentable {
	/**
	 * El metodo isRented se encarga de verificar si el objeto está arrendado.
	 */
	public boolean isRented();
	
	
	/**
	 * El metodo rentMe establece los parametros con sus parametros homonimos
	 * lo que significa que el objeto comienza a estar arrendado, estableciendo el nombre del cliente
	 * y la fecha de incio.
	 * 
	 * @param clientName es el nombre del cliente que arrendo el objeto.
	 * @param start es la fecha de inicio en el que se arrendo el objeto.
	 */
	public void rentMe(String clientName, Date start); 
	
	
	/**
	 * El metodo Receipt se encarga de generar una boleta a partir de la fecha final del arriendo
	 * y establece el nombre del cliente, la hora de inicio y final como null para que el objeto pueda ser arrendado nuevamente.
	 * 
	 * @param end es la fecha final del objeto arrendado
	 * @return boleta, este metodo retorna la boleta del objeto arrendado.
	 */
	public Receipt returnMe(Date end); 
}
