package common;

import com.typesafe.config.Config;

public class CoreConstant {

    private static Config conf;

    static {
        conf = Configs.newBuilder()
                .withResource("automation.conf")
                .withResource("automation." + Env.get().getName() + ".conf")
                .build();
    }

    public static final String MOVIE_HOST = conf.getString("localhost.url");
    public static final String MOVIE_API_USERINFO = conf.getString("movie.api.user");
    public static final String MOVIE_API_AUTHEN = conf.getString("movie.api.authen");
    public static final String MOVIE_API_MOVIE = conf.getString("movie.api.movie");


}
