package cl.uach.info090.tarea;

/**
 * La clase KayakButton extiende a la clase RentalItemButton para generar los botones tipo Kayak.
 * 
 * @author Diego Troncoso Jara.
 *
 */
public class KayakButton extends RentalItemButton {
	
	/**
	 * El constructor KayakButton se encarga de pasar los parámetros a la clase padre que sería
	 * RentalItemButton.
	 * @param serie es la serie del kayak.
	 * @param desc es la descripcion del kayak.
	 * @param baseFee es el precio base del kayak.
	 * @param hourFee es el precio por hora del kayak.
	 */
	public KayakButton(String serie, String desc, double baseFee, double hourFee) {
        super(serie, desc, baseFee, hourFee);

    }

}
