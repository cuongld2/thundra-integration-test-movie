package api.movie;

import common.BaseTest;
import common.db.movie.MovieSimple;
import features.api.movie.Authen;
import features.api.movie.UserInfo;
import io.restassured.response.Response;
import models.features.movie.Movie;
import models.features.movie.Token;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class MovieTest extends BaseTest {

    private static String username;
    private static final Authen authenApi = new Authen();
    private static final UserInfo userInfoApi = new UserInfo();
    private static models.features.movie.UserInfo userInfoModel = new models.features.movie.UserInfo();
    private static models.features.movie.Authen authenModel = new models.features.movie.Authen();
    private static final MovieSimple movieSimple = new MovieSimple();
    private static final Movie movieModel = new Movie();
    private static final features.api.movie.Movie movieApi = new features.api.movie.Movie();
    private static String token;

    private static final String title = "Test Automation using Serenity";
    private static final String content = "A lot of things man";
    private static final String author = "Donald";

    @BeforeClass
    public static void setUp(){

        username = RandomStringUtils.randomAlphabetic(8) + "@abc.com";
        String password = RandomStringUtils.randomAlphabetic(4);
        userInfoModel.setFullname(RandomStringUtils.randomAlphabetic(4) + " " + RandomStringUtils.randomAlphabetic(5));
        userInfoModel.setUsername(username);
        userInfoModel.setPassword(password);
        userInfoApi.createUser(userInfoModel);

        authenModel.setUsername(username);
        authenModel.setPassword(password);

        Response response = authenApi.authenUser(authenModel);
        Token actualResponse = response.as(Token.class);

        token = actualResponse.getToken();


    }

    @Test
    public void create_new_movie(){

        movieModel.setTitle(title);
        movieModel.setContent(content);
        movieModel.setAuthor(author);
        Response responseMovie = movieApi.createMovie(movieModel,token);
        Movie responseMovieActual = responseMovie.as(Movie.class);
        softAssertImpl.assertThat("Verify status code is 200",responseMovie.getStatusCode(), 200);
        softAssertImpl.assertThat("Verify title is correct",responseMovieActual.getTitle(),title);
        softAssertImpl.assertThat("Verify content is correct", responseMovieActual.getContent(), content);
        softAssertImpl.assertThat("Verify id is not null",responseMovieActual.getId().toString().isEmpty(),false);

    }

    @Test
    public void get_movie_by_id(){
        movieModel.setTitle(title);
        movieModel.setContent(content);
        movieModel.setAuthor(author);
        Response responseMovie = movieApi.createMovie(movieModel,token);
        Movie responseMovieActual = responseMovie.as(Movie.class);

        Response responseMovieById = movieApi.getMovieById(movieModel, token, responseMovieActual.getId().toString());
        Movie responseMovieActualById = responseMovieById.as(Movie.class);

        softAssertImpl.assertThat("Verify status code is 200",responseMovieById.getStatusCode(), 200);
        softAssertImpl.assertThat("Verify title is correct",responseMovieActualById.getTitle(),title);
        softAssertImpl.assertThat("Verify content is correct", responseMovieActualById.getContent(), content);
        movieSimple.clearUserInfoByUsername(username);
        movieSimple.clearMovieByTitle(title);
        softAssertImpl.assertAll();

    }

    @AfterClass
    public static void tearDown(){

        movieSimple.clearUserInfoByUsername(username);
        movieSimple.clearMovieByTitle(title);
        softAssertImpl.assertAll();

    }

}
