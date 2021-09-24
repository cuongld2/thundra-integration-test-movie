package features.api.movie;

import common.ApiUtil;
import common.CoreConstant;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;

public class Authen {

    public Response authenUser(models.features.movie.Authen authen){

        return given().baseUri(CoreConstant.MOVIE_HOST + CoreConstant.MOVIE_API_AUTHEN)
                .header("Content-Type", "application/json")
                .body(ApiUtil.parseObjectToJSON(authen, models.features.movie.Authen.class))
                .when()
                .post();

    }

    }

