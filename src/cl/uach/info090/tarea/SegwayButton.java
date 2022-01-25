package cl.uach.info090.tarea;

/**
 * La clase SegwayButton extiende a RentalItemButton para generar los botones tipo segway.
 * 
 * @author Diego Troncoso Jara
 *
 */
public class SegwayButton extends RentalItemButton{
	
	/**
	 * El constructor SegwayButton se encarga de pasar los par�metros a la clase padre que ser�a
	 * RentalItemButton.
	 * @param serie es la serie del seway.
	 * @param desc es la descricion del seway.
	 * @param baseFee es el precio base del seway.
	 * @param hourFee es el precio por hora del seway.
	 */
	public SegwayButton(String serie, String desc, double baseFee, double hourFee) {
        super(serie, desc, baseFee, hourFee);

    }

}
