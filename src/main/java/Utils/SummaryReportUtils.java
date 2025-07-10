package Utils;

public class SummaryReportUtils {

    public static void generateExcelSummary() {
        String excelPath = "results/reports/UserSummary.xlsx";
        ExcelSummaryWriter writer = new ExcelSummaryWriter(excelPath);

        // Added Users
        writer.addSheet("Added - Success", new String[]{"Username", "Status"},
                ReportTable.filterByStatus(UserSummaryTracker.getAddSummary(), "Success"));
        writer.addSheet("Added - Failure", new String[]{"Username", "Status"},
                ReportTable.filterByStatus(UserSummaryTracker.getAddSummary(), "Failure"));

        // Updated Users
        writer.addSheet("Updated - Success", new String[]{"Old Username", "New Username", "Status"},
                ReportTable.filterByStatus(UserSummaryTracker.getUpdateSummary(), "Success"));
        writer.addSheet("Updated - Failure", new String[]{"Old Username", "New Username", "Status"},
                ReportTable.filterByStatus(UserSummaryTracker.getUpdateSummary(), "Failure"));

        // Deleted Users
        writer.addSheet("Deleted - Success", new String[]{"Username", "Status"},
                ReportTable.filterByStatus(UserSummaryTracker.getDeleteSummary(), "Success"));
        writer.addSheet("Deleted - Failure", new String[]{"Username", "Status"},
                ReportTable.filterByStatus(UserSummaryTracker.getDeleteSummary(), "Failure"));

        writer.saveAndClose();
    }
}
