package inradar.linkedin.com.inradar;

import android.net.Uri;
import android.os.Bundle;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * Created by bili on 11/9/14.
 */
public class Utils {
    public static String getStringFromInputStream(InputStream stream) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }

    public static String buildUriWithQueryParams(String baseUrl, Bundle params) {
        Uri.Builder builder = Uri.parse(baseUrl).buildUpon();

        for (String name: params.keySet()) {
            builder.appendQueryParameter(name, params.get(name).toString());
        }

        String url = builder.build().toString();
        return url;
    }
}
