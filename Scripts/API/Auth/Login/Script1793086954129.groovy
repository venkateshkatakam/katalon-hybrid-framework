import internal.GlobalVariable as GlobalVariable

def loginResponse = CustomKeywords.'api.ApiAuthKeywords.apiLogin'()

println("Status = " + loginResponse.getStatusCode())

loginResponse.getHeaderFields().each { key, value ->
	println("$key -> $value")
}

println("Session Cookie = " + GlobalVariable.SESSION_COOKIE )

def response = CustomKeywords.'api.EmployeeApiKeywords.getEmployee'('manda')

println(response.getStatusCode())

println(response.getResponseText())