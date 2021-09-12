package az.bank.currencyapp.presentation.main;

import java.util.List;

import az.bank.currencyapp.data.models.CurrencyResponse;
import az.bank.currencyapp.data.models.RateBody;

public interface MainView {
    void responseSuccess(CurrencyResponse currencyResponse);
    void allRateResponseSuccess(List<RateBody> listRates);
    void responseError(String message);
}
