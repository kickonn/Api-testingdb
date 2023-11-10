package tests;

import appmanager.HelperBase;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pages.AdactinPage;

import static appmanager.HelperBase.sleep;

public class adactinstepdefs {

    HelperBase helperBase = new HelperBase();

    @Autowired
    AdactinPage AdactinPage;


    @Given("^User launches the adactin application$")
    public void user_launches_the_adactin_application() throws Throwable {
        helperBase.checkLogInUser();
//        helperBase.launchApplication();
    }


    @When("^User logs into the application with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_logs_into_the_application_with_and(String arg1, String arg2) throws Throwable {
        AdactinPage.loginToApplication(arg1, arg2);

    }

    @Then("^User searches for the hotels by entering all the details$")
    public void user_searches_for_the_hotels_by_entering_all_the_deatils() throws Throwable {
        AdactinPage.enterthehoteldaeatilsandsearch();
    }

    @Then("^User selects the hotel from the list of hotels$")
    public void user_selects_the_hotel_from_the_list_of_hotels() throws Throwable {
        AdactinPage.selectTheHotel();
    }


    @Then("User clicks on booked itinerary")
    public void user_Clicks_On_Booked_Itinerary() {
        helperBase.clickOn("//a[.='Booked Itinerary']","Booked Itinerary");
    }

    @And("user validates booked itinerary page")
    public void user_validates_Booked_Itinerary_Page() {
        AdactinPage.validatesBookedItinerarypage();
    }
    @And("user validates search hotel page")
    public void user_validates_saerch_hotel_Page() {
        AdactinPage.validatessearchhotelpage();
    }

    @Then("User Books a hotel and validates hotel is booked successfully")
    public void userBooksAHotelAndValidatesHotelIsBookedSuccessfully() {
        AdactinPage.bookTheHotel();
    }
}
