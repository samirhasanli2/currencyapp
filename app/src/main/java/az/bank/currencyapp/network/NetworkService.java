package az.bank.currencyapp.network;


import java.util.List;

import az.bank.currencyapp.models.AllRatesResponse;
import az.bank.currencyapp.models.CurrencyResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface NetworkService {
    @GET("api/get_rate_current_all/{from}/{to}")
    Single<List<CurrencyResponse>> getCurrency(
            @Path(value = "from", encoded = true) String from,
            @Path(value = "to", encoded = true) String to
    );

    @GET("api/all-bank-rates")
    Single<AllRatesResponse> getAllRates();
}
