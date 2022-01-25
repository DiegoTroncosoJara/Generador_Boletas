package cl.uach.info090.tarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;



/**
 * La clase RentalApp es la ventana principal del programa.
 * @author Diego Troncoso Jara 
 *
 */
public class RentalApp extends JFrame {
	
	private String filePath;
	private JPanel p1, p2, p3;
	private JLabel text;
	private Font FUENTE,FUENTE2,FUENTE_SET,FUENTE_SET2;
	private JButton salir,exportar,arrendar;
	private JScrollPane listScrollPane;
	private JList<Receipt> listReceipts;
	private DefaultListModel<Receipt> listModel;
	private JLabel s,d,vb,vh,e,c,i;
	private JFrame nuevaVentana;
	public static RentalApp j;
	public RentalItemButton b; // b es el boton del objeto que quiere arrendar.
	
 
	public static final SimpleDateFormat FECHA= new SimpleDateFormat("dd/MM/yyyy    HH:mm");
	private static final Color COLOR_DETAILS = new Color(255,232,233);
	private static final Color COLOR_ARRENDAR= new Color(4,166,0);
	private static final Color COLOR_FINALIZAR= new Color(234,0,58);
	
	
	
	
	/**
	 * El constructor RentalApp es la ventana en el cual
	 * se va desarrollar el programa, llamando a los metodos RentalItemButton y showDetails.
	 * 
	 * @param id es la ruta del archivo "rental_items.csv"
	 */
	public RentalApp(String id) {
		this.filePath = id;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(500,150,950,700);
		this.setResizable(false);
		this.setLayout(null);
		
		FUENTE = new Font("serif", Font.LAYOUT_LEFT_TO_RIGHT, 23);
		FUENTE2 = new Font("serif", Font.PLAIN, 18);
		FUENTE_SET = new Font("serif",Font.LAYOUT_RIGHT_TO_LEFT,17);
		FUENTE_SET2 = new Font("serif",Font.ITALIC,18);
		
	
		

		// Declaracion de los paneles
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		
		// Posicion paneles y caracteristica
		p1.setLayout(new GridLayout(4,4,5,5));
		p1.setBounds(5, 5, 545, 440);
		p2.setBounds(5,460,545,460);
		p3.setBounds(555,5,390,440);
		p3.setBackground(COLOR_DETAILS);
		
		// Lado derecho 
		JLabel serie = new JLabel("Serie: ");
		s = new JLabel("");
		serie.setBounds(560,20,100,20);
		serie.setFont(FUENTE_SET2);
		s.setBounds(700,20,100,20);
		s.setFont(FUENTE_SET);
		this.add(serie);
		this.add(s);
		
		JLabel desc = new JLabel("Desc: ");
		d = new JLabel("");
		desc.setBounds(560,83,100,20);
		desc.setFont(FUENTE_SET2);
		d.setBounds(700,83,220,20);
		d.setFont(FUENTE_SET);
		this.add(desc);
		this.add(d);
		
		JLabel valorBase = new JLabel("Valor Base: ");
		vb = new JLabel("");
		valorBase.setBounds(560,146,100,20);
		valorBase.setFont(FUENTE_SET2);
		vb.setBounds(700,146,100,20);
		vb.setFont(FUENTE_SET);
		this.add(valorBase);
		this.add(vb);
		
		JLabel valorHora = new JLabel("Valor Hora: ");
		vh = new JLabel("");
		valorHora.setBounds(560,209,100,20);
		valorHora.setFont(FUENTE_SET2);
		vh.setBounds(700,209,100,20);
		vh.setFont(FUENTE_SET);
		this.add(valorHora);
		this.add(vh);
		
		JLabel cliente = new JLabel("Cliente: ");
		c = new JLabel("");
		cliente.setBounds(560,335,100,20);
		cliente.setFont(FUENTE_SET2);
		c.setBounds(700,335,170,23);
		c.setFont(FUENTE_SET);
		
		this.add(cliente);
		this.add(c);
		
		JLabel estado = new JLabel("Estado: ");
		e = new JLabel("");
		estado.setBounds(560,272,170,20);
		estado.setFont(FUENTE_SET2);
		e.setBounds(700,272,200,20);
		e.setFont(FUENTE_SET);
		e.setForeground(COLOR_ARRENDAR);
		arrendar = new JButton("Arrendar");
		arrendar.setBackground(COLOR_ARRENDAR);
		arrendar.setForeground(Color.white);
		arrendar.setVisible(false);
		nuevaVentana = new JFrame();
		nuevaVentana.setBounds(790,300,330,170);
        nuevaVentana.setResizable(false);
        nuevaVentana.setLayout(null);
        
        JLabel cli = new JLabel("Cliente:");
        JTextField ingreso = new JTextField();
        JButton cancelar = new JButton("Cancelar");
        JButton ok = new JButton("Ok");
        
        JLabel inicio = new JLabel("Inicio: ");
		i = new JLabel("");
		inicio.setBounds(560,400,100,20);
		inicio.setFont(FUENTE_SET2);
		i.setBounds(700,400,200,20);
		this.add(inicio);
		this.add(i);
        
        cli.setBounds(5,50,70,20);
        cli.setFont(FUENTE_SET2);
        ingreso.setBounds(75,50,130,20);
        cancelar.setBounds(40,95,90,20);
        ok.setBounds(200,95,90,20);
        
        
        
        // Panel para las boletas
        text = new JLabel("Ultimas boletas");
        text.setBounds(5,5,560,10);
		text.setFont(FUENTE);
        listScrollPane = new JScrollPane(listReceipts);
		listScrollPane.setBounds(6, 500, 540, 150);
        
		
		listModel = new DefaultListModel<Receipt>();
        listReceipts = new JList<Receipt>(listModel);
        
        listReceipts.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listReceipts.setLayoutOrientation(JList.VERTICAL_WRAP);
        listReceipts.setVisibleRowCount(-1);
        JScrollPane scrolList = new JScrollPane(listReceipts);
        scrolList.setBounds(6,500, 540, 150);
        
        // En la ventana de arrendar 
        JLabel descItem = new JLabel();
        descItem.setBounds(10,7,150,20);
        descItem.setFont(FUENTE_SET2);
        nuevaVentana.add(descItem);
        nuevaVentana.add(cli);
        nuevaVentana.add(ingreso);
        nuevaVentana.add(cancelar);
        nuevaVentana.add(ok);
        
        // Funcion del boton arrendar
        arrendar.setBounds(820,272,100,20);
		arrendar.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent a){
	              if(b!=null) {
	            	  if(!b.isRented()) { // Si no esta arrendado aparece opcion para arrendar.
	            		  descItem.setText("Arrendar item "+b.getSerial());
						  nuevaVentana.setVisible(true);
					  }else { // Si esta arrendado comienza a estar disponible.              
						  e.setText("disponible");                             // El objeto comenzará a estar disponible
						  e.setForeground(COLOR_ARRENDAR);                     // y se le agrega el color "arrendar".
						  arrendar.setText("disponible");                      // El boton cambia el nombre a "disponible" y
						  arrendar.setBackground(COLOR_ARRENDAR);              // se le cambia el color.
						  Date fechaFinal = new Date();                        // Se crea la variable fechaFinal que muestra la fecha en tiempo real del arriendo
						  b.setEnd(fechaFinal);                                // A "b" se le incorpora la fecha final del arriendo.
						  Receipt ultimoRecibo = b.returnMe(b.getEnd());       // Se crea una boleta a partir de la fecha final.
						  listModel.add(0, ultimoRecibo);                      // El recibo se agrega a una lista de recibos.
						  b.setStart(null);                                    // El tiempo de inicio comienza a ser null.
						  b.setClientName(null);                               // El nombre del cliente comienza a ser null.
						  showDetails(b);                                      // Se llama a "showDetails" para mostrar los atributos actualizados del objeto.
						  revalidate();                                        // Refresco de pantalla.
						  repaint();                                           // Refresco de pantalla.
					  }
	              }
	            }
		});
		// Funcion del boton "ok" al momento de arrendar
		ok.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent a){
				 if(b!=null) {
					 b.setClientName(ingreso.getText());      // A "b" se le agrega el nombre del cliente.
					 c.setText(b.getClientName());            // Se muestra en pantalla.
					 Date FE = new Date();                    // Se crea la variable FE para que muestra la fecha en tiempo real del arriendo.
					 b.setStart(FE);                          // A "b" se le incorpora la fecha de inicio.
					 i.setText(FECHA.format(b.getStart()));   // Se muestra en pantalla la fecha y hora de inicio del objeto arrendado.
					 nuevaVentana.setVisible(false);          // Para cuando se clickee el boton de finalizar, no se mostrará la ventana de nombre del cliente.
					 e.setText("arrendado");                  // El objeto comienza a estar arrendado.
					 e.setForeground(COLOR_FINALIZAR);        // Se le agrega el color finalizar al label "arrendado"
					 arrendar.setText("Finalizar");           // El botón "arrendar" cambia de nombre a "finalizar"
					 arrendar.setBackground(COLOR_FINALIZAR); // y se cambia a color rojo.
					 ingreso.setText(null);                   // Se limpia el JTextField para poner el nombre del cliente.
					 revalidate();                            // Refresco de pantalla.
					 repaint();                               // Refresco de pantalla.
				 }
			 }
			 
		});
		
		// Funcion del boton "cancelar" al momento de arrendar
		// se cerrará la ventana para arrendar el objeto.
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				   nuevaVentana.dispatchEvent(new WindowEvent(nuevaVentana, WindowEvent.WINDOW_CLOSING));
				   revalidate();
				   repaint();
			}
		});
		
		
		salir = new JButton("Salir");
		salir.setFont(FUENTE2);
		salir.setBounds(775,600,150,45);
		
		// Funcion del boton salir se mostrará un
		// aviso si realmente quiere salir del programa.
		salir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              
                int exit = JOptionPane.showConfirmDialog( null, "Está seguro/a que desea terminar el programa?",
                        "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                if(exit == 0) {
                    System.exit(0);
                }
            }
         });
		exportar = new JButton("Exportar boletas");
		exportar.setFont(FUENTE2);
		exportar.setBounds(565,600,200,45);
		
		// Funcion del boton exportar boletas.
		exportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listModel.getSize()!=0) {
					for(int i = 0; i < listModel.getSize(); i++) {
	                    listModel.getElementAt(i).toFile();
	                }
	                JOptionPane.showMessageDialog(null, "La boleta ha sido creada");
				}
				
			}
		});
	
		
	
		p2.add(text);
		
		
		// Agregar objetos a la ventana
		this.add(estado);
		this.add(e);
		this.add(arrendar);
		this.add(scrolList);
		this.add(listScrollPane);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(exportar);
		this.add(salir);
		loadRentalItems(filePath);	
		this.setVisible(true);
	}
	
	/**
	 * Este metodo va a leer el archivo linea a linea y llamará al metodo addItem.
	 * 
	 * @param filePath es la ruta del archivo.
	 */
	public void loadRentalItems(String filePath) {
		try{
			BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
			String line = null;
			
			while((line = reader.readLine()) != null){
				addItem(line);
			}
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * addItem va a separar por comas "," y va a almacenar la informacion de la linea, en el cual
	 * tendrá la serie del objeto, la descripcion, el precio base y el precio por hora.
	 * Luego verificará que tipo de botón se va a crear dependiendo la primera letra que tenga
	 * la serie del objeto y cada vez que se precione el botón va a llamar
	 * al método "showDetails" y almacenará el botón en un panel.
	 * 
	 * 
	 * @param lineToParse es la linea leida del archivo.
	 * 
	 */
	public void addItem(String lineToParse){
		String[] tokens = lineToParse.split(",");
 		String serial= tokens[0];
		String desc = tokens[1];
		double baseFee = Double.parseDouble(tokens[2]);
		double hourFee = Double.parseDouble(tokens[3]);
		if(serial.contains("B")) {
			
			BikeButton bici = new BikeButton(serial, desc, baseFee, hourFee);
			bici.setText(serial);
			bici.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					 b = ((RentalItemButton)e.getSource());
					 showDetails(b);
				}
			});
			p1.add(bici);
		}else {
			if(serial.contains("K")) {
				KayakButton kayak = new KayakButton(serial, desc, baseFee, hourFee);
				kayak.setText(serial);
				kayak.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e){
						 b = ((RentalItemButton)e.getSource());
						 showDetails(b);
					}
				});
				p1.add(kayak);
			}
			else {
				SegwayButton seg = new SegwayButton(serial, desc, baseFee, hourFee);
				seg.setText(serial);
				seg.addActionListener(new ActionListener(){ 
					public void actionPerformed(ActionEvent e){
						b = ((RentalItemButton)e.getSource());
						showDetails(b);
					}
				});
				p1.add(seg);
			}
		}
	}
	
	/**
	 * showDetails recibirá a "r" y verificará las siguientes condiciones:
	 * Si no se presionó el botón, el panel no mostrará descripciones, en caso contrario, mostrará los atributos del boton.
	 * Si está arrendado, el texto "disponible" se cambiará a "arrendado" y el botón "arrendar" se cambiará a "finalizar", además
	 * de incorporar la fecha de incio y el nombre del usuario.
	 * Si no está arrendado, el texto objeto pasará de estar "arrendado" a "disponible" y el botón "finalizar" se cambiará a "arrendar",
	 * mostrará los atributos del botón a excepción de que la hora de inicio y el cliente serán textos vacíos.
	 * 
	 * @param r es el botón que se presionó.
	 */
	public void showDetails(RentalItemButton r) {
		if(r!=null) {
			s.setText(r.getSerial());
			d.setText(r.getDesc());
			vb.setText(Double.toString(r.getBaseFee()));
			vh.setText(Double.toString(r.getHourFee()));
			arrendar.setVisible(true);
			e.setText("disponible");
			c.setText(r.getClientName());
		}else {
			s.setText("");
			d.setText("");
			vb.setText("");
			vh.setText("");
			arrendar.setVisible(false);
			e.setText("");
			c.setText("");
		}
		if(r.getStart() != null) {
			i.setText(FECHA.format(r.getStart()));
		}else {
			i.setText("");
		}
		if(r.isRented()) {
			e.setText("arrendado");
			arrendar.setText("Finalizar");
			e.setForeground(COLOR_FINALIZAR);
			arrendar.setBackground(COLOR_FINALIZAR);
			revalidate();
			repaint();
		}else {
			s.setText(r.getSerial());
			d.setText(r.getDesc());
			vb.setText(Double.toString(r.getBaseFee()));
			vh.setText(Double.toString(r.getHourFee()));
			c.setText(r.getClientName());
			e.setText("disponible");
			arrendar.setText("Arrendar");
			e.setForeground(COLOR_ARRENDAR);
			arrendar.setBackground(COLOR_ARRENDAR);
			revalidate();
			repaint();
		}
	}
	
	/**
	 * Comenzará la ejecución del programa entregando la ruta del archivo "rental_items.csv"
	 */
	public static void main(String [] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		j = new RentalApp("Data/rental_items.csv");
	}
}
