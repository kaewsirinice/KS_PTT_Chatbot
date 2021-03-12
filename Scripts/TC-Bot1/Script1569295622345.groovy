import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

row = 1

WebUI.openBrowser('')

//String url = findTestData('QnA').getValue(3, row)

//if (url.equalsIgnoreCase('PTTEP_PROCUREMENT')) {
//    WebUI.navigateToUrl(PROCUREMENT)
//}

WebUI.navigateToUrl("https://webchat.botframework.com/embed/PTTEP-ALFRED-PROCUREMENT-DEV?s=moiwpTlFfNw.tzV9hXLgbun2CcC62jhIQ7J_DeBJtf76JBsvuXV86pQ")

WebUI.maximizeWindow()

WebUI.waitForPageLoad(5)

int step = 3
//findTestData('QnA').getRowNumbers()
for (int row = 1; row <= 3; row++) {
    WebUI.setText(findTestObject('Page_PTTEP-ALFRED-PROCUREMENT-DEV/input_PTTEP-ALFRED'), findTestData('QnA').getValue("Question", 
            row))

    WebUI.sendKeys(findTestObject('Page_PTTEP-ALFRED-PROCUREMENT-DEV/input_PTTEP-ALFRED'), Keys.chord(Keys.ENTER))

    WebUI.waitForElementVisible(findTestObject('Page_PTTEP-ALFRED-PROCUREMENT-DEV/answer_Row', [('step') : step]), 3)

    output = WebUI.getText(findTestObject('Page_PTTEP-ALFRED-PROCUREMENT-DEV/answer_Row', [('step') : step]), FailureHandling.STOP_ON_FAILURE)

    println(output)

    //	int length = output.length()
    //	
    //	if ( length < 128) {
    if (WebUI.verifyMatch(output, findTestData('QnA').getValue("Answer", row), false, FailureHandling.CONTINUE_ON_FAILURE)) {
        KeywordUtil.markPassed('Actual Result equals Expected Result')
    } else {
        KeywordUtil.markFailed('Actual Result does not match Expected Result')
    }
    
    //	}
    //	else {
    //		String temp = findTestData('testData').getValue(2, row)
    //		String temp1 = temp.substring(0, 128);
    //		if (WebUI.verifyMatch(output, temp1+'.*', true, FailureHandling.CONTINUE_ON_FAILURE)) {
    //			KeywordUtil.markPassed('Actual Result equals Expected Result')
    //		} else {
    //			KeywordUtil.markFailed('Actual Result does not match Expected Result')
    //	}
    //	}	
    step += 2
}

