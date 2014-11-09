/*
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

*/
/**
 * Created by bili on 11/8/14.
 *//*

public class RestService extends IntentService {
    private static final String TAG = "LinkedInLoginFragment";
    private static final String EXTRA_REST_RESULT_RECEIVER = "com.linkedin.inradar.rest_result_receiver";
    private static final String EXTRA_REST_ENDPOINT = "com.linkedin.inradar.rest_endpoint";
    private static final String EXTRA_REST_VERB = "com.linkedin.inradar.rest_verb";
    private static final String EXTRA_REST_PARAMS = "com.linkedin.inradar.rest_params";

    public static enum RestServiceVerb {
        GET, POST
    }

    RestService

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri action = intent.getData();
        Bundle extras = intent.getExtras();

        if (extras == null || action == null ||
                !extras.containsKey(EXTRA_REST_RESULT_RECEIVER) ||
                !extras.containsKey(EXTRA_REST_ENDPOINT)) {
            Log.e(TAG, "missing extras or action");
            return;
        }

        String endpoint = extras.getString(EXTRA_REST_ENDPOINT)
        RestServiceVerb verb = RestServiceVerb.values()[extras.getInt(EXTRA_REST_VERB, RestServiceVerb.GET.ordinal())];
        Bundle params = extras.getParcelable(EXTRA_REST_PARAMS);
        ResultReceiver receiver = extras.getParcelable(EXTRA_REST_RESULT_RECEIVER);

        try {
            HttpRequestBase request = null;
            switch (verb) {
                case GET: {
                    request = new HttpGet(endpoint);
                    // add params later
                }
                break;
            }

            if (request != null) {
                HttpClient client = new DefaultHttpClient();

                Log.d(TAG, "Executing requet: " + verb.name() + " action: " + action.toString());

                HttpResponse response = client.execute(request);

                HttpEntity responseEntity = response.getEntity();
                StatusLine responseStatus = response.getStatusLine();
                int statusCode = responseStatus != null ? responseStatus.getStatusCode() : 0;

                Log.d(TAG, "" + statusCode);

                if (responseEntity != null) {
                    Log.d(TAG, "has entity");
                } else {
                    Log.d(TAG, "no entity");
                }
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
