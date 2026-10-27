package api

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import internal.GlobalVariable


public class EmployeeApiKeywords {

	@Keyword
	getEmployee(employeeName){

		return WS.sendRequest(findTestObject('API/Employee/GetEmployee',
				[('sessionCookie'): GlobalVariable.SESSION_COOKIE, ('employeeName'): employeeName]))
	}

	@Keyword
	def getEmployeeId(employeeName) {

		def response = getEmployee(employeeName)

		def json = new JsonSlurper().parseText(response.getResponseText())

		return json.data[0].employeeId
	}


	@Keyword
	createEmployee(firstName, middleName, lastName, employeeId){

		return WS.sendRequest(findTestObject('API/Employee/CreateEmployee',
				[('sessionCookie'): GlobalVariable.SESSION_COOKIE, ('firstName'): firstName, ('lastName'): lastName, ('middleName'): middleName, ('employeeId'): employeeId]))
	}

	@Keyword
	def createEmployeeAndReturnEmpNumber(firstName, middleName, lastName, employeeId){

		def response = createEmployee(firstName, middleName, lastName, employeeId)

		def json = new JsonSlurper().parseText(response.getResponseText())

		return json.data.empNumber
	}

	@Keyword
	def deleteEmployee(empNumber){

		return WS.sendRequest(findTestObject('API/Employee/DeleteEmployee',
				['sessionCookie' : GlobalVariable.SESSION_COOKIE, 'empNumber' : empNumber]))
	}
}
