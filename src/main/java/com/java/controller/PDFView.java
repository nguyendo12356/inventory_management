package com.java.controller;

import java.awt.Color;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.java.entity.IOInventory;
import com.java.entity.InvoiceProduct;
import com.java.entity.Product;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		IOInventory inventoryModel = (IOInventory) model.get("model");
		
		BaseFont fonts = BaseFont.createFont("verdana.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);		
		
		String[] name = new String[] {"CỬA HÀNG ĐỒNG HỒ T2D","Thạnh Mỹ Tây, Châu Phú, An Giang","Điện thoại: (08) 345 345"};
		Paragraph title;
		for(int i = 0; i < name.length; i++) {
			title = new Paragraph(new Chunk(name[i], new Font(fonts, 10)));
			title.setAlignment(Paragraph.ALIGN_RIGHT);
			document.add(title);	
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String date = "Ngày: " + dateFormat.format(inventoryModel.getCreateDate());
		Paragraph sDate = new Paragraph(new Chunk(date, new Font(fonts,8)));
		sDate.setAlignment(Paragraph.ALIGN_RIGHT);
		Paragraph header = new Paragraph(new Chunk(new String("PHIẾU NHẬP HÀNG"), new Font(fonts,22)));
		
		header.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(header);
		document.add(sDate);
		document.add( Chunk.NEWLINE );
		Chunk suplier = new Chunk("Nhà cung cấp: "+ inventoryModel.getSuplier()+"\n"+
				"Đã cung cấp cho cửa hàng T2D các mặt hàng sau:", new Font(fonts, 12));
		document.add(new Paragraph(suplier));
		document.add( Chunk.NEWLINE );        
        
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setWidths(new float[] {1.0f, 3.0f ,2.0f, 1.0f, 1.0f, 2.0f});
        table.setSpacingBefore(10);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        
        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
		
        cell.setPhrase(new Phrase("Mã sản phẩm", new Font(fonts, 12)));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tên sản phẩm", new Font(fonts, 12)));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Loại", new Font(fonts, 12)));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Số lượng", new Font(fonts, 12)));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Giá", new Font(fonts, 12)));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Tổng giá", new Font(fonts, 12)));
        table.addCell(cell);
        int totalPrice = 0;
        for(int i = 0; i < inventoryModel.getIps().size(); i++) {
        	Product p = inventoryModel.getIps().get(i).getProduct();
        	InvoiceProduct ip = inventoryModel.getIps().get(i);
        	table.addCell(new PdfPCell(new Phrase(p.getCode(),new Font(fonts,12))));
        	table.addCell(new PdfPCell(new Phrase(p.getName(),new Font(fonts,12))));
        	table.addCell(new PdfPCell(new Phrase(p.getCategory().getName(),new Font(fonts,12))));
        	table.addCell(new PdfPCell(new Phrase(ip.getQuantity()+"",new Font(fonts,12))));
        	table.addCell(new PdfPCell(new Phrase(p.getPrice()+"",new Font(fonts,12))));
        	table.addCell(new PdfPCell(new Phrase(ip.getQuantity()*p.getPrice()+"",new Font(fonts,12))));
        	totalPrice += ip.getQuantity()*p.getPrice();
        }
        document.add(table);
        document.add( Chunk.NEWLINE ); 
        
        PdfPTable tablePrice = new PdfPTable(2);
		table.setWidthPercentage(10);
        table.setSpacingBefore(20);
        
        tablePrice.setHorizontalAlignment(Element.ALIGN_RIGHT);
        double vat = (totalPrice*10)/100;
        double totalPriceAfterVat = totalPrice - vat;
        tablePrice.addCell(new PdfPCell(new Phrase("Tổng tiền hàng",new Font(fonts,12))));
        tablePrice.addCell(new PdfPCell(new Phrase(totalPrice+"",new Font(fonts,12))));
        tablePrice.addCell(new PdfPCell(new Phrase("Thuế GTGT: ",new Font(fonts,12))));
        tablePrice.addCell(new PdfPCell(new Phrase(new DecimalFormat("#").format(vat),new Font(fonts,12))));
        tablePrice.addCell(new PdfPCell(new Phrase("Tổng tiền: ",new Font(fonts,12))));
        tablePrice.addCell(new PdfPCell(new Phrase(new DecimalFormat("#").format(totalPriceAfterVat),new Font(fonts,12))));
        document.add(tablePrice);
        
        document.add( Chunk.NEWLINE ); 
        
        Chunk cStaff = new Chunk("Nhân viên phụ trách: "+inventoryModel.getStaffName(), new Font(fonts,10));
        Paragraph pStaff = new Paragraph(cStaff);
        pStaff.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(pStaff);
        
	}

}
