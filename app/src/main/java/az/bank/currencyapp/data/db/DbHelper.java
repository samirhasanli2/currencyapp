package az.bank.currencyapp.data.db;

import java.util.List;

import az.bank.currencyapp.data.db.models.RateLocalModel;
import io.reactivex.rxjava3.core.Observable;

public interface DbHelper {

    Observable<List<RateLocalModel>> getAllRatesFromDB();

    Observable<Boolean> isRateEmpty();

    Observable<Boolean> saveRate(RateLocalModel rateLocalModel);

    Observable<Boolean> saveRateList(List<RateLocalModel> rateLocalModelList);

}