import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

/*
 * Uruchomienie przegladarki z adresem aplikacji
 */
WebUI.openBrowser(rawUrl=GlobalVariable.url)

/*
 * Klikniecie przycisku Sing in i przejscie do ekranu autentykacji 
 */
WebUI.click(findTestObject('Object Repository/PageAuthentication/header_sign_in'))

/*
 * Weryfikacja zawartosci sekcji logowania
 * Tekst "Email adress"
 * Tekst "Password"
 * Klikalny tekst "Forgot your password?"
 */
WebUI.verifyElementClickable(findTestObject('Object Repository/PageAuthentication/str_recover_pwd'))
str_email_element = WebUI.getText(findTestObject('Object Repository/PageAuthentication/label_email'))
assert str_email_exp == str_email_element
str_pwd_element = WebUI.getText(findTestObject('Object Repository/PageAuthentication/label_pwd'))
assert str_pwd_exp == str_pwd_element

/*
 * Wprowadzenie emaila zarejestrowanego uzytkownika
 */
WebUI.setText(findTestObject('Object Repository/PageAuthentication/inp_email_id'), GlobalVariable.registered_user_email)

/*
 * Wprowadzenie hasla uzytkownika
 * WebUI.setText(findTestObject('Object Repository/PageAuthentication/inp_pwd_id'), GlobalVariable.correct_user_pwd)
 */
WebUI.setEncryptedText(findTestObject('Object Repository/PageAuthentication/inp_pwd_id'), GlobalVariable.correct_user_pwd)

/*
 * Przycisniecie zielonego przycisku logowania
 */
WebUI.click(findTestObject('Object Repository/PageAuthentication/btn_login'))
/*
 * Pobranie nazwy uzytkownika
 */
str_username_elem = WebUI.getText(findTestObject('Object Repository/PageMyAccount/str_username'))

/*
 * Weryfikacja czy system zalogował odpowiedniego docelowego użytkownika.
 */
assert username_exp == str_username_elem

/*
 * Kliknięcie przycisku wylogowania.
 */
WebUI.click(findTestObject('Object Repository/PageMyAccount/header_sing_out'))

/*
 * Zamykanie przegladarki
 */
WebUI.closeBrowser()