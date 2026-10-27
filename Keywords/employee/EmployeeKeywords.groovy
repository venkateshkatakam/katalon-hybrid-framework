package employee

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class EmployeeKeywords {

	@Keyword
	searchEmployee(employeeName){
		
		WebUI.click(findTestObject('Object Repository/OrangeHRM/PIM/SearchEmployee/btn_Reset'))
		
		WebUI.setText(findTestObject('Object Repository/OrangeHRM/PIM/SearchEmployee/txt_EmployeeName'), employeeName)

		WebUI.click(findTestObject('Object Repository/OrangeHRM/PIM/SearchEmployee/btn_Search'))
	}
	
	@Keyword
	def verifyEmployeeExists(employeeName) {
	
		List<WebElement> employeeRows = WebUI.findWebElements(
			findTestObject('Object Repository/OrangeHRM/PIM/SearchEmployee/row_EmployeeResult'), 10)
	
		assert employeeRows.any { row ->
			row.getText().contains(employeeName) } : "Employee '${employeeName}' was expected but not found"
	}
	
	@Keyword
	def verifyEmployeeNotExists(String employeeName) {
	
		List<WebElement> employeeRows = WebUI.findWebElements(
			findTestObject('Object Repository/OrangeHRM/PIM/SearchEmployee/row_EmployeeResult'), 5)
		
		assert !employeeRows.any { row ->
			row.getText().contains(employeeName) } : "Employee '${employeeName}' still exists"
	}
	
}
