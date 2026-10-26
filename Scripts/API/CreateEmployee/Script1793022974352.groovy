import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable

CustomKeywords.'common.ApplicationKeywords.launchApplication'(GlobalVariable.URL)

CustomKeywords.'auth.LoginKeywords.login'(GlobalVariable.USERNAME, GlobalVariable.PASSWORD)

def response = CustomKeywords.'api.EmployeeApiKeywords.createEmployee'('SaiKeerthi', 'Test', 'Last', 'EMP133')

println("Create employee response:")

println(response.getResponseText())

WS.verifyResponseStatusCode(response, 200)

def json = new JsonSlurper().parseText(response.getResponseText())

def empNumber = json.data.empNumber

println('Employee Number = ' + empNumber)

def deleteResponse = CustomKeywords.'api.EmployeeApiKeywords.deleteEmployee'(empNumber)

println(deleteResponse.getResponseText())

WS.verifyResponseStatusCode(deleteResponse, 200)

WebUI.closeBrowser()