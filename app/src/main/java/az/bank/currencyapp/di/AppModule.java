package az.bank.currencyapp.di;

import android.app.Application;

import javax.inject.Singleton;

import az.bank.currencyapp.data.db.AppDbHelper;
import az.bank.currencyapp.data.db.DbHelper;
import az.bank.currencyapp.data.db.DbOpenHelper;
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

    @Provides
    @Singleton
    public AppDbHelper providesAppDbHelper() {
        return new AppDbHelper(new DbOpenHelper(application.getApplicationContext(), "rate_db"));
    }

}
