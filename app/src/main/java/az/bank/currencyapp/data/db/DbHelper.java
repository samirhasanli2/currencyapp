package az.bank.currencyapp.data.db;

import java.util.List;

import az.bank.currencyapp.data.models.RateBody;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface DbHelper {

    Single<List<RateBody>> getAllRatesFromDB();

    Single<Boolean> isRateEmpty();

    Single<Boolean> saveRate(RateBody rateLocalModel);

    Single<Boolean> saveRateList(List<RateBody> rateLocalModelList);

}