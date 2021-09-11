package az.bank.currencyapp.presentation.main;

import java.util.List;

import az.bank.currencyapp.models.CurrencyResponse;
import az.bank.currencyapp.models.RateBody;

public interface MainView {
    void responseSuccess(CurrencyResponse currencyResponse);
    void allRateResponseSuccess(List<RateBody> listRates);
    void responseError(String message);
}
