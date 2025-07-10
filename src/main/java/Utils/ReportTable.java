package Utils;

import java.util.ArrayList;
import java.util.List;
import com.aventstack.extentreports.ExtentTest;

public class ReportTable {

    public static void buildAndLogSummary(String title, String[] headers, List<String[]> data, ExtentTest parentTest) {

        String headerRow = buildHeaderRow(headers);

        StringBuilder successTable = new StringBuilder();
        StringBuilder failureTable = new StringBuilder();

        int successCount = 0;
        int failureCount = 0;

        for (String[] row : data) {
            if (row == null || row.length == 0) continue;

            String rowHtml = buildRowHtml(row);
            String status = row[row.length - 1];

            if ("Success".equalsIgnoreCase(status)) {
                successTable.append(rowHtml);
                successCount++;
            } else {
                failureTable.append(rowHtml);
                failureCount++;
            }
        }

        if (successCount > 0) {
            String successHtml = buildCompleteTable(title + " - Success", headerRow, successTable.toString(), "green");
            parentTest.info(successHtml);
        }
        if (failureCount > 0) {
            String failureHtml = buildCompleteTable(title + " - Failure", headerRow, failureTable.toString(), "red");
            parentTest.info(failureHtml);
        }
    }

    /**
     * Filters data by status ("Success" or "Failure").
     */
    public static List<String[]> filterByStatus(List<String[]> data, String statusFilter) {
        List<String[]> filteredData = new ArrayList<>();
        for (String[] row : data) {
            if (row != null && row.length > 0 && row[row.length - 1].equalsIgnoreCase(statusFilter)) {
                filteredData.add(row);
            }
        }
        return filteredData;
    }

    // ----------------- Private Helper Methods -----------------

    private static String buildHeaderRow(String[] headers) {
        StringBuilder headerRow = new StringBuilder("<tr style='background-color: #f2f2f2;'>");
        for (String header : headers) {
            headerRow.append("<th style='border: 1px solid #ddd; padding: 8px;'>").append(header).append("</th>");
        }
        headerRow.append("</tr>");
        return headerRow.toString();
    }

    private static String buildRowHtml(String[] row) {
        StringBuilder rowHtml = new StringBuilder("<tr>");
        for (String cell : row) {
            rowHtml.append("<td style='border: 1px solid #ddd; padding: 8px;'>").append(cell).append("</td>");
        }
        rowHtml.append("</tr>");
        return rowHtml.toString();
    }

    private static String buildCompleteTable(String title, String headerRow, String rows, String color) {
        return "<h3 style='color:" + color + ";'>" + title + "</h3>" +
               "<table style='border-collapse: collapse; width: 60%; font-family: Arial, sans-serif; border: 1px solid #ddd;'>" +
               headerRow + rows + "</table><br>";
    }
}
