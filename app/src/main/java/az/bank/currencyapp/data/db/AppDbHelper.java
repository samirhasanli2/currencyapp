package az.bank.currencyapp.data.db;


import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;


import az.bank.currencyapp.data.models.DaoMaster;
import az.bank.currencyapp.data.models.DaoSession;
import az.bank.currencyapp.data.models.RateBody;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();

    }


    @Override
    public Single<List<RateBody>> getAllRatesFromDB() {
        return Single.fromCallable(new Callable<List<RateBody>>() {
            @Override
            public List<RateBody> call() throws Exception {
                Log.e("Daooooo", mDaoSession.getRateBodyDao().queryBuilder().list().toString());
                return mDaoSession.getRateBodyDao().queryBuilder().list();
            }
        });
    }

    @Override
    public Single<Boolean> isRateEmpty() {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getRateBodyDao().count() > 0);
            }
        });
    }

    @Override
    public Single<Boolean> saveRate(final RateBody option) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRateBodyDao().insertInTx(option);
                return true;
            }
        });
    }

    @Override
    public Single<Boolean> saveRateList(final List<RateBody> questionList) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getRateBodyDao().insertInTx(questionList);
                return true;
            }
        });
    }
}