package az.bank.currencyapp.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import az.bank.currencyapp.data.db.DbHelper;
import az.bank.currencyapp.data.db.models.RateLocalModel;
import az.bank.currencyapp.data.models.AllRatesResponse;
import az.bank.currencyapp.data.models.CurrencyResponse;
import az.bank.currencyapp.data.network.NetworkService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Singleton
public class DataManager {
    public NetworkService networkService;

    private static DataManager dataManager;
    private final DbHelper dbHelper;

    @Inject
    public DataManager(NetworkService networkService, DbHelper dbHelper) {
        this.networkService = networkService;
        this.dbHelper = dbHelper;
    }

    public Single<List<CurrencyResponse>> getCurrency(String from, String to) {
        return networkService.getCurrency(from, to);
    }

    public Observable<List<RateLocalModel>> getAllRatesFromDB() {
        return dbHelper.getAllRatesFromDB();
    }

    public Observable<Boolean> saveRateList(List<RateLocalModel> rateLocalModelList) {
        return dbHelper.saveRateList(rateLocalModelList);
    }

    public Single<AllRatesResponse> getAllRates() {
        return networkService.getAllRates();
    }
}
