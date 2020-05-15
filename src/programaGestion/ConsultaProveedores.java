package programaGestion;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ConsultaProveedores extends WindowAdapter implements ActionListener
{

	ConsultaProveedores()
	{
		GPI.consultaProveedor.setLayout(new FlowLayout());
		GPI.consultaProveedor.setSize(550, 300);
		GPI.consultaProveedor.setResizable(false);
		GPI.consultaProveedor.addWindowListener(this);
		GPI.btnCProvExportarPDF.addActionListener(this);
		GPI.consultaProveedor.add(GPI.lblConsultaProveedor);
		GPI.consultaProveedor.add(GPI.taConsultaProveedor);
		GPI.consultaProveedor.add(GPI.btnCProvExportarPDF);
		GPI.taConsultaProveedor.setEditable(false);
		GPI.consultaProveedor.setLocationRelativeTo(null);
		GPI.consultaProveedor.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area

		{
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM proveedores";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			GPI.taConsultaProveedor.setText("");
			while(GPBD.rs.next())
			{
				if(GPI.taConsultaProveedor.getText().length()==0)
				{
					GPI.taConsultaProveedor.setText(GPBD.rs.getInt("idProveedor")+
							"-"+GPBD.rs.getString("nombreProveedor")+
							", "+GPBD.rs.getString("telefonoProveedor")+
							", "+GPBD.rs.getString("nifProveedor"));
				}
				else
				{
					GPI.taConsultaProveedor.setText(GPI.taConsultaProveedor.getText() + "\n" +
							GPBD.rs.getInt("idProveedor")+
							"-"+GPBD.rs.getString("nombreProveedor")+
							", "+GPBD.rs.getString("telefonoProveedor")+
							", "+GPBD.rs.getString("nifProveedor"));
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
			Log.registrarLog("Consulta de proveedor realizada");
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

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.consultaProveedor.isActive())
		{
			GPI.consultaProveedor.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(GPI.btnCProvExportarPDF)) 
		{
			// Se crea el documento 
			Document documento = new Document();
			String resultado = "";
			try 
			{
				try	//Sentencia para recopilar los datos e introducirlos en la variable

				{
					GPBD.ConexionBD();
					GPBD.sentencia = "SELECT * FROM proveedores";
					GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
					GPI.taConsultaProducto.setText("");
					while(GPBD.rs.next())
					{
						resultado = resultado + GPBD.rs.getInt("idProveedor")+
								"-"+GPBD.rs.getString("nombreProveedor")+
								"-"+GPBD.rs.getString("telefonoProveedor")+
								"-"+GPBD.rs.getString("nifProveedor")+"\n";
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
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf. 
				FileOutputStream ficheroPdf = new FileOutputStream("Proveedores.pdf");
				PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(22);
				// Se abre el documento. 
				documento.open();
				Paragraph titulo = new Paragraph("INFORME DE PROVEEDORES", 
						FontFactory.getFont("arial", // fuente 
								22, // tamaño 
								Font.BOLD, // estilo 
								BaseColor.RED)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);
				documento.add(titulo);
				// Sacar los datos
				String[] cadena = resultado.split("\n");
				PdfPTable tabla = new PdfPTable(4); // Se indica el número de columnas
				tabla.setSpacingBefore(5); // Espaciado ANTES de la tabla
				tabla.addCell("ID");
				tabla.addCell("NOMBRE");
				tabla.addCell("TELEFONO");
				tabla.addCell("NIF");
				String[] subCadena;
				for (int i = 0; i < cadena.length; i++) 
				{
					subCadena = cadena[i].split("-");
					for(int j = 0; j < 4;j++)
					{
						tabla.addCell(subCadena[j]);
					}
				}
				documento.add(tabla); 
				documento.close(); 
				//Abrimos el archivo PDF recién creado 
				try 
				{
					File path = new File ("Proveedores.pdf"); 
					Desktop.getDesktop().open(path); 
				}
				catch (IOException ex) 
				{
					System.out.println("Se ha producido un error al abrir el archivo PDF"); 
				}
			}
			catch ( Exception e ) 
			{ 
				System.out.println("Se ha producido un error al generar el archivo PDF");  
			}
		}
	}
}