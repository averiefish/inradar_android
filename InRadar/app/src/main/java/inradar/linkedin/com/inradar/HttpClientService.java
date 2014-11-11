package inradar.linkedin.com.inradar;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * Created by bili on 11/8/14.
 */
public class HttpClientService extends IntentService {
    private static final String TAG = "HttpClientService";

    public static final String EXTRA_REST_ENDPOINT = "com.linkedin.inradar.rest_endpoint";
    public static final String EXTRA_REST_RESULT_RECEIVER = "com.linkedin.inradar.rest_result_receiver";
    public static final String EXTRA_REST_PARAMS = "com.linkedin.inradar.rest_params";
    public static final String EXTRA_REST_RESULT = "com.linkedin.inradar.rest_result";

//    private static final String EXTRA_REST_RESULT_RECEIVER = "com.linkedin.inradar.rest_result_receiver";
//    private static final String EXTRA_REST_ENDPOINT = "com.linkedin.inradar.rest_endpoint";
//    private static final String EXTRA_REST_VERB = "com.linkedin.inradar.rest_verb";
//    private static final String EXTRA_REST_PARAMS = "com.linkedin.inradar.rest_params";

    private static final String ENDPOINT = "http://www.google.com";

    public HttpClientService() {
        super("HttpClientService");
    }

    public static enum RestServiceVerb {
        GET, POST
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        String endpoint = extras.getString(EXTRA_REST_ENDPOINT);
        ResultReceiver receiver = extras.getParcelable(EXTRA_REST_RESULT_RECEIVER);
        Bundle param = extras.getBundle(EXTRA_REST_PARAMS);
        Log.d(TAG, endpoint);
        try {
            HttpRequestBase request = null;
            String url = Utils.buildUriWithQueryParams(endpoint, param);
            request = new HttpGet(url);

            if (request != null) {
                HttpClient client = new DefaultHttpClient();

                Log.d(TAG, "Executing request");

                HttpResponse response = client.execute(request);

                HttpEntity responseEntity = response.getEntity();
                StatusLine responseStatus = response.getStatusLine();
                int statusCode = responseStatus != null ? responseStatus.getStatusCode() : 0;

                Log.d(TAG, "" + statusCode);

                if (responseEntity != null) {
                    Log.d(TAG, "has entity");
                    InputStream contentInputStream = responseEntity.getContent();
                    String content = Utils.getStringFromInputStream(contentInputStream);
                    Log.d(TAG, content);
                    Bundle resultData = new Bundle();
                    resultData.putString(EXTRA_REST_RESULT, content);
                    receiver.send(statusCode, resultData);
                } else {
                    Log.d(TAG, "no entity");
                    receiver.send(statusCode, null);
                }
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
