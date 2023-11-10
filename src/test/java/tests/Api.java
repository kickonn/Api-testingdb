
package tests;

import appmanager.HelperBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import pages.ApiPage;

import static appmanager.ApplicationManager.reader;

public class Api extends TestBase {
    HelperBase helperBase = new HelperBase();
    @Autowired
    ApiPage ApiPage;


    @Given("^User creates reference with RestAPI BaseURI : \"([^\"]*)\"$")
    public void user_creates_reference_with_RestAPI_BaseURI(String arg1) throws Throwable {
        new ApiPage(arg1);
    }

//    @When("^User Posts the Api request for the Post Url : \"([^\"]*)\" and \"(.+?)\" and \"(.+?)\"$")
//    public void user_Posts_the_Api_request_for_the_Post_Url(String arg1, String arg2, String arg3) throws Throwable {
//
//        ApiPage.ApiRequest(arg1, arg2, arg3);
//
//    }

    @Then("^User Validates the Response Code and body for the request:\"(.+?)\"$")
    public void user_Validates_the_Response_Code_and_body_for_the_request(String arg1) throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse(arg1);

    }


//    @When("^User Put the Api request for the Put Url : \"([^\"]*)\" and \"(.+?)\" and \"(.+?)\"$")
//    public void user_Put_the_Api_request_for_the_Put_Url(String arg1, String arg2, String arg3) throws Throwable {
//        ApiPage.ApiRequest(arg1, arg2, arg3);
//    }


//    @Then("^User Get the Api request for Get Url :\"([^\"]*)\" and \"(.+?)\" and \"(.+?)\"$")
//    public void user_Get_the_Api_request_for_Get_Url(String arg1, String arg2, String arg3) throws Throwable {
//        ApiPage.ApiRequest(arg1, arg2, arg3);
//    }
//
//
//    @Then("^User Del the Api request for det Url :\"([^\"]*)\" and \"(.+?)\" and \"(.+?)\"$")
//    public void user_Del_the_Api_request_for_Get_Url(String arg1, String arg2, String arg3) throws Throwable {
//        ApiPage.ApiRequest(arg1, arg2, arg3);
//    }

    @Then("^User Validates the Del Response Code for the request$")
    public void user_Validates_the_Del_Response_Code_for_the_request() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("del");

    }

    @Then("^User Validates the post Response Code for the request$")
    public void user_Validates_the_post_Response_Code_for_the_request() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("post");

    }

    @Then("^User Validates the get Response Code for the request$")
    public void user_Validates_the_get_Response_Code_for_the_request() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("get");

    }

    @Then("^User Validates the put Response Code for the request$")
    public void user_Validates_the_put_Response_Code_for_the_request() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("put");

    }


    @Given("^User creates reference with RestAPI BaseURI$")
    public void user_creates_reference_with_RestAPI_BaseURI() throws Throwable {
        new ApiPage(reader.get("BaseURI"));
    }

    @When("^User creates the employee:\"([^\"]*)\"$")
    public void user_creates_the_employee(String arg1) throws Throwable {
        ApiPage.ApiRequest(arg1, "post");
    }

    @Then("^User Validates the Response Code and body of the created employee$")
    public void user_Validates_the_Response_Code_and_body_of_the_created_employee() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("post");
    }

    @When("^User search for the created employee:\"([^\"]*)\"$")
    public void user_search_for_the_created_employee(String arg1) throws Throwable {
        ApiPage.ApiRequest(arg1, "getbyId");
    }

    @When("^User search for all the created employee:\"([^\"]*)\"$")
    public void user_search_for_all_the_created_employee(String arg1) throws Throwable {
        ApiPage.ApiRequest(arg1, "get");
    }

    @Then("^User Validates the Response Code and body of the searched list of employee$")
    public void user_Validates_the_Response_Code_and_body_of_the_searched_list_of_employee() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("get");
    }
@Then("^User Validates the Response Code and body of the searched employee$")
    public void user_Validates_the_Response_Code_and_body_of_the_searched_employee() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("getbyId");
    }

    @When("^User updates the searched employee:\"([^\"]*)\"$")
    public void user_updates_the_searched_employee(String arg1) throws Throwable {
        ApiPage.ApiRequest(arg1, "put");
    }

    @Then("^User Validates the Response Code and body of the updated employee$")
    public void user_Validates_the_Response_Code_and_body_of_the_updated_employee() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("put");
    }

    @When("^User delete the updated employee:\"([^\"]*)\"$")
    public void user_delete_the_updated_employee(String arg1) throws Throwable {
        ApiPage.ApiRequest(arg1, "del");
    }

    @Then("^User Validates the Response Code and body of the deleted employee$")
    public void user_Validates_the_Response_Code_and_body_of_the_deleted_employee() throws Throwable {
        ApiPage.validateresponseCode();
        ApiPage.validateBodyresponse("del");
    }


}