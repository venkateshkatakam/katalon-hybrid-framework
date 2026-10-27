package api

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable

public class ApiAuthKeywords {

	@Keyword
	getCsrfToken(){
		def response = WS.sendRequest(findTestObject('API/Auth/GetCSRFToken'))

		def headers = response.getHeaderFields()

		println(headers)

		//Extract Cookie
		def setCookieHeader = headers['Set-Cookie']

		if(setCookieHeader) {

			def cookieString = setCookieHeader[0]

			def sessionCookie = cookieString.split(';')[0].replace('orangehrm=', '')

			GlobalVariable.SESSION_COOKIE = sessionCookie

			//Extract Token
			def html = response.getResponseText()

			def matcher = html =~ /:token="([^"]+)"/

			return matcher[0][1]
		}
	}

	@Keyword
	def apiLogin() {

		def token = getCsrfToken()

		def response = WS.sendRequest(findTestObject('API/Auth/LoginRequest',
				[
					'token'    : token,
					'username' : GlobalVariable.USERNAME,
					'password' : GlobalVariable.PASSWORD,
					'sessionCookie' : GlobalVariable.SESSION_COOKIE
				]))

		return response
	}
}
