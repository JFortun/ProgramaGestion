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
		Vista.altaLocal.setLayout(new FlowLayout());
		Vista.altaLocal.setSize(350, 130);
		Vista.altaLocal.setResizable(false);
		Vista.altaLocal.addWindowListener(this);
		Vista.btnALAceptar.addActionListener(this);
		Vista.btnALLimpiar.addActionListener(this);
		Vista.altaLocal.add(Vista.lblALDireccionLocal);
		Vista.altaLocal.add(Vista.txtALDireccionLocal);
		Vista.altaLocal.add(Vista.btnALAceptar);
		Vista.altaLocal.add(Vista.btnALLimpiar);
		Vista.altaLocal.setLocationRelativeTo(null);
		Vista.altaLocal.setVisible(true);
		Vista.txtALDireccionLocal.selectAll();
		Vista.txtALDireccionLocal.setText("");
	}
	
	public void windowClosing(WindowEvent arg0) //Averiguar porqué no funciona
	{
		if(Vista.altaLocal.isActive())
		{
			Vista.altaLocal.setVisible(false);
		}
	}
	
	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(Vista.btnALAceptar)) 
		{
			try
			{
				Modelo.ConexionBD();
				Modelo.sentencia = "INSERT INTO locales (direccionLocal) VALUES ('"+Vista.txtALDireccionLocal.getText()+"')";
				Modelo.statement.executeUpdate(Modelo.sentencia);
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
		if(evento.getSource().equals(Vista.btnALLimpiar)) 
		{
			Vista.txtALDireccionLocal.selectAll();
			Vista.txtALDireccionLocal.setText("");
			Vista.txtALDireccionLocal.requestFocus();
		}
	}
}
