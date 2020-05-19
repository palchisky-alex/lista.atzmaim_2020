package im.atzma.lista2020.appmanager;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.attachment.http.HttpRequestAttachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;

import java.util.UUID;

// This class is used to create Allure reports for Rest Assured tests
public class CustomAllureRestAssured extends AllureRestAssured {

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        RequestSpecification specification = new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .build();

        AllureLifecycle lifecycle = Allure.getLifecycle();
        StepResult stepResult = (new StepResult()).setStatus(Status.PASSED).
                setName(String.format("%s: %s", requestSpec.getMethod(), requestSpec.getURI(), requestSpec.getRequestParams()));
        lifecycle.startStep(UUID.randomUUID().toString(), stepResult);

        Response response;
        try {
            response = super.filter(requestSpec, responseSpec, filterContext);
        } finally {
            lifecycle.stopStep();
        }
        return response;


    }

}
