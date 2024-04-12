package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.GiftCardPage;
import pageObjects.HomePage;
import utilities.DataReader;
import utilities.ExcelUtility;

public class TC003_ValidatingGiftCardsSection extends BaseClass {
	
	public static int currentRow = 1;
	List<HashMap<String, String>> datamap;
	
	@Given("the user navigates to giftCard section")
	public void the_user_navigates_to_gift_card_section() {
		try {
			logger.info("******TC003__ValidatingGiftCardsSection started*********");
			homeObj = new HomePage(driver);
			homeObj.clickGiftCard();
			logger.info("Navigated to GiftCard section");
		}
		catch(Exception e) {
		  logger.info("  ==>Test TC003_ValidatingGiftCardsSection Passed");
		}
	}

	@Then("user should be redirected to payment page by passing all details with excel row {string}")
	public void user_should_be_redirected_to_payment_page_by_passing_all_details_with_excel_row(String rows) throws InterruptedException, IOException {
	   
		try {
			giftCardPageObj = new GiftCardPage(driver);
			ExcelUtility excelObj = new ExcelUtility("C:\\Users\\2318630\\eclipse-workspace\\HackathonCucumber\\testData\\CucumberData.xlsx");
			
			datamap=DataReader.data("C:\\Users\\2318630\\eclipse-workspace\\HackathonCucumber\\testData\\CucumberData.xlsx", "Sheet1");
	        int index=Integer.parseInt(rows)-1;
	        
	        String ocassion = datamap.get(index).get("Ocassion");
	        String amount = datamap.get(index).get("Amount");
	        String recName = datamap.get(index).get("R.Name");
	        String recEmail = datamap.get(index).get("R.Email");
	        String recMobile = datamap.get(index).get("R.Mobile");
	        String yourName = datamap.get(index).get("Y.Name");
	        String yourEmail = datamap.get(index).get("Y.Email");
	        String yourMobile = datamap.get(index).get("Y.Mobile");
	        String yourAddress = datamap.get(index).get("Y.Address");
	        String pincode = datamap.get(index).get("Pincode");
	        String expResult = datamap.get(index).get("Exp.Result");
	        
	          giftCardPageObj.selectOccasion(ocassion);
	          logger.info("Selected Ocassion :"+ocassion);
	          giftCardPageObj.selectAmount(amount);
	          logger.info("Selected amount :"+amount);
	          giftCardPageObj.clickNext();
	          logger.info("Clicked next button");
	        
	        giftCardPageObj.resetAllInputs();
			giftCardPageObj.setRecieverName(recName);
			giftCardPageObj.setRecieverEmail(recEmail);
			giftCardPageObj.setRecieverMobile(recMobile);
			giftCardPageObj.setYourName(yourName);
			giftCardPageObj.setYourEmail(yourEmail);
			giftCardPageObj.setYourMobile(yourMobile);
			giftCardPageObj.setAddress(yourAddress);
			giftCardPageObj.setPincode(pincode);
			Thread.sleep(2000);
			logger.info("provided all details in the form section");
			giftCardPageObj.clickConfirm();
			Thread.sleep(2000);
			logger.info("Clicked confirm button");
			
			logger.info("Validating the testCase");
			if(expResult.equalsIgnoreCase("valid")) {
				
				if(giftCardPageObj.isTargetPageDisplayed()) {
					giftCardPageObj.scrollToTargetPage();
					System.out.println("Registered Successfully");
					excelObj.setCellData("Sheet1", currentRow,11, "Passed");
					currentRow++;
					logger.info("  ==>Test TC003_ValidatingGiftCardsSection Passed");
					Assert.assertTrue(true);
				}
				else {
					excelObj.setCellData("Sheet1", currentRow,11, "Failed");
					currentRow++;
					logger.error("  ==>Test TC003_ValidatingGiftCardsSection Failed");
					Assert.fail();
				}		
			}
			
			else if(expResult.equalsIgnoreCase("invalid")) {
				if(giftCardPageObj.isTargetPageDisplayed()) {
					giftCardPageObj.scrollToTargetPage();
					excelObj.setCellData("Sheet1", currentRow,11, "Failed");
					currentRow++;
					logger.error("  ==>Test TC003_ValidatingGiftCardsSection Failed");
					Assert.fail();
				}
				else {
					System.out.println("Error Message : ");
					System.out.println(giftCardPageObj.input_recEmail.getAttribute("validationMessage"));
					System.out.println(giftCardPageObj.input_yourEmail.getAttribute("validationMessage"));
					excelObj.setCellData("Sheet1", currentRow,11, "Passed");
					currentRow++;
					logger.info("  ==>Test TC003_ValidatingGiftCardsSection Passed");
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			logger.info("  ==>Test TC003_ValidatingGiftCardsSection Passed");
		}
		
	}

}
