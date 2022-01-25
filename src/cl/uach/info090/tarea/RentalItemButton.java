package cl.uach.info090.tarea;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JButton;
/**
 * La clase "RentalItemButton" creará un botón a partir de 
 * la serie del objeto, su descripcion, la hora base y el precio por hora.
 * 
 * @author Diego Troncoso Jara.
 *
 */
public class RentalItemButton extends JButton implements Rentable {
	private String serial;
	private String desc;
	private Double baseFee;
	private Double hourFee;
	private String clientName;
	private Date start;
	private Date end;
	
	
	private static final Color COLOR_AVIABLE = new Color(160,254,219);
	private static final Color COLOR_RENTED = new Color(255,91,96);
	
	/**
	 * El constructor RentalItemButton se encarga de establecer los atributos de los parámetros con sus respectivos 
	 * parámetros homónimos, exceptuando el nombre del cliente, la hora de inicio
	 * y la hora final que por defecto serán nulos.
	 * 
	 * @param serial es la serie del objeto.
	 * @param desc es la descripcion del objeto.
	 * @param baseFee es el precio base del objeto.
	 * @param hourFee es el precio por hora del objeto.
	 */
	public RentalItemButton(String serial, String desc, double baseFee, double hourFee) {
		this.serial = serial;
		this.setText(serial);
		this.desc = desc;
		this.baseFee = baseFee;
		this.hourFee = hourFee;
		this.clientName = null;
		this.start = null;
		this.end = null;
	}
	
	
	/**
	 * El método isRented verificará si el objeto estará arrendado.
	 * Si es que se le asigna un nombre de cliente y una hora de inicio
	 * es porque está arrendado, de caso contrario, está disponible.
	 */
	public  boolean isRented() {
		return(clientName != null && start != null);	
	}
	
	
	/**
	 * El metodo rentMe establece los parametros con sus parametros homonimos
	 * lo que significa que el objeto comienza a estar arrendado, estableciendo el nombre del cliente
	 * y la fecha de incio.
	 * 
	 * @param clientName es el nombre del cliente
	 * @param start es la fecha de inicio del objeto
	 */
	public void rentMe(String clientName, Date start) {
		
		this.clientName = clientName;
		this.start = start;
	}
	/**
	 * El metodo Receipt se encarga de generar una boleta a partir de la fecha final del arriendo
	 * y establece el nombre del cliente, la hora de inicio y final como null para que el objeto pueda ser arrendado nuevamente.
	 * 
	 * @param end es la fecha final del objeto arrendado
	 * @return boleta, este metodo retorna la boleta del objeto arrendado.
	 */
	public Receipt returnMe(Date end) {
		this.end=end;
		int time = (int)  (end.getTime() - start.getTime())/(60000);
		double total = this.getBaseFee() + this.hourFee * time;
		Receipt boleta = new Receipt(clientName, serial, start,  end, time, baseFee, hourFee, total);
		this.clientName = null;
		this.start = null;
		this.end = null;
		return boleta;
	}
	
	public String getSerial() {
		return serial;
	}



	public void setSerial(String serial) {
		this.serial = serial;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public Double getBaseFee() {
		return baseFee;
	}



	public void setBaseFee(Double baseFee) {
		this.baseFee = baseFee;
	}



	public Double getHourFee() {
		return hourFee;
	}



	public void setHourFee(Double hourFee) {
		this.hourFee = hourFee;
	}



	public String getClientName() {
		return clientName;
	}



	public void setClientName(String clientName) {
		this.clientName = clientName;
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


	/**
	 * Este metodo se encarga de verificar si el boton del objeto está rentado se establece el "COLOR_AVIABLE", en caso
	 * contrario el boton tendra el color "COLOR_RENTED".
	 * 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground((!isRented() ?  COLOR_AVIABLE : COLOR_RENTED));
		this.setOpaque(true);
	}
	
	
}
