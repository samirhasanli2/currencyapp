package az.bank.currencyapp.presentation.main;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import az.bank.currencyapp.data.db.DbHelper;
import az.bank.currencyapp.data.db.models.RateLocalModel;
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
    public DbHelper dbHelper;

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
        compositeDisposable.add(dataManager.getAllRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(allRatesResponse -> {
                    RateBody azn = new RateBody("AZN", 1.0, 1.0, 1.0, 1.0, "Uni Bank");
                    allRatesResponse.getBody().add(azn);

                    // Burada apiden coxsayli banklarin valyutasi gelir. Unibanki saxlayiram ve elave yalnishliq olaraq number de gelir onu da filter edirem.
                    // Istifade etdiyim bezi sheyler SDK versiyani yukseltdi onu azaltmaga vaxt olmadi.
                    List<RateBody> rateList = allRatesResponse.getBody().stream().filter(p -> p.getName().equals("Uni Bank")).filter(p -> !Util.isNumeric(p.getCode())).collect(Collectors.toList());
                    List<RateLocalModel> rateLocalModelList = new ArrayList<>() ;
                    for (int i = 0; i < rateList.size(); i++) {
                        rateLocalModelList.add(new RateLocalModel(rateList.get(i).getCode(), rateList.get(i).getName(), rateList.get(i).getBuy_cash(), rateList.get(i).getBuy_none_cash(), rateList.get(i).getSell_cash(), rateList.get(i).getSell_none_cash()));
                    }
                    dataManager.saveRateList(rateLocalModelList);
                    mainView.allRateResponseSuccess(rateList);
                }, throwable -> {
                    mainView.responseError(errorHandler.getThrowError(throwable));
                }));

    }




}
