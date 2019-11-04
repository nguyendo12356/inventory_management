package com.java.controller;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.java.model.InventoryModel;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		InventoryModel inventoryModel = (InventoryModel) model.get("model");
		
		Paragraph header = new Paragraph(new Chunk("Export PDF", FontFactory.getFont(FontFactory.HELVETICA,30)));
		Paragraph by = new Paragraph(new Chunk("Author " + inventoryModel.getCodeBill(),FontFactory.getFont(FontFactory.HELVETICA, 20)));
		
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
		
        cell.setPhrase(new Phrase("Book Title", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Author", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("ISBN", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Published Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
        
        for(int i = 0; i < 3; i++) {
        	table.addCell("1");
        	table.addCell("2");
        	table.addCell("3");
        	table.addCell("4");
        	table.addCell("5");
        }
        
		document.add(header);
		document.add(by);
		document.add(table);
	}

}
