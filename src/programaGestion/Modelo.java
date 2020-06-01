package programaGestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {

	// Conexión a la base de datos
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/programaGestion?useSSL=false";
	static String usuario = "root";
	static String clave = "15935784265";
	public static String sentencia = "";
	public static Connection connection = null;
	public static Statement statement = null;
	static ResultSet rs = null;

	public static void ConexionBD() {
		try
		{
			//Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			//Establecer la conexión con la BD
			connection = DriverManager.getConnection(url, usuario, clave);
			//Crear una sentencia
			statement = connection.createStatement();

		}

		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}

		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}

		finally
		{
			Log.registrarLog("Conexión a la base de datos realizada");
		}
	}

}

//***************************************************************************************************************************************
//***************************************************************************************************************************************

/*	Código SQL para crear la base de datos
create database programaGestion charset utf8mb4 collate utf8mb4_spanish2_ci;
use programaGestion;
create table locales (
idLocal int auto_increment,
direccionLocal varchar(45),
primary key(idLocal)
);
create table proveedores (
idProveedor int auto_increment,
nombreProveedor varchar(45),
telefonoProveedor int(9),
nifProveedor varchar(9),
primary key(idProveedor)
);
create table productos (
idProducto int auto_increment,
nombreProducto varchar(45),
descripcionProducto longtext,
stockProducto int,
precioProducto decimal(10,2),
idProveedorFK int,
primary key(idProducto),
foreign key(idProveedorFK) references proveedores (idProveedor)
);
create table venden (
idLocalFK int,
idProductoFK int,
fechaVenta date,
primary key(idLocalFK, idProductoFK),
foreign key(idLocalFK) references locales (idLocal),
foreign key(idProductoFK) references productos (idProducto)
);
create table usuarios (
idUsuario int auto_increment,
nombreUsuario varchar(45),
tipoUsuario tinyint,
claveUsuario varchar(256),
primary key(idUsuario)
);

SHA256:
	administrador: B20B0F63CE2ED361E8845D6BF2E59811AAA06EC96BCDB92F9BC0C5A25E83C9A6
	usuario: 9250E222C4C71F0C58D4C54B50A880A312E9F9FED55D5C3AA0B0E860DED99165
 */