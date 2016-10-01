package com.example.android.java8feature.model;

import com.example.android.java8feature.utils.HttpLoggingInterceptor;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Description Please
 *
 * @author pranit
 * @version 1.0
 * @since 11/9/16
 */

public interface JsonPlaceHolderService {

    @GET("todos")
    Observable<List<ToDo>> getAllToDos();

    class Factory {
        public static JsonPlaceHolderService create() {
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder.addInterceptor(getHttpLoggingInterceptor());
            okHttpBuilder.addInterceptor(getHeaderInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .client(okHttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(JsonPlaceHolderService.class);
        }

        private static Interceptor getHeaderInterceptor() {
            // checked exception problem with lambda http://stackoverflow.com/a/27668305
            return (chain) -> {
                Request orgRequest = chain.request();
                final Request newRequest = orgRequest.newBuilder()
                        .build();
                return chain.proceed(newRequest);
            };
        }

        private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return httpLoggingInterceptor;
        }
    }
}
