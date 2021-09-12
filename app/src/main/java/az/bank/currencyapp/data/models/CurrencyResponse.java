package az.bank.currencyapp.data.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyResponse {
    @SerializedName("from")
    @Expose
    private String from;

    @SerializedName("to")
    @Expose
    private String to;

    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("date")
    @Expose
    private String date;

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getResult() {
        return result;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
