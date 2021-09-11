package az.bank.currencyapp.network;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import az.bank.currencyapp.models.AllRatesResponse;
import az.bank.currencyapp.models.CurrencyResponse;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class DataManager {
    public NetworkService networkService;

    private static DataManager dataManager;

    @Inject
    public DataManager(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Single<List<CurrencyResponse>> getCurrency(String from, String to) {
        return networkService.getCurrency(from, to);
    }

    public Single<AllRatesResponse> getAllRates() {
        return networkService.getAllRates();
    }
}
