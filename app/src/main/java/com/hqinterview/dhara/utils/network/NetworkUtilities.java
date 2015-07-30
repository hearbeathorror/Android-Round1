package com.hqinterview.dhara.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.hqinterview.dhara.HQInterviewDharaApp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 23-07-2015.
 */
public class NetworkUtilities {
    private static HashMap<String,String> NETWORK_URLS;
    private static final char PARAMETER_DELIMITER = '&';
    private static final char PARAMETER_EQUALS_CHAR = '=';
    private static final String BASE_URL = "http://appcontent.hotelquickly.com/en/1/android/index.json";

    public enum METHOD {
        GET
    }

    public enum NETWORK_FUNCTIONS {
        GetDataFromBase("");

        private String value;

        NETWORK_FUNCTIONS(String value) {
            this.value = value;
        }

        public String getMethodName() {
            return this.value;
        }
    }

    public static void init() {
        NETWORK_URLS = new HashMap<>();
        NETWORK_URLS.put(NETWORK_FUNCTIONS.GetDataFromBase.name(),
                BASE_URL + NETWORK_FUNCTIONS.GetDataFromBase.getMethodName());
    }

    public static String makeWebserviceCall(NetworkUtilities.METHOD method,
                                            NetworkUtilities.NETWORK_FUNCTIONS function,HashMap<String, String> params,
                                            HashMap<String, String> extraParams) {
        String response = "";
        String url = NETWORK_URLS.get(function.name());
        Log.e("dhara", "url : " + url);
        switch (method) {
            case GET:
                response = getData(url, params);
                break;

            default:
                break;
        }
        return response;
    }

    private static String getData(String url, HashMap<String,String> params) {
        String postParameters = "";
        HttpURLConnection urlConnection = null;

        try {
            URL urlToRequest = new URL(url);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            //urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("GET");
            //urlConnection.setRequestProperty("Content-Type",
            //        "text/xml;charset=utf-8");

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                // throw some exception

                InputStream in =
                        new BufferedInputStream(urlConnection.getInputStream());
                String response = getResponseText(in);
                return response;
            }
        }catch (ProtocolException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;

    }

    public String createQueryStringForParameters(Map<String, String> parameters) {
        StringBuilder parametersAsQueryString = new StringBuilder();
        if (parameters != null) {
            boolean firstParameter = true;

            for (String parameterName : parameters.keySet()) {
                if (!firstParameter) {
                    parametersAsQueryString.append(PARAMETER_DELIMITER);
                }
                parametersAsQueryString.append(parameterName)
                        .append(PARAMETER_EQUALS_CHAR)
                        .append(URLEncoder.encode(
                                parameters.get(parameterName)));

                firstParameter = false;
            }
        }
        return parametersAsQueryString.toString();
    }

    private static String getResponseText(InputStream is) {
        String line = "";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * Checks if network is available
     * @return
     */
    public static boolean isNetworkAvailable() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm =
                (ConnectivityManager) HQInterviewDharaApp.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

}