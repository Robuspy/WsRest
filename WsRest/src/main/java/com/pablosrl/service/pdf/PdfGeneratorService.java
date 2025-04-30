package com.pablosrl.service.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.pablosrl.data.cuentas_cobrar.ClienteSaldo;

import javax.enterprise.context.ApplicationScoped;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PdfGeneratorService {

    public byte[] generarPdfSaldo(List<ClienteSaldo> saldos) throws Exception {
        if (saldos == null || saldos.isEmpty()) {
            throw new IllegalArgumentException("No hay datos para generar el PDF.");
        }

        ClienteSaldo cliente = saldos.get(0); // Usamos el primero para datos generales
        double totalSaldo = saldos.stream()
        	    .mapToDouble(ClienteSaldo::getSaldoCuota)
        	    .sum();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4, 36, 36, 50, 36);
        PdfWriter.getInstance(doc, baos);
        doc.open();

        Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font bold = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font normal = new Font(Font.HELVETICA, 11);

        doc.add(new Paragraph("PABLO SRL - Estado de Cuenta del Cliente", titleFont));
        doc.add(new Paragraph(" "));

        doc.add(new Paragraph("Código Cliente: " + cliente.getCodCliente(), normal));
        doc.add(new Paragraph("Nombre: " + cliente.getNombreCliente(), normal));
        doc.add(new Paragraph("Fecha de Emisión: " + LocalDate.now(), normal));
        doc.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{1.3f, 2f, 1.5f, 1.5f, 2f, 2f});

        String[] headers = {"Tipo", "Comprobante", "F. Origen", "F. Venc.", "Monto", "Saldo"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, bold));
            cell.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(cell);
        }

        for (ClienteSaldo s : saldos) {
            table.addCell(new Phrase(s.getTipoComprobante(), normal));
            table.addCell(new Phrase(s.getNroComprobante(), normal));
            table.addCell(new Phrase(s.getFecOrigen().toString(), normal));
            table.addCell(new Phrase(s.getFecVencimiento().toString(), normal));
            table.addCell(new Phrase(String.format("%,.0f", s.getMontoComprobante()), normal));
            table.addCell(new Phrase(String.format("%,.0f", s.getSaldoCuota()), normal));
        }

        doc.add(table);
        
        Paragraph total = new Paragraph("Total Saldo Pendiente: Gs. " + String.format("%,.0f", totalSaldo), bold);
        total.setSpacingBefore(10f);
        
        doc.add(total);
        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Documento generado automáticamente. No válido como factura.", normal));
        doc.close();

        return baos.toByteArray();
    }
} 
