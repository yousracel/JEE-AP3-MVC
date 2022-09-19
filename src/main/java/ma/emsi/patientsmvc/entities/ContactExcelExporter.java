package ma.emsi.patientsmvc.entities;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class ContactExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Contact> listContacts;

    public ContactExcelExporter(List<Contact> listContacts){
        this.listContacts=listContacts;
        workbook =new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet= workbook.createSheet("Contacts");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        //CreationHelper createHelper = workbook.getCreationHelper();
        XSSFFont font= workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        //style.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy "));
        font.setColor(HSSFColor.HSSFColorPredefined.CORNFLOWER_BLUE.getIndex());

        createCell(row, 0, "Date", style);
        createCell(row, 1, "ID", style);
        createCell(row, 2, "Type", style);
        createCell(row, 3, "Intermédiaire", style);
        createCell(row, 4, "Nom De L'intermédiare", style);
        createCell(row, 5, "périmètre", style);
        createCell(row, 6, "Type Feedback", style);
        createCell(row, 7, "Nature de la Demande", style);
        createCell(row, 8, "Commentaire", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }else {
            cell.setCellValue((Date) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);


        for (Contact contact : listContacts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, contact.getDate().toString(), style);
            createCell(row, columnCount++, contact.getId(), style);
            createCell(row, columnCount++, contact.getType(), style);
            createCell(row, columnCount++, contact.getIntermediaire(), style);
            createCell(row, columnCount++, contact.getNomInterm(), style);
            createCell(row, columnCount++, contact.getPerimetre(), style);
            createCell(row, columnCount++, contact.getTypeFeedback(), style);
            createCell(row, columnCount++, contact.getNatureDemande(), style);
            createCell(row, columnCount++, contact.getDescription(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
