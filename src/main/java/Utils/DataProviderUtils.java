package Utils;

import org.testng.annotations.*;

public class DataProviderUtils {

	@DataProvider(name = "userdata")
	public Object[][] userDataProvider(){
		return ExcelUtils.readExcelData("test-data/testdata.xlsx", "adduser");
	}
	
	@DataProvider(name = "updateuserdata")
	public Object[][] updateUserDataProvider(){
		return ExcelUtils.readExcelData("test-data/testdata.xlsx", "updateuser");
	}
	
	@DataProvider(name = "deleteuserdata")
	public Object[][] deleteUserDataProvider(){
		return ExcelUtils.readExcelData("test-data/testdata.xlsx", "deleteuser");
	}
}
