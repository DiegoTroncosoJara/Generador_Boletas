package cl.uach.info090.tarea;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * La clase Receipt es la encargada de generar las boletas y crear un archivo
 * con la fecha y el nombre del cliente que tendrá la información de la boleta.
 * 
 * @author Diego Troncoso Jara.
 *
 */
public class Receipt {
	private String clientName;
	private String serial;
	private Date start;
	private Date end;
	private int timeMin;
	private double baseFee;
	private double hourFee;
	private double total;
	private int hours,mins,secs;
	/**
	 * El metodo Receipt se encarga de establecer los parametros en sus respectivos
	 * parametros homonimos para generar una boleta de una forma más detallada.
	 * 
	 * 
	 * @param clientName es el nombre del cliente.
	 * @param serial es la serie del objeto.
	 * @param start es la fecha de inicio del objeto arrendado.
	 * @param end es la fecha final del objeto arrendado.
	 * @param timeMin es la diferencia de las fechas start y end.
	 * @param baseFee es el precio base del objeto.
	 * @param hourFee es el precio por hora del objeto.
	 * @param total el precio total del objeto.
	 */
	public 	Receipt(String clientName, String serial, Date start, Date end, int timeMin, double baseFee, double hourFee, double total) {
		this.clientName = clientName;
		this.serial = serial;
		this.start = start;
		this.end = end;
		this.timeMin = timeMin;
		this.baseFee = baseFee;
		this.hourFee = hourFee;
		this.total = total;
		
	}
	/**
	 * El metodo toString se encarga de imprimir una boleta en el panel del programa
	 * a partir de la fecha, la hora de inicio, la cantidad de tiempo y el total.
	 * 
	 */
	@Override
	public String toString() {
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		double hourDiff = (this.getEnd().getTime() - this.getStart().getTime())
				/ (1000.0 * 60 * 60);
		hours = (int) hourDiff;
		double minsDiff = (hourDiff - hours) * 60;
		mins = (int) minsDiff;
		secs = (int) ((minsDiff - mins) * 60);

		String rentalTime = hours+":"+mins + ":" + secs;
		
		String dato = dateFormat.format(start) + "    " + hourFormat.format(start) + "    " + rentalTime + "    $" + total;
		
		
		return (dato);
		
		
	}
	/**
	 * El metodo toFile es el encargado de generar un archivo .txt con la fecha del arriendo y el nombre del cliente.
	 * Además incorpora dentro del archivo la información de la boleta. 
	 * 
	 */
	public void toFile() {
		
		DateFormat fecha = new SimpleDateFormat("ddMMyyyy");  
		DateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		File file = new File("Boletas/"+fecha.format(start)+"_"+clientName+".txt");
		String relleno = 
				"Nombre del cliente :  " + clientName + "\n" +
				"Producto arrendado :  " + serial + "\n" +
				"Hora de arriendo   :  " + hourFormat.format(start) + "\n" +
				"Fecha de arriendo  :  " + fecha2.format(start) + "\n" +
				"Precio base        :  " + baseFee + "\n" +
				"Precio por hora    :  " + hourFee + "\n" +
				"----------------------\n"+
				"Tiempo Arrendado   :  " + hours+":"+mins+":"+secs  + "\n" +
				"Total              :  " + total + "\n"
				;
		while(!file.exists()) {
			try {
				file.createNewFile();
				FileWriter es = new FileWriter(file);
			    BufferedWriter n = new BufferedWriter(es);
			    n.write(relleno);
			    n.close();
				}catch(IOException ex) {
					ex.printStackTrace();
					}
		}
		
		
	}
	
	
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getTimeMin() {
		return timeMin;
	}
	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}
	public double getBaseFee() {
		return baseFee;
	}
	public void setBaseFee(double baseFee) {
		this.baseFee = baseFee;
	}
	public double getHourFee() {
		return hourFee;
	}
	public void setHourFee(double hourFee) {
		this.hourFee = hourFee;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

}
