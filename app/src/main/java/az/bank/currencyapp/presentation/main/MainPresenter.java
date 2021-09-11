package az.bank.currencyapp.presentation.main;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import az.bank.currencyapp.deps.DaggerAppComponent;
import az.bank.currencyapp.di.AppModule;
import az.bank.currencyapp.di.NetworkModule;
import az.bank.currencyapp.models.RateBody;
import az.bank.currencyapp.network.DataManager;
import az.bank.currencyapp.network.NetworkService;
import az.bank.currencyapp.util.ErrorHandler;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter {

    @Inject
    public NetworkService networkService;
    private DataManager dataManager;

    @Inject
    public ErrorHandler errorHandler;

    private MainView mainView;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainPresenter(MainView mainView, Application application) {
        DaggerAppComponent.builder().networkModule(new NetworkModule()).appModule(new AppModule(application)).build().inject(this);


        this.mainView = mainView;
    }

    void fetchData() {
        compositeDisposable.add(networkService.getCurrency("AZN", "USD")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currencyResponses -> {
                    if(currencyResponses.size() > 0) {
                        mainView.responseSuccess(currencyResponses.get(0));
                    }else {
                        mainView.responseError(errorHandler.getStringError("currency_wrong"));
                    }
                }, throwable -> {
                    mainView.responseError(errorHandler.getThrowError(throwable));
                }));

        compositeDisposable.add(networkService.getAllRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allRatesResponse -> {
                    RateBody azn = new RateBody();

                    mainView.allRateResponseSuccess(allRatesResponse.getBody());
                }, throwable -> {
                    mainView.responseError(errorHandler.getThrowError(throwable));
                }));

    }
}
