package clienteServidor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
		
	// confijurar y ejecutar el servidor
	public void ejecutarServidor() {
		
		// configurar servidor para que reciba conexiones; pocesar la conexiones 
		
		try {
			servidor = new ServerSocket(12345, 100); // paso 1: crear un objeto ServerSocket
			
			while(true) {
				
				try {
					
					esperarConexion(); // paso 2: esperar una conexion
					obtenerFlujos(); // paso 3: obtener flujos de entrada y salida
					procesarConexiones();// paso 4: procesar la conexion
				}
				
				// procesar exe¡cepcion EOFException cuando el cliente cierre la conexion
				catch (EOEFxception excepcionEOF) {
					System.err.println("El servidor termino la conexion");
				}
				
				finally {
					cerrarCoenxion(); // paso 5: cerrar la conexion
					contador = contador + 1;
				}
			} // fin de la intruccion while
		} // fin de bloque try
		
		// procesar problemas con E/S
		catch(IOEexeption excepcionES) {
			excepcionES.printStrackTrace();
		}
		
	} // fin del metodo ejecutarServidor
	
	// esperar que la conexion llegue, despues mostrar informacion de la conexion
	private void esperarConexion() throws IOException{
		
		mostrarMensaje("Esperando una conexion\n");
		conexion = servidor.accept();
		mostrarMensaje("Conexio "+ contador + " recibir de: " + conexion.getInetAddress().getHostName());
	}
	
	// obtener flujos para enviar y recibir datos private void obtenerFlujos() throws IOExpected
	private void obtenerFlujos() throws IOException{
		
		// establecer flujo de salida para los objetos
				salida.flush();
				
				// establecer flujo de entrada para los objetos
				entrada = new ObjectInputStream(conexion.getInputStream());
				mostrarMensaje("\nSe recibieron los flujos de E/S\n");
			}
	
	// procesar la informacion con el cliente
	private void procesarConexion() throws IOException{
		
		// enviar mensaje de conexion exitosa al cliente
		String mensaje = "Conexion exitosa";
		enviarDatos(mensaje);
		
		// habilitar campoIntroducir para que el usuario del servidor pueda enviar mensajes
		establecerCampoTextoEditable(true);
		
		do {
			try {
				mensaje = (String) entrada.readObject();
				mostrarMensaje( "\n" + mensaje);
			}
			
			catch(ClassNotFoundException exceptionClaseNoEncontrada) {
				mostrarMensaje("\nSe recibio un tipo de objeto desconocido");
			}
			
		}while(!mensaje.equals("CLIENTE>>>> TERMINAR"));
	} // fin del metodo procesarConexion
	
		
	
		
}
	

	
	


