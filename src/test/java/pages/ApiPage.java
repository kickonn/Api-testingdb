package pages;


import appmanager.Assertions;
import appmanager.HelperBase;
import appmanager.util.json.JSONHelper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static appmanager.ApplicationManager.getWebDriver;
import static appmanager.ApplicationManager.reader;
import static appmanager.ExtentCucumberFormatter.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

//import static io.restassured.RestAssured.given;

public class ApiPage extends HelperBase {


    @Autowired
    JSONHelper jsonHelper;

    ApiPage() {

    }

/*    static String accessToken;

    public String getAccessToken(String baseURI, String strUserName, String strPassWord) {
        accessToken = "";
        try {
            RestAssured.baseURI = baseURI;
            Response response = RestAssured.given().relaxedHTTPSValidation()
                    .queryParam("username", strUserName)
                    .queryParam("password", strPassWord)
                    .header("Content-Type", "application/json")
                    .when()
                    .post("/login");
            accessToken = response.getBody().jsonPath().get("accessToken");
            testStepInfo("Retreived Access Token for the user " + strUserName + " is " + accessToken);


        } catch (Exception ex) {
            testStepJsonFailed("Unable to retrieve the access token ->" + ex.getMessage());
        }
        return accessToken;
    }

    public String getAccessTokenOAuth2(String baseURI, String id, String secret) {
        accessToken = "";
        try {
            RestAssured.baseURI = "http://sqe1-1link.fhlbny.net";
            Response response = RestAssured.given().auth().preemptive()
                    .basic("id-907f8e44-f9df-4cf9-cb8a-746eb8e6d043", "secret-19eca956-6e37-d6ce-5acb-2e7860de79aa")

                    .formParam("grant_type", "client_credentials")

                    .when()
                    .post("/o/oauth2/token");
            accessToken = response.getBody().jsonPath().get("access_token");
            response.prettyPrint();
            System.out.print(Response);
            testStepInfo("Retreived Access Token for the client credentials " + " is " + accessToken);


        } catch (Exception ex) {
            testStepJsonFailed("Unable to retrieve the access token ->" + ex.getMessage());
        }
        return accessToken;
    }*/


    static RequestSpecification httpsRequest;
    static RequestSpecification httpsRequestput;
    static RequestSpecification httpsRequestget;
    static RequestSpecification httpsRequestdel;
    static RequestSpecification httpsRequestGetByID;


    static String baseURI;

    public ApiPage(String baseURI) {
        ApiPage.baseURI = baseURI;
        try {
            RestAssured.baseURI = baseURI;
            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("Content-Type", "application/json");

            httpsRequest = RestAssured.given().relaxedHTTPSValidation()

                    .headers(requestHeaders);

            httpsRequestput = RestAssured.given().relaxedHTTPSValidation()

                    .headers(requestHeaders);
            httpsRequestget = RestAssured.given().relaxedHTTPSValidation()
                    .headers(requestHeaders);
            httpsRequestdel = RestAssured.given().relaxedHTTPSValidation()

                    .headers(requestHeaders);

            httpsRequestGetByID = RestAssured.given().relaxedHTTPSValidation().
                    headers(requestHeaders);

        } catch (Exception e) {

            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }

    public static String Fn;
    public static String Ln;

    public void fakedata() {
        Faker faker = new Faker();
        Fn = faker.name().firstName();
        Ln = faker.name().lastName();

    }


    public void validateresponseCode() {
        try {
            if (Response.getStatusCode()==200) {
//            if ((Status.SUCCESS.matches(Response.statusCode()))) {
                testStepInfo("Response Code is : " + Response.statusCode());
            } else {
                testStepJsonFailed("Response Code is not 201/200 , instead it is->" + Response.statusCode());
            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }

    public void validateBodyresponse(String type) {
        try {
            String[] node = {"firstName", "lastName", "emailId"};
            AtomicInteger i = new AtomicInteger();

            switch (type) {

                case "post":
                case "put":
                    Map<String, Object> body = Response.then().extract().body().as(new TypeRef<Map<String, Object>>() {
                    });

                    body.entrySet().stream().skip(1).map(Map.Entry::getValue).
                            forEach(s -> {
                                Assertions.getInstance().assertTrue((value.get(node[i.get()]).equals(s)), "Successfully validated " + node[i.get()] + " as : i/p value---> " + value.get(node[i.get()]) + " " + "and response value---> " + s);

                                i.getAndIncrement();

                            });
                    testStepInfo("successfully created employee with a id :" + Response.body().jsonPath().get("id"));
                    break;


                case "del":
                    try {
                        Response.then().assertThat().body("deleted", equalTo(true)).log().body();
                        testStepJsonPassed("employee deleted--->  " + Response.getBody().prettyPrint());
                        testStepInfo(" employee with id " + id + " deleted  successfully and  validated with status code:" + Response.getStatusCode() + " and status:" + Response.getBody().jsonPath().get("deleted"));
                        break;
                    } catch (Exception e) {
                        testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
                    }
                case "getbyId":
                    Response.then().assertThat().body("size()", equalTo(4)).assertThat().body("id", is(id)).log().body();
                    testStepJsonPassed("employee--->  " + Response.body().prettyPrint());
                    break;


                case "get":
                    List<Map<String, Object>> emp = Response.then().extract().body().as(new TypeRef<List<Map<String, Object>>>() {
                    });
                    ArrayList<Object> AllEmpId = Response.getBody().jsonPath().get("id");
                    id = (int) AllEmpId.get(getRandomInt(0, AllEmpId.size() - 2));
                    testStepInfo("Selected employee id is--->" + id);
                    emp.forEach(s -> testStepJsonPassed("employee--->  " + s));
                    break;


            }
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }


    static int id;

    static Response Response;

    JSONObject value;


    public Response ApiRequest(String jsonpath, String type) {
        try {

            jsonHelper.init(jsonpath);
            JSONObject jsonobject;
            fakedata();
                /*JSONObject jsonobject = (JSONObject) jsonparser.parse(new FileReader(jsonpath));
                name = (String) jsonobject.replace("thirdPartyName", "Apithirdparty" + getRandomNumbericString(3));*///we can use this when json file has only one payload and we dont need JSONArray
            switch (type) {
                case "post":

                    jsonobject = (JSONObject) jsonHelper.getJSONObject(0);

                    jsonobject.replace("firstName", Fn);
                    jsonobject.replace("lastName", Ln);
                    jsonobject.replace("emailId", Ln + Fn + "@gmail.com");

                    value = jsonobject;
                    httpsRequest.body(value.toString());
                    Response = httpsRequest.when().post(reader.get("Resource"));
                    System.out.println(Response.body().asString());
                    id = Response.getBody().jsonPath().get("id");
                    break;


                case "put":

                    jsonobject = (JSONObject) jsonHelper.getJSONObject(0);
                    jsonobject.replace("firstName", Fn + getRandomNumbericString(3));
                    jsonobject.replace("lastName", Ln + getRandomNumbericString(3));
                    jsonobject.replace("emailId", Ln + Fn + getRandomNumbericString(3) + "@gmail.com");
                    value = jsonobject;
                    httpsRequestput.body(value.toString());
                    Response = httpsRequestput.when().put(reader.get("Resource") + "/" + id);
                    id = Response.getBody().jsonPath().get("id");
                    System.out.println(Response.getBody().asString());


                    break;

                case "get":
                    Response = httpsRequestget.when().get(reader.get("Resource"));
                    System.out.println(Response.getBody().asString());
                    break;

                case "del":
                    Response = httpsRequestdel.when().delete(reader.get("Resource") + "/" + id);
                    System.out.println(Response.getBody().asString());
                    break;


                case "getbyId":
                    Response = httpsRequestget.when().get(reader.get("Resource") + "/" + id);
                    System.out.println(Response.getBody().asString());
                    break;

            }

        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }

        return Response;
    }


}





