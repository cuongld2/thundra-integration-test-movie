package api.movie;

import common.BaseTest;
import common.db.movie.MovieSimple;
import features.api.movie.UserInfo;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class UserInfoTest extends BaseTest {

    private UserInfo userInfoApi = new UserInfo();
    private models.features.movie.UserInfo userInfoModel = new models.features.movie.UserInfo();
    private MovieSimple movieSimple = new MovieSimple();

    @Test
    public void test_create_new_user_success_case(){

        String username = "cuongld@abc.com";
        userInfoModel.setFullname("Le Dinh Cuong");
        userInfoModel.setUsername(username);
        userInfoModel.setPassword("cuongld");
        Response response = userInfoApi.createUser(userInfoModel);
        softAssertImpl.assertThat("Verify status code is 200", response.getStatusCode(), 200);
        //Clear db

        movieSimple.clearUserInfoByUsername(username);
        softAssertImpl.assertAll();

    }

}
