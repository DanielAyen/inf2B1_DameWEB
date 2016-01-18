package Logik;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * Die Klasse DatenzugriffPDF erzeugt eine PDF
 *
 */
public class DatenzugriffPDF implements iDatenzugriff, Serializable {
	// private iMeldung logger;

	private static final long serialVersionUID = -1632008787864445195L;
	// public static final String ZielOrdner = "/home/informatik/Dame.pdf";
	public static SpielBean spiel;
	public static String location="/home/informatik/LokalRepo/inf2B1_DameWEB/DameWeb/Bilder/";//baris
	public static final String FeldS = location+"felds.png";
	public static final String FeldW = location+"feldw.png";
	public static final String FigurS = location+"FeldSSteinS.png";
	public static final String FigurW = location+"FeldSSteinW.png";
	public static final String DameS = location+"dameS.png";
	public static final String DameW = location+"dameW.png";
	public static final String FigurSG = location+"SteinSG.png";
	public static final String FigurWG = location+"SteinWG2.png";
	public static final String DameSG = location+"dameSG.png";
	public static final String DameWG = location+"dameWG.png";

	/**
	 * speichert / schreibt die PDF
	 * 
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@Override
	public void speichernPdf(SpielBean spielA,Object o, String pfad) throws IOException {
spiel=spielA;
		Document document = new Document(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream(pfad));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		document.open();

		// Document doc = new Document(PageSize.LETTER.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream(pfad));
			document.open();

			Paragraph p = new Paragraph("Dame - Spielstand \n"); // Ueberschrift in
																														// der PDF
			document.add(p);
			document.add(createFirstTable());
			document.close();
			// logger.log("PDF wurde erstellt. Projekt bitte refreshen.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static PdfPTable createFirstTable() throws DocumentException, IOException {
		// a table with twelve columns
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(65);

		for (int i = 11; i >= 0; i--) {// zeile
			for (int j = 0; j <= 12 - 1; j++) {// spalte

				if (spiel.getBrett().getBrettFeldIndex(i, j).getIstSchwarz()) {
					if (!spiel.getBrett().getBrettFeldIndex(i, j).getIstBelegt()) {
						table.addCell(createImageCell(FeldS));
					} else {
						Spielfigur fig = spiel.getBrett().getBrettFeldIndex(i, j).getSpielfigur();
						if (fig.getFarbe() == FarbEnum.SCHWARZ) {
							if (FarbEnum.SCHWARZ == spiel.getAmZug()) {
								if (spiel.getBrett().getBrettFeldIndex(i, j).getSpielfigur().getDame(fig)) {
									table.addCell(createImageCell(DameSG));
								} else {
									table.addCell(createImageCell(FigurSG));
								}
							} else {
								if (fig.getDame(fig)) {
									table.addCell(createImageCell(DameS));
								} else {
									table.addCell(createImageCell(FigurS));
								}
							}
						} else {
							if (FarbEnum.WEIß == spiel.getAmZug()) {
								if (spiel.getBrett().getBrettFeldIndex(i, j).getSpielfigur().getDame(fig)) {
									table.addCell(createImageCell(DameWG));
								} else {
									table.addCell(createImageCell(FigurWG));
								}
							} else {
								if (fig.getDame(fig)) {
									table.addCell(createImageCell(DameW));
								} else {
									table.addCell(createImageCell(FigurW));
								}
							}
						}
					}
				} else {
					table.addCell(createImageCell(FeldW));
				}
			}
		}
		return table;
	}

	public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
		Image img = Image.getInstance(path);
		PdfPCell cell = new PdfPCell(img, true);
		return cell;
	}

	/**
	 * laedt / liest die PDF
	 */
	@Override
	public Object laden(File selectedFile) {
		return selectedFile;
	}

	@Override
	public void speichern(Object o, String pfad) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
