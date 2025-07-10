package Utils;

import java.util.ArrayList;
import java.util.List;

public class UserSummaryTracker {

    private static List<String[]> addSummary = new ArrayList<>();
    private static List<String[]> updateSummary = new ArrayList<>();
    private static List<String[]> deleteSummary = new ArrayList<>();

    public static void addUser(String username, boolean status) {
        addSummary.add(new String[]{ username, status ? "Success" : "Failure" });
    }

    public static void updateUser(String oldUsername, String newUsername, boolean status) {
        updateSummary.add(new String[]{ oldUsername, newUsername, status ? "Success" : "Failure" });
    }

    public static void deleteUser(String username, boolean status) {
        deleteSummary.add(new String[]{ username, status ? "Success" : "Failure" });
    }

    public static List<String[]> getAddSummary() {
        return addSummary;
    }

    public static List<String[]> getUpdateSummary() {
        return updateSummary;
    }

    public static List<String[]> getDeleteSummary() {
        return deleteSummary;
    }

    public static void clearAll() {
        addSummary.clear();
        updateSummary.clear();
        deleteSummary.clear();
    }
}
