package az.bank.currencyapp.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import az.bank.currencyapp.data.db.AppDbHelper;
import az.bank.currencyapp.data.db.DbHelper;
import az.bank.currencyapp.data.models.AllRatesResponse;
import az.bank.currencyapp.data.models.CurrencyResponse;
import az.bank.currencyapp.data.models.RateBody;
import az.bank.currencyapp.data.network.NetworkService;
import az.bank.currencyapp.util.Util;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class DataManager {
    public NetworkService networkService;
//    public DbHelper dbHelper;
    public AppDbHelper appDbHelper;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static DataManager dataManager;

    @Inject
    public DataManager(NetworkService networkService, AppDbHelper appDbHelper) {
        this.networkService = networkService;
        this.appDbHelper = appDbHelper;
    }

    public Single<List<CurrencyResponse>> getCurrency(String from, String to) {
        return networkService.getCurrency(from, to);
    }

    public Single<List<RateBody>> getAllRatesFromDB() {
        return appDbHelper.getAllRatesFromDB();
    }

    public Single<Boolean> saveRateList(List<RateBody> rateLocalModelList) {
        return appDbHelper.saveRateList(rateLocalModelList);
    }

    public Single<List<RateBody>> getAllRates() {
        return appDbHelper.getAllRatesFromDB()
                .flatMap(rateLocalModels -> {
                    if(rateLocalModels.isEmpty()) {
                        Single<AllRatesResponse> responseSingle = networkService.getAllRates();
                        Single<List<RateBody>> response = responseSingle.flatMap(allRatesResponse -> Single.just(allRatesResponse.getBody()));
                        compositeDisposable.add(response.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(allRatesResponse -> {
                                    saveRateList(allRatesResponse).subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(status -> {
                                            });
                                }));
                        return response;
                    }
                    return Single.just(rateLocalModels);
                });
    }
}
