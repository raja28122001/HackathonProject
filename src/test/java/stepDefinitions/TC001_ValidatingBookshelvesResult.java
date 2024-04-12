package stepDefinitions;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import factory.BaseClass;
import pageObjects.HomePage;
import pageObjects.ProductsPage;

public class TC001_ValidatingBookshelvesResult extends BaseClass{
	
	@Given("the user search for {string}")
	public void the_user_search_for(String item) {
		try {
			homeObj = new HomePage(driver);
			homeObj.searchItem(item);
		}
		catch(Exception e) {
			logger.error("  ==>Test TC001_ValidateBookshelves Failed");	
		}
	}

	@When("the user performs all conditions as per description")
	public void the_user_performs_all_conditions_as_per_description() throws InterruptedException {
		try {
			logger.info("******TC001_ValidatingBookshelvesResult Started*******");
			logger.info("Searched for bookshelves");
			
			productPageObj = new ProductsPage(driver);
			productPageObj.closePopup();
			logger.info("Closed the ad popup");
			
			productPageObj.clickPrice();
			productPageObj.setPrice();
			logger.info("Clicked price option and setting price");
			
			productPageObj.clickCategory();
			productPageObj.selectBookshelves();
			logger.info("Clicked category option and selecting the bookshelves option ");
			
			productPageObj.selectOutOfStock();
			logger.info("Selected exclude out of stock");
			
			productPageObj.clickSortBy();
			productPageObj.selectHighToLow();
			Thread.sleep(2000);
			logger.info("Clicked sortby option and selected High to Low");
		}
		catch(Exception e) {
			logger.error("  ==>Test TC001_ValidateBookshelves Failed");
		}
	}

	@Then("the user sees the result page of the product")
	public void the_user_sees_the_result_page_of_the_product() {
	   
		try {
			List<String> productDetail = productPageObj.getProductNameAndPrice();
			System.out.println("Product And Price Details : ");
			for(String s : productDetail) {
		        System.out.println(s);
			}
			logger.info("Captured the Product names and prices");
			
			logger.info("Valiating the testCase");
			if(productDetail.size()==3) {
				logger.info(" ==>Test TC001_ValidateBookshelves Passed");
				Assert.assertTrue(true);
			}
			else {
				logger.error("  ==>Test TC001_ValidateBookshelves Failed");
				Assert.fail();
			}	
	  }
	  catch(Exception e) {
			logger.error("  ==>Test TC001_ValidateBookshelves Failed");
	  }
  }
}
