package az.bank.currencyapp.di;

import android.content.Context;

import javax.inject.Singleton;

import az.bank.currencyapp.BuildConfig;
import az.bank.currencyapp.network.NetworkService;
import az.bank.currencyapp.util.ErrorHandler;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit provideCall() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkService providesNetworkService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }


//    @Provides
//    @Singleton
//    @SuppressWarnings("unused")
//    public Service providesService(NetworkService networkService) {
//        return new Service(networkService);
//    }

}
