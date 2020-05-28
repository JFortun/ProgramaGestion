package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ObtenerDatosModProd extends WindowAdapter implements ActionListener
{

	ObtenerDatosModProd()
	{

		String nombreProducto = "";
		String precioProducto = "";
		String stockProducto = "";
		String descripcionProducto = "";


		try
		{
			int idProdElegido = Integer.parseInt(Vista.choModificacionProducto.getSelectedItem().split("-")[0]);
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM productos WHERE idProducto="+idProdElegido;
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
			Modelo.rs.next();
			//Le doy a los text field los valores extraidos del elemento seleccionado
			nombreProducto = Modelo.rs.getString("nombreProducto");
			Vista.txtMPNombreProducto.setText(nombreProducto);
			precioProducto = Modelo.rs.getString("precioProducto");
			Vista.txtMPPrecioProducto.setText(precioProducto);
			stockProducto = Modelo.rs.getString("stockProducto");
			Vista.txtMPStockProducto.setText(stockProducto);
			descripcionProducto = Modelo.rs.getString("descripcionProducto");
			Vista.taMPDescripcionProducto.setText(descripcionProducto);

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
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
		
		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{	
			Vista.choMPProveedorProducto.removeAll();
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM proveedores";
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);

			while(Modelo.rs.next())
			{
				String poblarChoice = Integer.toString(Modelo.rs.getInt("idProveedor"));
				poblarChoice = poblarChoice + "-"+ Modelo.rs.getString("nombreProveedor");
				Vista.choMPProveedorProducto.add(poblarChoice);
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
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
		Vista.modificacionProductoConfirmacion.setLayout(new FlowLayout());
		Vista.modificacionProductoConfirmacion.setSize(325, 300);
		Vista.modificacionProductoConfirmacion.setResizable(false);
		Vista.modificacionProductoConfirmacion.addWindowListener(this);
		Vista.btnMProdConfirmar.addActionListener(this);
		Vista.btnMProdVolver.addActionListener(this);
		Vista.modificacionProductoConfirmacion.add(Vista.lblMPNombreProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.txtMPNombreProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.lblMPPrecioProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.txtMPPrecioProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.lblMPStockProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.txtMPStockProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.lblMPDescripcionProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.taMPDescripcionProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.lblMPProveedorProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.choMPProveedorProducto);
		Vista.modificacionProductoConfirmacion.add(Vista.btnMProdConfirmar);
		Vista.modificacionProductoConfirmacion.add(Vista.btnMProdVolver);
		Vista.modificacionProductoConfirmacion.setLocationRelativeTo(null);
		Vista.modificacionProductoConfirmacion.setVisible(true);
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.modificacionProductoConfirmacion.isActive())
		{
			Vista.modificacionProductoConfirmacion.setVisible(false);
		}
	}

	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(Vista.btnMProdConfirmar))
		{
			try
			{
				int idProdElegido = Integer.parseInt(Vista.choModificacionProducto.getSelectedItem().split("-")[0]);
				String[] PSeleccionado=Vista.choMPProveedorProducto.getSelectedItem().split("-");
				String proveedorProducto = PSeleccionado[0];
				String nombre = Vista.txtMPNombreProducto.getText();
				String precio = Vista.txtMPPrecioProducto.getText();
				String stock = Vista.txtMPStockProducto.getText();
				String descripcion = Vista.taMPDescripcionProducto.getText();

				Modelo.ConexionBD();
				Modelo.sentencia = "UPDATE productos SET nombreProducto = '"+nombre+"', descripcionProducto = '"+descripcion+"', stockProducto = "+stock+", precioProducto = "+precio+", idProveedorFK = "+proveedorProducto+" WHERE idProducto="+idProdElegido;
				Modelo.statement.executeUpdate(Modelo.sentencia);
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
			finally
			{
				Log.registrarLog("Modificación de producto realizada");
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
		else if(evento.getSource().equals(Vista.btnMProdVolver))
		{
			Vista.modificacionProductoConfirmacion.setVisible(false);
		}

	}
}
