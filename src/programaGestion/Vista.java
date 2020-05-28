package programaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Vista extends WindowAdapter
{

	// Creo los frames
	static JFrame login = new JFrame("Login");
	static JFrame menuPrincipal = new JFrame("Menú Principal");
	// Creo la barra de menú
	static JMenuBar barraMenu = new JMenuBar();
	// Creo los menús
	static JMenu menuProductos = new JMenu("Productos");
	static JMenu menuProveedores = new JMenu("Proveedores");
	static JMenu menuLocales = new JMenu("Locales");
	static JMenu menuVentas = new JMenu("Ventas");
	static JMenu menuAyuda = new JMenu("Ayuda");
	// Creo los menuItems
	static JMenuItem mniProductosAlta = new JMenuItem("Alta");
	static JMenuItem mniProductosModificacion = new JMenuItem("Modificacion");
	static JMenuItem mniProductosConsulta = new JMenuItem("Consulta");
	static JMenuItem mniProveedoresAlta = new JMenuItem("Alta");
	static JMenuItem mniProveedoresBaja = new JMenuItem("Baja");
	static JMenuItem mniProveedoresModificacion = new JMenuItem("Modificacion");
	static JMenuItem mniProveedoresConsulta = new JMenuItem("Consulta");
	static JMenuItem mniLocalesAlta = new JMenuItem("Alta");
	static JMenuItem mniLocalesConsulta = new JMenuItem("Consulta");
	static JMenuItem mniVentasAlta = new JMenuItem("Alta");
	static JMenuItem mniVentasConsulta = new JMenuItem("Consulta");
	static JMenuItem mniAyuda = new JMenuItem("Ayuda");
	// Creo los elementos de la ventana de login
	static JDialog errorLogin = new JDialog(login,"ERROR", true);
	static JLabel lblUsuario = new JLabel("Usuario:");
	static JLabel lblClave = new JLabel("Clave:");
	static JTextField txtUsuario = new JTextField(20); 
	static JPasswordField txtClave = new JPasswordField(20);
	static JButton btnAceptar = new JButton("Aceptar");
	static JButton btnLimpiar = new JButton("Limpiar");
	static Button btnELVolver = new Button("Volver");
	static JLabel lblErrorLogin = new JLabel("Credenciales incorrectas");

	//***************************************************************************************************************************************
	//***************************************************************************************************************************************

	//Creo los elementos de alta productos
	static Dialog altaProducto = new Dialog(menuPrincipal,"ALTA DE PRODUCTOS", true);
	static Label lblAPNombreProducto = new Label("Nombre del producto");
	static Label lblAPPrecioProducto = new Label("Precio del producto");
	static Label lblAPStockProducto = new Label("Stock del producto");
	static Label lblAPDescripcionProducto = new Label("Descripción producto");
	static Label lblAPProveedorProducto = new Label("Proveedor del producto");
	static TextField txtAPNombreProducto = new TextField(20);
	static TextField txtAPPrecioProducto = new TextField(20);
	static TextField txtAPStockProducto = new TextField(20);
	static TextArea taAPDescripcionProducto = new TextArea(5,20);
	static Choice choAPProveedorProducto = new Choice();
	static Button btnAProdAceptar = new Button("Aceptar");
	static Button btnAProdLimpiar = new Button("Limpiar");
	// Creo los elementos de modificacion de productos
	static Frame modificacionProducto = new Frame("MODIFICACION DE PRODUCTOS");
	static Dialog modificacionProductoConfirmacion = new Dialog(modificacionProducto,"MODIFICACION", true);
	static Label lblMPNombreProducto = new Label("Nombre del producto");
	static Label lblMPPrecioProducto = new Label("Precio del producto");
	static Label lblMPStockProducto = new Label("Stock del producto");
	static Label lblMPDescripcionProducto = new Label("Descripción producto");
	static Label lblMPProveedorProducto = new Label("Proveedor del producto");
	static TextField txtMPNombreProducto = new TextField(20);
	static TextField txtMPPrecioProducto = new TextField(20);
	static TextField txtMPStockProducto = new TextField(20);
	static TextArea taMPDescripcionProducto = new TextArea(5,20);
	static Choice choMPProveedorProducto = new Choice();
	static Choice choModificacionProducto = new Choice();
	static Button btnMProdConfirmar = new Button("Confirmar");
	static Button btnMProdVolver = new Button("Volver");
	static Label lblModificacionProducto = new Label("Modifica los datos del proveedor que se requiera:");
	static Button btnMProdAceptar = new Button("Aceptar");
	// Creo los elementos de consulta de productos
	static Frame consultaProducto = new Frame("CONSULTA DE PRODUCTOS");
	static Label lblConsultaProducto = new Label("Éstos son los productos disponibles en la actualidad");
	static Choice choConsultaProducto = new Choice();
	static TextArea taConsultaProducto = new TextArea(10,60);
	static Button btnCProdExportarPDF = new Button("Exportar a PDF");

	//***************************************************************************************************************************************
	//***************************************************************************************************************************************

	// Creo los elementos de alta de proveedores
	static Dialog altaProveedor = new Dialog(menuPrincipal,"ALTA DE PROVEEDORES", true);
	static Label lblAPNombreProveedor = new Label("Nombre del proveedor");
	static Label lblAPTelefonoProveedor = new Label("Teléfono del proveedor");
	static Label lblAPNIFProveedor = new Label("NIF del proveedor");
	static TextField txtAPNombreProveedor = new TextField(20);
	static TextField txtAPTelefonoProveedor = new TextField(20);
	static TextField txtAPNIFProveedor = new TextField(20);
	static Button btnAProvAceptar = new Button("Aceptar");
	static Button btnAProvLimpiar = new Button("Limpiar");
	// Creo los elementos de baja de proveedores
	static Frame bajaProveedor = new Frame("BAJA DE PROVEEDORES");
	static Dialog bajaProveedorConfirmacion = new Dialog(bajaProveedor,"CONFIRMACION", true);
	static Label lblBajaProveedor = new Label("Elige el proveedor a borrar:");
	static Label lblBajaProveedorConfirmacion = new Label("¿Seguro que quieres borrar éste proveedor?");
	static Choice choBajaProveedor = new Choice();
	static Button btnBProvAceptar = new Button("Aceptar");
	static Button btnBProvConfirmar = new Button("Confirmar");
	static Button btnBProvVolver = new Button("Volver");
	// Creo los elementos de modificacion de proveedores
	static Frame modificacionProveedor = new Frame("MODIFICACION DE PROVEEDORES");
	static Dialog modificacionProveedorConfirmacion = new Dialog(modificacionProducto,"MODIFICACION", true);
	static Label lblMPNombreProveedor = new Label("Nombre del proveedor");
	static Label lblMPTelefonoProveedor = new Label("Teléfono del proveedor");
	static Label lblMPNIFProveedor = new Label("NIF del proveedor");
	static TextField txtMPNombreProveedor = new TextField(20);
	static TextField txtMPTelefonoProveedor = new TextField(20);
	static TextField txtMPNIFProveedor = new TextField(20);
	static Choice choModificacionProveedor = new Choice();
	static Button btnMProvConfirmar = new Button("Confirmar");
	static Button btnMProvVolver = new Button("Volver");
	static Label lblModificacionProveedor = new Label("Modifica los datos del proveedor que se requiera:");
	static Button btnMProvAceptar = new Button("Aceptar");
	// Creo los elementos de consulta de proveedores
	static Frame consultaProveedor = new Frame("CONSULTA DE PROVEEDORES");
	static Label lblConsultaProveedor = new Label("Éstos son los proveedores activos en la actualidad");
	static Choice choConsultaProveedor = new Choice();
	static TextArea taConsultaProveedor = new TextArea(10,60);
	static Button btnCProvExportarPDF = new Button("Exportar a PDF");

	//***************************************************************************************************************************************
	//***************************************************************************************************************************************

	// Creo los elementos de alta de locales
	static Frame altaLocal = new Frame("ALTA DE LOCALES");
	static Label lblALDireccionLocal = new Label("Dirección del local");
	static TextField txtALDireccionLocal = new TextField(20);
	static Button btnALAceptar = new Button("Aceptar");
	static Button btnALLimpiar = new Button("Limpiar");
	// Creo los elementos de consulta de locales
	static Frame consultaLocal = new Frame("CONSULTA DE LOCALES");
	static Label lblConsultaLocal = new Label("Éstos son los locales activos en la actualidad");
	static Choice choConsultaLocal = new Choice();
	static TextArea taConsultaLocal = new TextArea(10,60);
	static Button btnCLExportarPDF = new Button("Exportar a PDF");
	
	//***************************************************************************************************************************************
	//***************************************************************************************************************************************

	// Creo los elementos de alta de ventas
	static Frame altaVenta = new Frame("ALTA DE VENTAS");
	static Label lblAVLocalVenta = new Label("Selecciona un local");
	static Label lblAVProductoVenta = new Label("Selecciona un producto");
	static Label lblAVFechaVenta = new Label("Indica una fecha");
	static Choice choAVLocalVenta = new Choice();
	static Choice choAVProductoVenta = new Choice();
	static TextField txtAVFechaVenta = new TextField(20);
	static Button btnAVAceptar = new Button("Aceptar");
	static Button btnAVLimpiar = new Button("Limpiar");
	// Creo los elementos de consulta de ventas
	static Frame consultaVenta = new Frame("CONSULTA DE VENTAS");
	static Label lblConsultaVenta = new Label("Éstos son las ventas realizadas");
	static Choice choConsultaVenta = new Choice();
	static TextArea taConsultaVenta = new TextArea(10,60);
	static Button btnCVExportarPDF = new Button("Exportar a PDF");
}
