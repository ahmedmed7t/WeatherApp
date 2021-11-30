package com.medhat.weatherapp.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.medhat.weatherapp.base.retrofit.RetrofitService;
import com.medhat.weatherapp.base.room.AppLocalDB;
import com.medhat.weatherapp.base.room.WeatherDao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    private final Map<String, RetrofitService> mServices = new HashMap<>();
    private final String url = "https://api.openweathermap.org";
    private final String api_key = "72067ca3a1b1091a10db6a82ddd6b8a0";
    private final int MODE_PRIVATE = 0;

    @Provides
    @Singleton
    public RetrofitService getByLocationHelper(){
        Interceptor interceptor1 = chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("appid",api_key).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .addInterceptor(interceptor1).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mServices.put(url, retrofit.create(RetrofitService.class));

        return mServices.get(url);
    }

    @Provides
    @Singleton
    public Realm getRealmDB(){
        String realmName = "Weather App";
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name(realmName)
                .build();
        return Realm.getInstance(config);
    }

    @Provides
    @Singleton
    public SharedPreferences getSharedPreferences(@ApplicationContext Context context){
        return context.getSharedPreferences("UserData", MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public WeatherDao getLocalDB(@ApplicationContext Context context){
        AppLocalDB db = Room.databaseBuilder(context,
                AppLocalDB.class, "Local-Weather-App").build();
        return db.weatherDao();
    }
}
