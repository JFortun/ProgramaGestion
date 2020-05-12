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

	//***************************************************************************************************************************************
	//***************************************************************************************************************************************


}
