package common.db.movie;

import common.db.Base;

import java.util.List;
import java.util.Map;

public class MovieSimple extends Movie {

    private Base base = new Base();

    public List<Map<String, Object>> getAllUserInfo() throws Exception {

        String query = "select * from user_info";
        return base.executeQuery(movieDbConnect.getSession(), query);
    }

    public void clearUserInfoByUsername(String username) {

        String query = String.format("delete from user_info where username = '%s'", username);
        base.queryNoReturn(movieDbConnect.getSession(), query);
    }

    public void clearMovieByTitle(String title){

        String query = String.format("delete from movie where title = '%s'", title);
        base.queryNoReturn(movieDbConnect.getSession(), query);

    }

}
