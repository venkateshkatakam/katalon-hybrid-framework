import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys

CustomKeywords.'common.ApplicationKeywords.launchApplication'(GlobalVariable.URL)

CustomKeywords.'auth.LoginKeywords.login'(GlobalVariable.USERNAME, GlobalVariable.PASSWORD)

CustomKeywords.'common.NavigationKeywords.goToPIM'()

def response = CustomKeywords.'api.EmployeeApiKeywords.createEmployee'("SaiVenkat",
    "Test",
    "Last",
    "EMP1001"
)


println(response.getStatusCode())
println(response.getResponseText())

//WebUI.click(findTestObject('Object Repository/OrangeHRM/PIM/AddEmployee/lnk_AddEmployee'))
//
//WebUI.setText(findTestObject('Object Repository/OrangeHRM/PIM/AddEmployee/txt_FirstName'), GlobalVariable.FIRSTNAME)
//
//WebUI.setText(findTestObject('Object Repository/OrangeHRM/PIM/AddEmployee/txt_LastName'), GlobalVariable.LASTNAME)
//
//WebUI.click(findTestObject('Object Repository/OrangeHRM/PIM/AddEmployee/btn_SaveEmployee'))
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/OrangeHRM/PIM/AddEmployee/msg_EmployeeCreated'), 10)

def employeeId = CustomKeywords.'api.EmployeeApiKeywords.getEmployeeId'("SaiVenkat")

println('Employee ID of created Employee is ' + employeeId)

WebUI.closeBrowser()

