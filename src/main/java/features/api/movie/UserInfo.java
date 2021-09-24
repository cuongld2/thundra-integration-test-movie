package features.api.movie;

import common.ApiUtil;
import common.CoreConstant;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;

public class UserInfo {

    public Response createUser(models.features.movie.UserInfo userInfo){

        return given().baseUri(CoreConstant.MOVIE_HOST + CoreConstant.MOVIE_API_USERINFO)
                .header("Content-Type", "application/json")
                .body(ApiUtil.parseObjectToJSON(userInfo, models.features.movie.UserInfo.class))
                .when()
                .post();

    }

}
