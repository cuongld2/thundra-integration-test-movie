package api.movie;

import common.BaseTest;
import common.db.movie.MovieSimple;
import features.api.movie.Authen;
import features.api.movie.UserInfo;
import io.restassured.response.Response;
import models.features.movie.Token;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AuthenTest extends BaseTest {

    private Authen authenApi = new Authen();
    private UserInfo userInfoApi = new UserInfo();
    private models.features.movie.UserInfo userInfoModel = new models.features.movie.UserInfo();
    private models.features.movie.Authen authenModel = new models.features.movie.Authen();
    private MovieSimple movieSimple = new MovieSimple();


    @Test
    public void authen_user_credential_success(){

        String username = RandomStringUtils.randomAlphabetic(8) + "@abc.com";
        String password = RandomStringUtils.randomAlphabetic(4);
        userInfoModel.setFullname(RandomStringUtils.randomAlphabetic(4) + " " + RandomStringUtils.randomAlphabetic(5));
        userInfoModel.setUsername(username);
        userInfoModel.setPassword(password);
        userInfoApi.createUser(userInfoModel);

        authenModel.setUsername(username);
        authenModel.setPassword(password);

        Response response = authenApi.authenUser(authenModel);
        Token actualResponse = response.as(Token.class);
        softAssertImpl.assertThat("Verify token is not null", actualResponse.getToken().isEmpty(),false);

        movieSimple.clearUserInfoByUsername(username);
        softAssertImpl.assertAll();

    }

}
