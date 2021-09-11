package az.bank.currencyapp.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import az.bank.currencyapp.network.DataManager;
import az.bank.currencyapp.util.ErrorHandler;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;
    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ErrorHandler providesErrorHandler() {
        return new ErrorHandler(application.getApplicationContext());
    }

}
