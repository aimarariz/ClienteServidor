package clienteServidor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// creacion de la clase Servidor
public class Servidor extends JFrame{
	
	// creacionde los metodos
	private JTextField campoIntroducir;
	private JTextArea areaPantalla;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private ServerSocket servidor;
	private Socket conexion;
	
	private int contador = 1;
	
	// configuracion de la GUI
	public Servidor() {
		
		super("Servidor");
		
		Container contenedor = getContentPane();
		
		// crear campoIntroducir y registrar componente  de escucha
		campoIntroducir = new JTextField();
		campoIntroducir.setEditable(false);
		campoIntroducir.addActionListener(new ActionListener() {
			
			// enviar mensaje al cliente
			@Override
			public void actionPerformed(ActionEvent e) {
				
				enviarDatos(e.getActionCommand());
				campoIntroducir.setText("");
				
				
			}
		});
		contenedor.add(campoIntroducir, BorderLayout.NORTH);
		
		// crear areaPantalla
		areaPantalla = new JTextArea();
		contenedor.add(new JScrollPane(areaPantalla), BorderLayout.CENTER);
		
		setSize(300,150);
		setVisible(true);
		
	 }// fin de la construccion de la clase Servidor
	
		
	}
	
	


