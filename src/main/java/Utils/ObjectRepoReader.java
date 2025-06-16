package Utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectRepoReader {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("test-data/ObjectRepository.properties");
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load ObjectRepository.properties: " + e.getMessage());
        }
    }

    public static By getLocator(String pageName, String elementName) {
        String key = pageName + "." + elementName;
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("No locator found for: " + key);
        }

        String[] parts = value.split("=", 2);
        String locatorType = parts[0].toLowerCase();
        String locatorValue = parts[1];

        switch (locatorType) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "class":
                return By.className(locatorValue);
            case "tag":
                return By.tagName(locatorValue);
            case "link":
                return By.linkText(locatorValue);
            case "partiallink":
                return By.partialLinkText(locatorValue);
            default:
                throw new RuntimeException("Unsupported locator type: " + locatorType);
        }
    }
}
