package az.bank.currencyapp.data.db;


import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;


import az.bank.currencyapp.data.models.DaoMaster;
import az.bank.currencyapp.data.models.RateBodyDao;
import az.bank.currencyapp.di.ApplicationContext;
import az.bank.currencyapp.di.DatabaseInfo;

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
//        switch (oldVersion) {
//            case 1:
//
//            case 2:
//                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
//                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
//        }
//        db.execSQL("INSERT INTO " + RateBodyDao.TABLENAME + " (" +
//                RateBodyDao.Properties.Id.columnName + ", " +
//                RateBodyDao.Properties.Code.columnName + ", " +
//                RateBodyDao.Properties.Buy_cash.columnName + ", " +
//                RateBodyDao.Properties.Buy_none_cash.columnName + ", " +
//                RateBodyDao.Properties.Sell_cash.columnName + ", " +
//                RateBodyDao.Properties.Sell_none_cash.columnName + ", " +
//                RateBodyDao.Properties.Name.columnName +
//                ") VALUES(1, 0, 'Example Note')");
    }
}