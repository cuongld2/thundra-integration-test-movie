package common;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;


public class ApiUtil {

    public static HttpResponse executeHttpPost(String apiURL, String cookies) throws Exception {

        String url = apiURL;
        HttpResponse response = null;

        try {
            CloseableHttpClient client = initCloseableHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("Accept", "application/json");
            post.setHeader("Authorization", cookies);

            int timeout = 60;
            HttpParams httpParams = post.getParams();
            httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
            httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);

            response = client.execute(post);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    /**
     * pricing testing
     *
     * @param apiURL
     * @param cookies
     * @return
     * @throws Exception
     */
    public static HttpResponse executeHttpPostPricing(String apiURL, String cookies,
                                                      List<NameValuePair> params) throws Exception {

        String url = apiURL;
        HttpResponse response = null;

        try {
            CloseableHttpClient client = initCloseableHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Cookie", cookies);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("Accept", "application/json");

            post.setEntity(new UrlEncodedFormEntity(params));

            int timeout = 60;
            HttpParams httpParams = post.getParams();
            httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
            httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);

            response = client.execute(post);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    /**
     * Send GET request without request parameters
     *
     * @param apiURL
     * @param cookies authorization token OATH2
     * @return
     * @throws Exception
     */
    public static HttpResponse executeHttpGet(String apiURL, String cookies) throws Exception {

        String url = apiURL;
        HttpResponse response = null;

        try {
            CloseableHttpClient client = initCloseableHttpClient();
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpget.setHeader("Accept", "application/json");
            httpget.setHeader("Authorization", cookies);

            int timeout = 60;
            HttpParams httpParams = httpget.getParams();
            httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
            httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);

            response = client.execute(httpget);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    /**
     * Send GET request without authorization
     *
     * @param apiURL
     * @return
     * @throws Exception
     */
    public static HttpResponse executeHttpGet(String apiURL) throws Exception {

        String url = apiURL;
        HttpResponse response = null;

        try {
            CloseableHttpClient client = initCloseableHttpClient();
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpget.setHeader("Accept", "application/json");

            int timeout = 60;
            HttpParams httpParams = httpget.getParams();
            httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
            httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);

            response = client.execute(httpget);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    /**
     * Initiation CloseableHttpClient
     *
     * @return CloseableHttpClient
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws KeyStoreException
     */
    private static CloseableHttpClient initCloseableHttpClient() throws NoSuchAlgorithmException,
            KeyManagementException, KeyStoreException {
        CloseableHttpClient client =
                HttpClients.custom().setHostnameVerifier(new AllowAllHostnameVerifier())
                        .setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                            public boolean isTrusted(X509Certificate[] arg0, String arg1)
                                    throws CertificateException {
                                return true;
                            }
                        }).build()).build();
        return client;
    }

    public static String getStatusCodeFromResponse(HttpResponse response) {
        return response.getStatusLine().getStatusCode() + "";
    }

    public static String extractJSONFromResponse(HttpResponse response) throws Exception {
        StringBuffer json = new StringBuffer();
        try {
            BufferedReader rd =
                    new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                json.append(line);
            }
        } catch (Exception e) {
            throw e;
        }
        return json.toString();
    }

    public static String extractDataFromBPMJson(HttpResponse response) throws Exception {
        String originalJson = extractJSONFromResponse(response);
        // System.out.println(originalJson);
        JSONObject jsonObject = new JSONObject(originalJson);
        // System.out.println(jsonObject.toString());
        JSONObject data = jsonObject.getJSONObject("data");
        return data.toString();
    }

    public static <any> Object parseJSONToObject(String json, Class<?> className) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object object = gson.fromJson(json, className);
        return object;
    }

    public static <T> List<T> parseJsonToObjectArray(String json, Class<T[]> className) {
        T[] arr = new Gson().fromJson(json, className);
        return Arrays.asList(arr);
    }

    public static String parseObjectToJSON(Object object, Class<?> className) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object, className);

        return json;
    }


    @Deprecated
    public static String parseObjectToJSON(Object object, java.lang.reflect.Type className,
                                           Boolean ignoreNullValue) {
        Gson gson = null;
        if (ignoreNullValue == true) {
            gson =
                    new GsonBuilder().registerTypeAdapter(className, new IgnoreEmptyAdapter())
                            .setPrettyPrinting().create();
        } else {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }

        String json = gson.toJson(object, className);

        return json;
    }

    /**
     * Send Post request by SOAP
     *
     * @param soapBody
     * @param soapAction api url
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static HttpResponse executeHttpPostforSOAP(String soapBody, String soapAction)
            throws ClientProtocolException, IOException, KeyManagementException,
            NoSuchAlgorithmException, KeyStoreException {
        StringEntity stringEntity = new StringEntity(soapBody.trim(), "UTF-8");
        stringEntity.setChunked(true);

        CloseableHttpClient client = initCloseableHttpClient();
        HttpPost httpPost = new HttpPost(soapAction);
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("Accept", "text/xml");
        httpPost.addHeader("SOAPAction", soapAction);

        int timeout = 60;
        HttpParams httpParams = httpPost.getParams();
        httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
        httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);

        return client.execute(httpPost);
    }

}
