package az.bank.currencyapp.presentation.main;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.stream.Collectors;

import javax.inject.Inject;

import az.bank.currencyapp.deps.DaggerAppComponent;
import az.bank.currencyapp.di.AppModule;
import az.bank.currencyapp.di.NetworkModule;
import az.bank.currencyapp.models.RateBody;
import az.bank.currencyapp.network.DataManager;
import az.bank.currencyapp.network.NetworkService;
import az.bank.currencyapp.util.ErrorHandler;
import az.bank.currencyapp.util.Util;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    void fetchData() {
        compositeDisposable.add(networkService.getAllRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allRatesResponse -> {
                    RateBody azn = new RateBody("AZN", 1.0, 1.0, 1.0, 1.0, "Uni Bank");
                    allRatesResponse.getBody().add(azn);

                    // Burada apiden coxsayli banklarin valyutasi gelir. Unibanki saxlayiram ve elave yalnishliq olaraq number de gelir onu da filter edirem.
                    // Istifade etdiyim bezi sheyler SDK versiyani yukseltdi onu azaltmaga vaxt olmadi.
                    mainView.allRateResponseSuccess(allRatesResponse.getBody().stream().filter(p -> p.getName().equals("Uni Bank")).filter(p -> !Util.isNumeric(p.getCode())).collect(Collectors.toList()));
                }, throwable -> {
                    mainView.responseError(errorHandler.getThrowError(throwable));
                }));

    }




}
