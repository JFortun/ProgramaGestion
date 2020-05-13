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
			int idProdElegido = Integer.parseInt(GPI.choModificacionProducto.getSelectedItem().split("-")[0]);
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM productos WHERE idProducto="+idProdElegido;
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			GPBD.rs.next();
			//Le doy a los text field los valores extraidos del elemento seleccionado
			nombreProducto = GPBD.rs.getString("nombreProducto");
			GPI.txtMPNombreProducto.setText(nombreProducto);
			precioProducto = GPBD.rs.getString("precioProducto");
			GPI.txtMPPrecioProducto.setText(precioProducto);
			stockProducto = GPBD.rs.getString("stockProducto");
			GPI.txtMPStockProducto.setText(stockProducto);
			descripcionProducto = GPBD.rs.getString("descripcionProducto");
			GPI.taMPDescripcionProducto.setText(descripcionProducto);

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
			try
			{
				if(GPBD.connection!=null)
				{
					GPBD.connection.close();
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
			GPI.choMPProveedorProducto.removeAll();
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM proveedores";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);

			while(GPBD.rs.next())
			{
				String poblarChoice = Integer.toString(GPBD.rs.getInt("idProveedor"));
				poblarChoice = poblarChoice + "-"+ GPBD.rs.getString("nombreProveedor");
				GPI.choMPProveedorProducto.add(poblarChoice);
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
				if(GPBD.connection!=null)
				{
					GPBD.connection.close();
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error 3-"+e.getMessage());
			}
		}
		GPI.modificacionProductoConfirmacion.setLayout(new FlowLayout());
		GPI.modificacionProductoConfirmacion.setSize(325, 300);
		GPI.modificacionProductoConfirmacion.setResizable(false);
		GPI.modificacionProductoConfirmacion.addWindowListener(this);
		GPI.btnMProdConfirmar.addActionListener(this);
		GPI.btnMProdVolver.addActionListener(this);
		GPI.modificacionProductoConfirmacion.add(GPI.lblMPNombreProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.txtMPNombreProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.lblMPPrecioProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.txtMPPrecioProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.lblMPStockProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.txtMPStockProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.lblMPDescripcionProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.taMPDescripcionProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.lblMPProveedorProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.choMPProveedorProducto);
		GPI.modificacionProductoConfirmacion.add(GPI.btnMProdConfirmar);
		GPI.modificacionProductoConfirmacion.add(GPI.btnMProdVolver);
		GPI.modificacionProductoConfirmacion.setLocationRelativeTo(null);
		GPI.modificacionProductoConfirmacion.setVisible(true);
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.modificacionProductoConfirmacion.isActive())
		{
			GPI.modificacionProductoConfirmacion.setVisible(false);
		}
	}

	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(GPI.btnMProdConfirmar))
		{
			try
			{
				int idProdElegido = Integer.parseInt(GPI.choModificacionProducto.getSelectedItem().split("-")[0]);
				String[] PSeleccionado=GPI.choMPProveedorProducto.getSelectedItem().split("-");
				String proveedorProducto = PSeleccionado[0];
				String nombre = GPI.txtMPNombreProducto.getText();
				String precio = GPI.txtMPPrecioProducto.getText();
				String stock = GPI.txtMPStockProducto.getText();
				String descripcion = GPI.taMPDescripcionProducto.getText();

				GPBD.ConexionBD();
				GPBD.sentencia = "UPDATE productos SET nombreProducto = '"+nombre+"', descripcionProducto = '"+descripcion+"', stockProducto = "+stock+", precioProducto = "+precio+", idProveedorFK = "+proveedorProducto+" WHERE idProducto="+idProdElegido;
				GPBD.statement.executeUpdate(GPBD.sentencia);
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
					if(GPBD.connection!=null)
					{
						GPBD.connection.close();
					}
				}
				catch (SQLException e)
				{
					System.out.println("Error 3-"+e.getMessage());
				}
			}		
		}
		else if(evento.getSource().equals(GPI.btnMProdVolver))
		{
			GPI.modificacionProductoConfirmacion.setVisible(false);
		}

	}
}
