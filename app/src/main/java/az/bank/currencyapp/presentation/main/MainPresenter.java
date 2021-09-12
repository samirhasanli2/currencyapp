package az.bank.currencyapp.presentation.main;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.common.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import az.bank.currencyapp.data.db.AppDbHelper;
import az.bank.currencyapp.data.db.DbHelper;

import az.bank.currencyapp.deps.DaggerAppComponent;
import az.bank.currencyapp.di.AppModule;
import az.bank.currencyapp.di.NetworkModule;
import az.bank.currencyapp.data.models.RateBody;
import az.bank.currencyapp.data.DataManager;
import az.bank.currencyapp.data.network.NetworkService;
import az.bank.currencyapp.util.ErrorHandler;
import az.bank.currencyapp.util.Util;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter {

    @Inject
    public NetworkService networkService;
    @Inject
    public AppDbHelper appDbHelper;

    private DataManager dataManager;

    @Inject
    public ErrorHandler errorHandler;

    private MainView mainView;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainPresenter(MainView mainView, Application application) {
        DaggerAppComponent.builder().networkModule(new NetworkModule()).appModule(new AppModule(application)).build().inject(this);
        dataManager = new DataManager(networkService, appDbHelper);

        this.mainView = mainView;
    }


    void fetchData() {
        compositeDisposable.add(dataManager.getAllRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allRatesResponse -> {
                    RateBody azn = new RateBody("AZN", 1.0, 1.0, 1.0, 1.0, "Uni Bank");
                    allRatesResponse.add(azn);

                    // Burada apiden coxsayli banklarin valyutasi gelir. Unibanki saxlayiram ve elave yalnishliq olaraq number de gelir onu da filter edirem.
                    List<RateBody> rateList = new ArrayList<RateBody>();
                    for (int i = 0; i < allRatesResponse.size(); i++) {
                        if(allRatesResponse.get(i).getName().equals("Uni Bank") && !Util.isNumeric(allRatesResponse.get(i).getCode())) {
                            rateList.add(allRatesResponse.get(i));
                        }
                    }
                    mainView.allRateResponseSuccess(rateList);
                }, throwable -> {
                    mainView.responseError(errorHandler.getThrowError(throwable));
                }));

    }




}
