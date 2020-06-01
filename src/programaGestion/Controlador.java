package programaGestion;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.JFrame;

public class Controlador extends JFrame implements ActionListener, WindowListener
{
	private static final long serialVersionUID = 1L;
	
	static int tipoUsuario = 0;
	
	Color colorLogin = new Color(204, 230, 255);
	Color colorMenu = new Color(153, 255, 204);
	
	
	Controlador()
	{
		Color colorLogin = new Color(204, 230, 255);
		Vista.login.setLayout(new FlowLayout());
		Vista.login.setSize(315, 130);
		Vista.login.setResizable(false);
		Vista.login.setLocationRelativeTo(null);
		Vista.login.addWindowListener(this);
		Vista.btnAceptar.addActionListener(this);
		Vista.btnLimpiar.addActionListener(this);
		Vista.login.add(Vista.lblUsuario);
		Vista.login.add(Vista.txtUsuario);
		Vista.login.add(Vista.lblClave);
		Vista.txtClave.setEchoChar('*');
		Vista.login.add(Vista.txtClave);
		Vista.login.add(Vista.btnAceptar);
		Vista.login.add(Vista.btnLimpiar);
		Vista.login.setVisible(true);
		Vista.login.getContentPane().setBackground(colorLogin);

	}

	// Declaro los eventos que suceden en el login
	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource().equals(Vista.btnLimpiar)) 
		{
			Vista.txtUsuario.selectAll();
			Vista.txtUsuario.setText("");
			Vista.txtClave.selectAll();
			Vista.txtClave.setText("");
			Vista.txtUsuario.requestFocus();
		}

		else if(evento.getSource().equals(Vista.btnAceptar)) 
		{
			char[] claveExtraida = Vista.txtClave.getPassword();
			String clave = new String(claveExtraida);
			String cadenaEncriptada = Encriptacion.getSHA256(clave);
			Modelo.sentencia = "SELECT * FROM usuarios WHERE nombreUsuario = '"+ Vista.txtUsuario.getText()+ "'AND claveUsuario = '"+ cadenaEncriptada+"'";

			try
			{
				Modelo.ConexionBD();
				//Crear un objeto ResultSet para guardar lo obtenido
				//y ejecutar la sentencia SQL
				Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
				if(Modelo.rs.next())

				{
					// Para saber si el usuario es administrador o usuario básico
					Modelo.sentencia = "SELECT tipoUsuario FROM usuarios WHERE nombreUsuario = '" + Vista.txtUsuario.getText()+"'";
					Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
					Modelo.rs.next();
					tipoUsuario = Modelo.rs.getInt("tipoUsuario");
					if (tipoUsuario == 1)
					{
						Vista.menuPrincipal.setLayout(new FlowLayout());
						Vista.menuPrincipal.setSize(800, 400);
						Vista.menuPrincipal.setResizable(false);
						Vista.menuPrincipal.addWindowListener(this);
						Vista.menuPrincipal.setLocationRelativeTo(null);
						Vista.menuPrincipal.setVisible(true);
						Vista.menuPrincipal.setJMenuBar(Vista.barraMenu);
						// Añado el menu Productos
						Vista.menuProductos.add(Vista.mniProductosAlta);
						Vista.mniProductosAlta.addActionListener(this);
						Vista.menuProductos.add(Vista.mniProductosModificacion);
						Vista.mniProductosModificacion.addActionListener(this);
						Vista.menuProductos.add(Vista.mniProductosConsulta);
						Vista.mniProductosConsulta.addActionListener(this);
						// Añado el menu Proveedores
						Vista.menuProveedores.add(Vista.mniProveedoresAlta);
						Vista.mniProveedoresAlta.addActionListener(this);
						Vista.menuProveedores.add(Vista.mniProveedoresBaja);
						Vista.mniProveedoresBaja.addActionListener(this);
						Vista.menuProveedores.add(Vista.mniProveedoresModificacion);
						Vista.mniProveedoresModificacion.addActionListener(this);
						Vista.menuProveedores.add(Vista.mniProveedoresConsulta);
						Vista.mniProveedoresConsulta.addActionListener(this);
						// Añado el menu Locales
						Vista.menuLocales.add(Vista.mniLocalesAlta);
						Vista.mniLocalesAlta.addActionListener(this);
						Vista.menuLocales.add(Vista.mniLocalesConsulta);
						Vista.mniLocalesConsulta.addActionListener(this);
						// Añado el menu Ventas
						Vista.menuVentas.add(Vista.mniVentasAlta);
						Vista.mniVentasAlta.addActionListener(this);
						Vista.menuVentas.add(Vista.mniVentasConsulta);
						Vista.mniVentasConsulta.addActionListener(this);
						// Añado el menu Ayuda
						Vista.menuAyuda.add(Vista.mniAyuda);
						Vista.mniAyuda.addActionListener(this);
						// Asigno el menú a la barrra de menú
						Vista.barraMenu.add(Vista.menuProductos);
						Vista.barraMenu.add(Vista.menuProveedores);
						Vista.barraMenu.add(Vista.menuLocales);
						Vista.barraMenu.add(Vista.menuVentas);
						Vista.barraMenu.add(Vista.menuAyuda);
						// Pongo invisible el login
						Vista.login.setVisible(false);
						Vista.menuPrincipal.getContentPane().setBackground(colorMenu);

					}
					else if (tipoUsuario == 2)
					{
						Vista.menuPrincipal.setLayout(new FlowLayout());
						Vista.menuPrincipal.setSize(800, 400);
						Vista.menuPrincipal.setResizable(false);
						Vista.menuPrincipal.addWindowListener(this);
						Vista.menuPrincipal.setLocationRelativeTo(null);
						Vista.menuPrincipal.setVisible(true);
						Vista.menuPrincipal.setJMenuBar(Vista.barraMenu);
						// Añado el menu Productos
						Vista.menuProductos.add(Vista.mniProductosAlta);
						Vista.mniProductosAlta.addActionListener(this);
						// Añado el menu Proveedores
						Vista.menuProveedores.add(Vista.mniProveedoresAlta);
						Vista.mniProveedoresAlta.addActionListener(this);
						// Añado el menu Locales
						Vista.menuLocales.add(Vista.mniLocalesAlta);
						Vista.mniLocalesAlta.addActionListener(this);
						// Añado el menu Ventas
						Vista.menuVentas.add(Vista.mniVentasAlta);
						Vista.mniVentasAlta.addActionListener(this);
						// Añado el menu Ayuda
						Vista.menuAyuda.add(Vista.mniAyuda);
						Vista.mniAyuda.addActionListener(this);
						// Asigno el menú a la barrra de menú
						Vista.barraMenu.add(Vista.menuProductos);
						Vista.barraMenu.add(Vista.menuProveedores);
						Vista.barraMenu.add(Vista.menuLocales);
						Vista.barraMenu.add(Vista.menuVentas);
						Vista.barraMenu.add(Vista.menuAyuda);
						// Pongo invisible el login
						Vista.login.setVisible(false);
						Vista.menuPrincipal.getContentPane().setBackground(colorMenu);
					}
				}

				else
				{
					Vista.errorLogin.setLayout(new FlowLayout());
					Vista.errorLogin.setSize(200, 100);
					Vista.errorLogin.setResizable(false);
					Vista.errorLogin.addWindowListener(this);
					Vista.errorLogin.add(Vista.lblErrorLogin);
					Vista.btnELVolver.addActionListener(this);
					Vista.errorLogin.add(Vista.btnELVolver);
					Vista.errorLogin.setLocationRelativeTo(null);
					Vista.errorLogin.setVisible(true);

				}
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}

			finally
			{
				Log.registrarLog("Conexión de usuario realizada");
				try
				{
					if(Modelo.connection!=null)
					{
						Modelo.connection.close();
					}
				}
				catch (SQLException e)
				{
					System.out.println("Error 3-"+e.getMessage());
				}
			}
		}

		// Alta de productos
		else if(evento.getSource().equals(Vista.mniProductosAlta)) 
		{
			new AltaProductos();
		}
		// Modificacion de productos
		else if(evento.getSource().equals(Vista.mniProductosModificacion)) 
		{
			new ModificacionProductos();
		}
		// Consulta de productos;
		else if(evento.getSource().equals(Vista.mniProductosConsulta)) 
		{
			new ConsultaProductos();
		}
		// Alta de proveedores
		else if(evento.getSource().equals(Vista.mniProveedoresAlta)) 
		{
			new AltaProveedores();
		}
		// Baja de proveedores
		else if(evento.getSource().equals(Vista.mniProveedoresBaja)) 
		{
			new BajaProveedores(); 
		}
		// Modificacion de proveedores
		else if(evento.getSource().equals(Vista.mniProveedoresModificacion)) 
		{
			new ModificacionProveedores();
		}
		// Consulta de proveedores;
		else if(evento.getSource().equals(Vista.mniProveedoresConsulta)) 
		{
			new ConsultaProveedores();
		}	
		// Alta de locales
		else if(evento.getSource().equals(Vista.mniLocalesAlta)) 
		{
			new AltaLocales();
		}
		// Consulta de locales;
		else if(evento.getSource().equals(Vista.mniLocalesConsulta)) 
		{
			new ConsultaLocales();
		}
		// Alta de ventas
		else if(evento.getSource().equals(Vista.mniVentasAlta)) 
		{
			new AltaVentas();
		}
		// Consulta de ventas;
		else if(evento.getSource().equals(Vista.mniVentasConsulta)) 
		{
			new ConsultaVentas();
		}
		// Botón de ayuda;
		else if(evento.getSource().equals(Vista.mniAyuda)) 
		{
			new Ayuda();
		}
		else
		{
			// Tareas del Volver
			Vista.errorLogin.setVisible(false);
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.errorLogin.isActive())
		{
			Vista.errorLogin.setVisible(false);
		}
		else
		{
			Log.registrarLog("Salida del programa");
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
}