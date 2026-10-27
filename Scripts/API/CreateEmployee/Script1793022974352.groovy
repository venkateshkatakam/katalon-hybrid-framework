import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'common.ApplicationKeywords.launchApplication'(GlobalVariable.URL)

CustomKeywords.'auth.LoginKeywords.login'(GlobalVariable.USERNAME, GlobalVariable.PASSWORD)

def employeeId = 'E' + new Random().nextInt(9000)

def response = CustomKeywords.'api.EmployeeApiKeywords.createEmployee'(GlobalVariable.FIRSTNAME, GlobalVariable.MIDDLENAME, 
    GlobalVariable.LASTNAME, employeeId)

WS.verifyResponseStatusCode(response, 200)

def json = new JsonSlurper().parseText(response.getResponseText())

def empNumber = json.data.empNumber

println('Employee Number = ' + empNumber)

def deleteResponse = CustomKeywords.'api.EmployeeApiKeywords.deleteEmployee'(empNumber)

println(deleteResponse.getResponseText())

WS.verifyResponseStatusCode(deleteResponse, 200)

WebUI.closeBrowser()