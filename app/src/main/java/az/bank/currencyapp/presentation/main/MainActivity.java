package az.bank.currencyapp.presentation.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import az.bank.currencyapp.R;
import az.bank.currencyapp.deps.DaggerAppComponent;
import az.bank.currencyapp.di.AppModule;
import az.bank.currencyapp.models.CurrencyResponse;
import az.bank.currencyapp.di.NetworkModule;
import az.bank.currencyapp.models.RateBody;
import az.bank.currencyapp.network.NetworkService;
import az.bank.currencyapp.util.DecimalDigitsInputFilter;


public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    public NetworkService networkService;

    private MainPresenter presenter;
    EditText fromInput;
    EditText toInput;
    TextView fromText;
    TextView toText;
    LinearLayout selectFromCurrency;
    LinearLayout selectToCurrency;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        toInput = findViewById(R.id.toInput);
        fromInput.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
        fromText = findViewById(R.id.fromText);
        selectFromCurrency = findViewById(R.id.selectFromCurrency);
        selectToCurrency = findViewById(R.id.selectToCurrency);
        toText = findViewById(R.id.toText);
    }



    @Override
    public void responseSuccess(CurrencyResponse currencyResponse) {
        Log.e("TAG", "responseSuccess: "+currencyResponse.getResult());
    }

    @Override
    public void allRateResponseSuccess(List<RateBody> listRates) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.select_currency));

        ArrayList<String> currnecyStrings = new ArrayList<String>();
        for (int i = 0; i < listRates.size(); i++) {
            if(listRates.get(i).getName().equals("Uni Bank")) {
                currnecyStrings.add(listRates.get(i).getCode());
            }
        }
        toInput.setText(listRates.get(0).getBuy_none_cash());


        selectFromCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CLIIKKK", "onClick: ASASD");
                builder.setItems(currnecyStrings.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fromText.setText(currnecyStrings.get(which));

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        selectToCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setItems(currnecyStrings.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toText.setText(currnecyStrings.get(which));

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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