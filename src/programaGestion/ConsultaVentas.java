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

public class ConsultaVentas extends WindowAdapter implements ActionListener
{

	ConsultaVentas()
	{
		Vista.consultaVenta.setLayout(new FlowLayout());
		Vista.consultaVenta.setSize(550, 300);
		Vista.consultaVenta.setResizable(false);
		Vista.consultaVenta.addWindowListener(this);
		Vista.btnCVExportarPDF.addActionListener(this);
		Vista.consultaVenta.add(Vista.lblConsultaVenta);
		Vista.consultaVenta.add(Vista.taConsultaVenta);
		Vista.consultaVenta.add(Vista.btnCVExportarPDF);
		Vista.taConsultaVenta.setEditable(false);
		Vista.consultaVenta.setLocationRelativeTo(null);
		Vista.consultaVenta.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area

		{
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM venden";
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
			Vista.taConsultaVenta.setText("");
			while(Modelo.rs.next())
			{
			    String[] arrayFA = Modelo.rs.getString("fechaVenta").split("-");
			    String fechaEuropea = arrayFA[2] + "-" + arrayFA[1] + "-" + arrayFA[0];
				if(Vista.taConsultaVenta.getText().length()==0)
				{
					Vista.taConsultaVenta.setText(Modelo.rs.getInt("idLocalFK")+
							"-"+Modelo.rs.getString("idProductoFK")+
							", "+ fechaEuropea);
				}
				else
				{
					Vista.taConsultaVenta.setText(Vista.taConsultaVenta.getText() + "\n" +
							Modelo.rs.getInt("idLocalFK")+
							"-"+Modelo.rs.getString("idProductoFK")+
							", " + fechaEuropea);
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
			Log.registrarLog("Consulta de venta realizada");
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

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.consultaVenta.isActive())
		{
			Vista.consultaVenta.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(Vista.btnCVExportarPDF)) 
		{
			// Se crea el documento 
			Document documento = new Document();
			String resultado = "";
			try 
			{
				try	//Sentencia para recopilar los datos e introducirlos en la variable

				{
					Modelo.ConexionBD();
					Modelo.sentencia = "SELECT * FROM venden";
					Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
					Vista.taConsultaProducto.setText("");
					while(Modelo.rs.next())
					{
						String[] arrayFA = Modelo.rs.getString("fechaVenta").split("-");
						resultado = resultado + Modelo.rs.getInt("idLocalFK")+
								"-"+Modelo.rs.getString("idProductoFK")+
								"-"+arrayFA[2]+"/"+arrayFA[1]+"/"+arrayFA[0]+"\n";;
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
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf. 
				FileOutputStream ficheroPdf = new FileOutputStream("Ventas.pdf");
				PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(22);
				// Se abre el documento. 
				documento.open();
				Paragraph titulo = new Paragraph("INFORME DE VENTAS", 
						FontFactory.getFont("arial", // fuente 
								22, // tamaño 
								Font.BOLD, // estilo 
								BaseColor.RED)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);
				documento.add(titulo);
				// Sacar los datos
				String[] cadena = resultado.split("\n");
				PdfPTable tabla = new PdfPTable(3); // Se indica el número de columnas
				tabla.setSpacingBefore(5); // Espaciado ANTES de la tabla
				tabla.addCell("LOCAL");
				tabla.addCell("PRODUCTO");
				tabla.addCell("FECHA");
				String[] subCadena;
				for (int i = 0; i < cadena.length; i++) 
				{
					subCadena = cadena[i].split("-");
					for(int j = 0; j < 3;j++)
					{
						tabla.addCell(subCadena[j]);
					}
				}
				documento.add(tabla); 
				documento.close(); 
				//Abrimos el archivo PDF recién creado 
				try 
				{
					File path = new File ("Ventas.pdf"); 
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