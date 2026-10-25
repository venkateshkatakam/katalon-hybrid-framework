import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser(GlobalVariable.URL)

WebUI.setText(findTestObject('Object Repository/OrangeHRM/Login/txt_Username'), GlobalVariable.USERNAME)

WebUI.setText(findTestObject('Object Repository/OrangeHRM/Login/txt_Password'), GlobalVariable.PASSWORD)

WebUI.click(findTestObject('Object Repository/OrangeHRM/Login/btn_Login'))

WebUI.verifyElementPresent(findTestObject('Object Repository/OrangeHRM/Dashboard/lbl_Dashboard'), 10)

WebUI.closeBrowser()
