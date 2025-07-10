package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelSummaryWriter {

    private Workbook workbook;
    private String filePath;

    public ExcelSummaryWriter(String filePath) {
        this.workbook = new XSSFWorkbook();
        this.filePath = filePath;
    }

    public void addSheet(String sheetName, String[] headers, List<String[]> data) {
        Sheet sheet = workbook.createSheet(sheetName);

        // Header Row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Data Rows
        int rowNum = 1;
        for (String[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                row.createCell(i).setCellValue(rowData[i]);
            }
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void saveAndClose() {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            workbook.close();
            System.out.println("Excel Summary written to: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
