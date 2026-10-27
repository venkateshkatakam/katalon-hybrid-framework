import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'common.ApplicationKeywords.launchApplication'(GlobalVariable.URL)

CustomKeywords.'auth.LoginKeywords.login'(GlobalVariable.USERNAME, GlobalVariable.PASSWORD)

CustomKeywords.'common.NavigationKeywords.goToPIM'()

def employeeId = 'E' + new Random().nextInt(9000)

def firstName = GlobalVariable.FIRSTNAME + employeeId

def empNumber = CustomKeywords.'api.EmployeeApiKeywords.createEmployeeAndReturnEmpNumber'(firstName, GlobalVariable.MIDDLENAME,
	GlobalVariable.LASTNAME, employeeId)

println('Employee Number = ' + empNumber)

def getResponse = CustomKeywords.'api.EmployeeApiKeywords.getEmployee'(firstName)

WS.verifyResponseStatusCode(getResponse, 200)

println(getResponse.getResponseText())

def deleteResponse = CustomKeywords.'api.EmployeeApiKeywords.deleteEmployee'(empNumber)

println(deleteResponse.getResponseText())

WS.verifyResponseStatusCode(deleteResponse, 200)

def verifyResponse = CustomKeywords.'api.EmployeeApiKeywords.getEmployee'(firstName)

println(verifyResponse.getResponseText())

def verifyJson = new JsonSlurper().parseText(verifyResponse.getResponseText())

assert verifyJson.meta.total == 0

WebUI.closeBrowser()