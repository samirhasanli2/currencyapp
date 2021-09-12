package az.bank.currencyapp.presentation.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import az.bank.currencyapp.R;
import az.bank.currencyapp.data.db.AppDbHelper;
import az.bank.currencyapp.data.db.DbHelper;
import az.bank.currencyapp.deps.DaggerAppComponent;
import az.bank.currencyapp.di.AppModule;
import az.bank.currencyapp.data.models.CurrencyResponse;
import az.bank.currencyapp.di.NetworkModule;
import az.bank.currencyapp.data.models.RateBody;
import az.bank.currencyapp.data.network.NetworkService;
import az.bank.currencyapp.util.DecimalDigitsInputFilter;
import az.bank.currencyapp.util.Util;


public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    public NetworkService networkService;
    @Inject
    public AppDbHelper appDbHelper;

    private MainPresenter presenter;
    EditText fromInput;
    EditText toInput;
    TextView fromText;
    TextView toText;
    LinearLayout selectFromCurrency;
    LinearLayout selectToCurrency;
    LinearLayout loading;

    List<RateBody> rates;
    int fromPosition = 0;
    int toPosition = 0;

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
        fromText = findViewById(R.id.fromText);
        toText = findViewById(R.id.toText);
        selectFromCurrency = findViewById(R.id.selectFromCurrency);
        selectToCurrency = findViewById(R.id.selectToCurrency);
        loading = findViewById(R.id.loading);
        fromInput.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2)});
    }



    @Override
    public void responseSuccess(CurrencyResponse currencyResponse) {

    }

    @Override
    public void allRateResponseSuccess(List<RateBody> listRates) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.select_currency));
        rates = listRates;

        ArrayList<String> currnecyStrings = new ArrayList<String>();
        for (int i = 0; i < listRates.size(); i++) {
            if(listRates.get(i).getName().equals("Uni Bank")) {
                currnecyStrings.add(listRates.get(i).getCode());
            }
        }

        if(listRates.size() > 0) {
            toPosition = listRates.size() -1;
            convertView();
        }
        loading.setVisibility(View.GONE);
        fromInput.requestFocus();
        fromInput.setSelection(fromInput.getText().length());
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(fromInput, InputMethodManager.SHOW_IMPLICIT);

        selectFromCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setItems(currnecyStrings.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fromText.setText(currnecyStrings.get(which));
                        fromPosition = which;
                        convertView();
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
                        toPosition = which;
                        convertView();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        fromInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                convertView();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        Log.e("Response", "rateResponseSuccess: " + (new Gson()).toJson(listRates));
    }

    void convertView() {
        if(rates.size() > 0 && Util.isNumeric(fromInput.getText().toString())) {
            fromText.setText(rates.get(fromPosition).getCode());
            toText.setText(rates.get(toPosition).getCode());
            toInput.setText(String.valueOf(Double.parseDouble(fromInput.getText().toString()) * (rates.get(fromPosition).getBuy_cash()/rates.get(toPosition).getBuy_cash())));
        }else {
            toInput.setText("");
        }
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
        loading.setVisibility(View.GONE);
    }





}