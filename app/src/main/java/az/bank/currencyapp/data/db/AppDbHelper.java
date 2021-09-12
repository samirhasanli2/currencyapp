package az.bank.currencyapp.data.db;


import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;


import az.bank.currencyapp.data.db.models.DaoMaster;
import az.bank.currencyapp.data.db.models.DaoSession;
import az.bank.currencyapp.data.db.models.RateLocalModel;
import io.reactivex.rxjava3.core.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }


    @Override
    public Observable<List<RateLocalModel>> getAllRatesFromDB() {
        return Observable.fromCallable(new Callable<List<RateLocalModel>>() {
            @Override
            public List<RateLocalModel> call() throws Exception {
                return mDaoSession.getRateLocalModelDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isRateEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getRateLocalModelDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveRate(final RateLocalModel option) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRateLocalModelDao().insertInTx(option);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveRateList(final List<RateLocalModel> questionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRateLocalModelDao().insertInTx(questionList);
                return true;
            }
        });
    }
}