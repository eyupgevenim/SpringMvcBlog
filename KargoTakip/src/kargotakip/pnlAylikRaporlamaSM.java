package kargotakip;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kargotakip.op.Kargo;

public class pnlAylikRaporlamaSM extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel dm;
	private JDateChooser dcTarih;
	private String yol="";
	private String pdfIsim="tablo";
	
	
	public pnlAylikRaporlamaSM() {
		setBackground(new Color(204, 204, 153));
		setBounds(80, 60, 798, 488);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 153, 102));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 178, 123);
		add(panel);
		panel.setLayout(null);
		
		// listele buttonu
		JButton btn_aListele = new JButton("Listele");
		btn_aListele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dm.getDataVector().removeAllElements();
				revalidate();
				
				DateFormat df = new SimpleDateFormat("yyyy-MM");
				String tarih = df.format(dcTarih.getDate().getTime());
				
				ArrayList<Kargo> gL= new Kargo().subeAylik(frmMain.SUBE_,tarih);
				if(gL != null){
					for(Kargo k:gL){
						Object[] row = {
								k.kayitTarihi,k.kargo_,k.ucret
							};
						dm.addRow(row);
					}
				
				}else{
					JOptionPane.showMessageDialog(null, 
							"Veri bulunamadý !", "Arama sonucu", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				gL=null;
				
			}
		});
		btn_aListele.setBackground(new Color(51, 255, 153));
		btn_aListele.setIcon(new ImageIcon("img\\arama.png"));
		btn_aListele.setBounds(10, 70, 158, 31);
		panel.add(btn_aListele);
		
		dcTarih = new JDateChooser();
		dcTarih.setBounds(10, 11, 158, 31);
		panel.add(dcTarih);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(153, 102, 51));
		panel_1.setBounds(198, 11, 592, 466);
		add(panel_1);
		panel_1.setLayout(null);
		
		// tablo
		table = new JTable();
		dm = new DefaultTableModel(0, 0);
		dm.setColumnIdentifiers(new String[] {
				"Tarih", "Kargo Sayýsý", "Toplam Ücret"
			});
		table.setModel(dm);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 572, 406);
		panel_1.add(scrollPane);
		
		//pdf ye çek buttonu
		JButton btn_pdfCek = new JButton("PDF'e \u00C7ek");
		btn_pdfCek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DateFormat df = new SimpleDateFormat("yyyy-MM");
				pdfIsim = df.format(dcTarih.getDate().getTime());
				
				JFileChooser fc = new JFileChooser();
		        fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		        int result = fc.showOpenDialog(frmMain.PANEL);//

		        if( result == JFileChooser.APPROVE_OPTION )
		        {
		            File selectedFile = fc.getSelectedFile();
		            yol=selectedFile.getAbsolutePath();
		            
		            print();
		        }
				
			}
		});
		btn_pdfCek.setBackground(new Color(204, 255, 204));
		btn_pdfCek.setIcon(new ImageIcon("img\\pdf.png"));
		btn_pdfCek.setBounds(463, 428, 119, 27);
		panel_1.add(btn_pdfCek);

	}
	
	//tabloyu pdf ye çekme fonk.
	private void print(){
		  try {
	            Document doc = new Document();
	            PdfWriter.getInstance(doc, new FileOutputStream(yol+"\\"+pdfIsim+".pdf"));
	            doc.open();
	            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
	            
	            //tablo baþlýklarý 
	            for (int i = 0; i < table.getColumnCount(); i++) {
	                pdfTable.addCell(table.getColumnName(i));
	            }
	            //tablo içeriði
	            for (int rows = 0; rows < table.getRowCount(); rows++) {
	                for (int cols = 0; cols < table.getColumnCount(); cols++) {
	                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

	                }
	            }
	            doc.add(pdfTable);
	            doc.close();
	        }catch (DocumentException ex) {
	            System.out.println(ex);
	        }catch (FileNotFoundException ex) {
	        	System.out.println(ex);
	        }
	}
	
}
