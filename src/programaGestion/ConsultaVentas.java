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
		GPI.consultaVenta.setLayout(new FlowLayout());
		GPI.consultaVenta.setSize(550, 300);
		GPI.consultaVenta.setResizable(false);
		GPI.consultaVenta.addWindowListener(this);
		GPI.btnCVExportarPDF.addActionListener(this);
		GPI.consultaVenta.add(GPI.lblConsultaVenta);
		GPI.consultaVenta.add(GPI.taConsultaVenta);
		GPI.consultaVenta.add(GPI.btnCVExportarPDF);
		GPI.taConsultaVenta.setEditable(false);
		GPI.consultaVenta.setLocationRelativeTo(null);
		GPI.consultaVenta.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area

		{
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM venden";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			GPI.taConsultaVenta.setText("");
			while(GPBD.rs.next())
			{
				if(GPI.taConsultaVenta.getText().length()==0)
				{
					GPI.taConsultaVenta.setText(GPBD.rs.getInt("idLocalFK")+
							"-"+GPBD.rs.getString("idProductoFK")+
							", "+GPBD.rs.getString("fechaVenta"));
				}
				else
				{
					GPI.taConsultaVenta.setText(GPI.taConsultaVenta.getText() + "\n" +
							GPBD.rs.getInt("idLocalFK")+
							"-"+GPBD.rs.getString("idProductoFK")+
							", "+GPBD.rs.getString("fechaVenta"));
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
		if(GPI.consultaVenta.isActive())
		{
			GPI.consultaVenta.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(GPI.btnCVExportarPDF)) 
		{
			// Se crea el documento 
			Document documento = new Document();
			String resultado = "";
			String[] fecha;
			try 
			{
				try	//Sentencia para recopilar los datos e introducirlos en la variable

				{
					GPBD.ConexionBD();
					GPBD.sentencia = "SELECT * FROM venden";
					GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
					GPI.taConsultaProducto.setText("");
					while(GPBD.rs.next())
					{
						fecha = (GPBD.rs.getString("fechaVenta")).split("-");
						resultado = resultado + GPBD.rs.getInt("idLocalFK")+
								"-"+GPBD.rs.getString("idProductoFK")+
								"-"+fecha[2]+"/"+fecha[1]+"/"+fecha[0]+"\n";;
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