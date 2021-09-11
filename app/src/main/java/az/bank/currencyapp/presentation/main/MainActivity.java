package az.bank.currencyapp.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import az.bank.currencyapp.R;
import az.bank.currencyapp.deps.DaggerAppComponent;
import az.bank.currencyapp.di.AppModule;
import az.bank.currencyapp.models.CurrencyResponse;
import az.bank.currencyapp.di.NetworkModule;
import az.bank.currencyapp.models.RateBody;
import az.bank.currencyapp.network.NetworkService;
import az.bank.currencyapp.util.DecimalDigitsInputFilter;
import az.bank.currencyapp.util.MoneyTextWatcher;


public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    public NetworkService networkService;

    private MainPresenter presenter;
    EditText fromInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .appModule(new AppModule(getApplication()))
                .build().inject(this);

        presenter = new MainPresenter(this, getApplication());
        presenter.fetchData();
        initView();
    }

    private void initView() {
        fromInput = findViewById(R.id.fromInput);
        fromInput.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
//        fromInput.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100000000")});
    }



    @Override
    public void responseSuccess(CurrencyResponse currencyResponse) {
        Log.e("TAG", "responseSuccess: "+currencyResponse.getResult());
    }

    @Override
    public void allRateResponseSuccess(List<RateBody> listRates) {
        Log.e("Response", "rateResponseSuccess: " + (new Gson()).toJson(listRates));
    }

    @Override
    public void responseError(String message) {
        Log.e("error", "responseError: " + message);
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_layout), message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this,R.color.red));
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setAction(getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();

    }
}