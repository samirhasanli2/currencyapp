package az.bank.currencyapp.deps;

import javax.inject.Singleton;

import az.bank.currencyapp.di.AppModule;
import az.bank.currencyapp.di.NetworkModule;
import az.bank.currencyapp.presentation.main.MainActivity;
import az.bank.currencyapp.presentation.main.MainPresenter;
import dagger.Component;

@Singleton
@Component(modules = { NetworkModule.class, AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
}