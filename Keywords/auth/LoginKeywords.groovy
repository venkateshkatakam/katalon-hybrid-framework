package auth

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class LoginKeywords {

    @Keyword
    def login(String username, String password) {
        WebUI.setText(findTestObject('Object Repository/OrangeHRM/Login/txt_Username'), username)
        WebUI.setText(findTestObject('Object Repository/OrangeHRM/Login/txt_Password'), password)
        WebUI.click(findTestObject('Object Repository/OrangeHRM/Login/btn_Login'))
        WebUI.verifyElementPresent(findTestObject('Object Repository/OrangeHRM/Dashboard/lbl_Dashboard'), 10)

        def sessionCookie = DriverFactory.getWebDriver().manage().getCookieNamed('orangehrm')
        if (sessionCookie == null || !sessionCookie.getValue()) {
            throw new AssertionError("Login succeeded, but the 'orangehrm' session cookie was not found")
        }

        GlobalVariable.SESSION_COOKIE = sessionCookie.getValue()
        return GlobalVariable.SESSION_COOKIE
    }
}
