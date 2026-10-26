import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser(GlobalVariable.URL)

CustomKeywords.'auth.LoginKeywords.login'(GlobalVariable.USERNAME, GlobalVariable.PASSWORD)

WebUI.closeBrowser()