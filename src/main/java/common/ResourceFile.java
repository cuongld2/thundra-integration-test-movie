package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceFile {

  private Properties prop = new Properties();

  public ResourceFile(String path) throws IOException {
    InputStream input = new FileInputStream(path);
    // load a properties file
    prop.load(input);
    if (input != null) {
      input.close();
    }
  }

  public String getValue(String key) {
    return prop.getProperty(key);
  }

  public Properties getProp() {
    return prop;
  }

}
