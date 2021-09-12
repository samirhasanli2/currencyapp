package az.bank.currencyapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllRatesResponse {
    @SerializedName("body")
    @Expose
    private List<RateBody> body;

    public List<RateBody> getBody() {
        return body;
    }

    public void setBody(List<RateBody> body) {
        this.body = body;
    }
}
