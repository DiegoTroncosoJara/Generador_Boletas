package cl.uach.info090.tarea;

import java.util.Date;
/**
 * La clase BikeButton extiende a RentalItemButton para generar los botones tipo bicileta.
 * 
 * @author Diego Troncoso Jara
 *
 */
public class BikeButton extends RentalItemButton{
	
	/**
	 * El constructor BikeButton se encarga de establecer los parametros con sus
	 * parametros homonimos.
	 * 
	 * @param serie es la serie de la bicicleta.
	 * @param desc es la descripcion de la bicicleta.
	 * @param baseFee es el precio base de la bicicleta.
	 * @param hourFee es el precio por hora de la bicicleta.
	 */
	public BikeButton(String serie, String desc, double baseFee, double hourFee) {
        super(serie, desc, baseFee, hourFee);
    }
	
	/**
	 * El metodo returnMe se encarga de generar la boleta de la bicicleta y si es que se arrendo
	 * por mas de 3 horas, se le hace el descuento del precio base al total de la boleta.
	 * 
	 * @param end es la fecha final del arriendo de la bicicleta.
	 */
	@Override
	public Receipt returnMe(Date end) {
		int time = (int)  (end.getTime() - super.getStart().getTime())/(36*100000);
		if(time>3) {
			double total = super.getHourFee() * time;
			Receipt boleta = new Receipt(super.getClientName(), super.getSerial(), super.getStart(),  end, time, super.getBaseFee(), super.getHourFee(), total);
			super.setClientName(null);
			super.setStart(null);
			super.setEnd(null);
			return boleta;
		}else {
			double total = super.getBaseFee()+super.getHourFee() * time;
			Receipt boleta = new Receipt(super.getClientName(), super.getSerial(), super.getStart(),  end, time, super.getBaseFee(), super.getHourFee(), total);
			super.setClientName(null);
			super.setStart(null);
			super.setEnd(null);
			return boleta;
		}
	}
}
