import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.llm.keyword.LlmKeywords as LLM
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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

def employeeId = 'E' + new Random().nextInt(9000)

def randomSuffix = UUID.randomUUID().toString().replaceAll('[0-9-]', '').substring(0, 4)

def uniqueFirstName = GlobalVariable.FIRSTNAME + randomSuffix

// API Create
def empNumber = CustomKeywords.'api.EmployeeApiKeywords.createEmployeeAndReturnEmpNumber'(uniqueFirstName, GlobalVariable.MIDDLENAME, 
    GlobalVariable.LASTNAME, employeeId)

println('Employee Number = ' + empNumber)

// UI Verify
CustomKeywords.'employee.EmployeeKeywords.searchEmployee'(uniqueFirstName)

CustomKeywords.'employee.EmployeeKeywords.verifyEmployeeExists'(uniqueFirstName)

// API Delete
def deleteResponse = CustomKeywords.'api.EmployeeApiKeywords.deleteEmployee'(empNumber)

println(deleteResponse.getResponseText())

WS.verifyResponseStatusCode(deleteResponse, 200)

// UI Verify
CustomKeywords.'employee.EmployeeKeywords.searchEmployee'(uniqueFirstName)

CustomKeywords.'employee.EmployeeKeywords.verifyEmployeeNotExists'(uniqueFirstName)

WebUI.closeBrowser()

