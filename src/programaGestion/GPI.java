package programaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.WindowAdapter;

public class GPI extends WindowAdapter
{

	// Creo los frames
	static Frame login = new Frame("Login");
	static Frame menuPrincipal = new Frame("Menú Principal");
	// Creo la barra de menú
	static MenuBar barraMenu = new MenuBar();
	// Creo los menús
	static Menu menuProductos = new Menu("Productos");
	static Menu menuProveedores = new Menu("Proveedores");
	static Menu menuLocales = new Menu("Locales");
	static Menu menuVentas = new Menu("Ventas");
	// Creo los menuItems
	static MenuItem mniProductosAlta = new MenuItem("Alta");
	static MenuItem mniProductosModificacion = new MenuItem("Modificacion");
	static MenuItem mniProductosConsulta = new MenuItem("Consulta");
	static MenuItem mniProveedoresAlta = new MenuItem("Alta");
	static MenuItem mniProveedoresBaja = new MenuItem("Baja");
	static MenuItem mniProveedoresModificacion = new MenuItem("Modificacion");
	static MenuItem mniProveedoresConsulta = new MenuItem("Consulta");
	static MenuItem mniLocalesAlta = new MenuItem("Alta");
	static MenuItem mniLocalesConsulta = new MenuItem("Consulta");
	static MenuItem mniVentasAlta = new MenuItem("Alta");
	static MenuItem mniVentasConsulta = new MenuItem("Consulta");
	// Creo los elementos de la ventana de login
	static Dialog errorLogin = new Dialog(login,"ERROR", true);
	static Label lblUsuario = new Label("Usuario:");
	static Label lblClave = new Label("Clave:");
	static TextField txtUsuario = new TextField(20); 
	static TextField txtClave = new TextField(20);
	static Button btnAceptar = new Button("Aceptar");
	static Button btnLimpiar = new Button("Limpiar");

	//***************************************************************************************************************************************
	//***************************************************************************************************************************************

	//Creo los elementos de Alta Productos
	//static Dialog altaProducto = new Dialog(menuPrincipal,"ALTA DE PROVEEDORES", true);
	//static Label lblAPNombreProducto = new Label("Nombre del proveedor");
	//static Label lblAPPrecioProducto = new Label("Teléfono del proveedor");
	//static Label lblAPStockProducto = new Label("NIF del proveedor");
	//static TextField txtAPNombreProducto = new TextField(20);
	//static TextField txtAPPrecioProducto = new TextField(20);
	//static TextField txtAPStockProducto = new TextField(20);
	//static Button btnAProdAceptar = new Button("Aceptar");
	//static Button btnAProdLimpiar = new Button("Limpiar");	

	//***************************************************************************************************************************************
	//***************************************************************************************************************************************

	// Creo los elementos de Alta de Proveedores
	static Dialog altaProveedor = new Dialog(menuPrincipal,"ALTA DE PROVEEDORES", true);
	static Label lblAPNombreProveedor = new Label("Nombre del proveedor");
	static Label lblAPTelefonoProveedor = new Label("Teléfono del proveedor");
	static Label lblAPNIFProveedor = new Label("NIF del proveedor");
	static TextField txtAPNombreProveedor = new TextField(20);
	static TextField txtAPTelefonoProveedor = new TextField(20);
	static TextField txtAPNIFProveedor = new TextField(20);
	static Button btnAProvAceptar = new Button("Aceptar");
	static Button btnAProvLimpiar = new Button("Limpiar");
	// Creo los elementos de Baja de Proveedores
	static Frame bajaProveedor = new Frame("BAJA DE PROVEEDORES");
	static Dialog bajaProveedorConfirmacion = new Dialog(bajaProveedor,"CONFIRMACION", true);
	static Label lblBajaProveedor = new Label("Elige el proveedor a borrar:");
	static Label lblBajaProveedorConfirmacion = new Label("¿Seguro que quieres borrar éste proveedor?");
	static Choice choBajaProveedor = new Choice();
	static Button btnBProvAceptar = new Button("Aceptar");
	static Button btnBProvConfirmar = new Button("Confirmar");
	static Button btnBProvVolver = new Button("Volver");
	// Creo los elementos de modificacion de Proveedores
	static Frame modificacionProveedor = new Frame("MODIFICACION DE PROVEEDORES");
	static Dialog modificacionProveedorConfirmacion = new Dialog(modificacionProveedor,"MODIFICACION", true);
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
	// Creo los elementos de consulta de Proveedores
	static Frame consultaProveedor = new Frame("CONSULTA DE PROVEEDORES");
	static Label lblConsultaProveedor = new Label("Éstos son los proveedores activos en la actualidad");
	static Choice choConsultaProveedor = new Choice();
	static TextArea taConsultaProveedor = new TextArea(10,60);

	//***************************************************************************************************************************************
	//***************************************************************************************************************************************


}
