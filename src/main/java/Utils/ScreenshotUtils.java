package Utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver) {
        Date currentDate = new Date();
        String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationPath = new File("results/screenshots/" + screenshotFileName + ".png");
        String absolutePath = destinationPath.getAbsolutePath();
        try {
            FileUtils.copyFile(screenshotFile, destinationPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absolutePath;
    }
}
