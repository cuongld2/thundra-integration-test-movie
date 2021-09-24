package common.db.movie;

import common.db.Base;
import common.db.HibernateEdition;



public abstract class Movie {

  private Base base = new Base();

  protected static HibernateEdition movieDbConnect = new HibernateEdition(
      "db/hibernate-moviedb.cfg.xml");

}
