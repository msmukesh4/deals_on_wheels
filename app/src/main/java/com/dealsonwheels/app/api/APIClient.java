package com.dealsonwheels.app.api;

import com.dealsonwheels.app.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mukesh on 11/11/17.
 */

public class APIClient {
    private static Retrofit retrofit = null;
    public static int LOG_NOT_NEEDED = 0;
    public static int LOG_REQ_RES = 1;
    public static int LOG_REQ_RES_BODY_HEADERS = 2;
    public static int LOG_REQ_RES_HEADERS_ONLY = 3;

    public static Retrofit getClient(int logLevel){

        if (null == retrofit){

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            if (logLevel == LOG_NOT_NEEDED)
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            else if (logLevel == LOG_REQ_RES)
                            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            else if (logLevel == LOG_REQ_RES_BODY_HEADERS)
                            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            else if (logLevel == LOG_REQ_RES_HEADERS_ONLY)
                            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            else
                throw new IllegalStateException("Log level must be 0/1/2/3");

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    public static APIInterface getAPIService(int logLevel){
        return getClient(logLevel).create(APIInterface.class);
    }




}
