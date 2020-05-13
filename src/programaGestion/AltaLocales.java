package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AltaLocales extends WindowAdapter implements ActionListener
{

	AltaLocales()
	{
		GPI.altaProveedor.setLayout(new FlowLayout());
		GPI.altaProveedor.setSize(350, 130);
		GPI.altaProveedor.setResizable(false);
		GPI.altaProveedor.addWindowListener(this);
		GPI.btnALAceptar.addActionListener(this);
		GPI.btnALLimpiar.addActionListener(this);
		GPI.altaProveedor.add(GPI.lblALDireccionLocal);
		GPI.altaProveedor.add(GPI.txtALDireccionLocal);
		GPI.altaProveedor.add(GPI.btnALAceptar);
		GPI.altaProveedor.add(GPI.btnALLimpiar);
		GPI.altaProveedor.setLocationRelativeTo(null);
		GPI.altaProveedor.setVisible(true);
		GPI.txtALDireccionLocal.selectAll();
		GPI.txtALDireccionLocal.setText("");
	}
	
	public void windowClosing(WindowEvent arg0) //Averiguar porqué no funciona
	{
		if(GPI.altaLocal.isActive())
		{
			GPI.altaLocal.setVisible(false);
		}
	}
	
	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(GPI.btnALAceptar)) 
		{
			try
			{
				GPBD.ConexionBD();
				GPBD.sentencia = "INSERT INTO locales (direccionLocal) VALUES ('"+GPI.txtALDireccionLocal.getText()+"')";
				GPBD.statement.executeUpdate(GPBD.sentencia);
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}

			finally
			{
				Log.registrarLog("Alta de local realizada");
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
		if(evento.getSource().equals(GPI.btnALLimpiar)) 
		{
			GPI.txtALDireccionLocal.selectAll();
			GPI.txtALDireccionLocal.setText("");
			GPI.txtALDireccionLocal.requestFocus();
		}
	}
}
