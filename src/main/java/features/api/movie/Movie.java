package features.api.movie;

import common.ApiUtil;
import common.CoreConstant;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.given;

public class Movie {

    public Response createMovie(models.features.movie.Movie movie, String token){

        return given().baseUri(CoreConstant.MOVIE_HOST + CoreConstant.MOVIE_API_MOVIE)
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .body(ApiUtil.parseObjectToJSON(movie, models.features.movie.Movie.class))
                .when()
                .post();

    }

    public Response getMovieById(models.features.movie.Movie movie, String token, String movieId){

        return given().baseUri(CoreConstant.MOVIE_HOST + CoreConstant.MOVIE_API_MOVIE +"/"+movieId)
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .body(ApiUtil.parseObjectToJSON(movie, models.features.movie.Movie.class))
                .when()
                .get();
    }
}
