package az.bank.currencyapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateBody {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("buy_cash")
    @Expose
    private Double buy_cash;

    @SerializedName("buy_none_cash")
    @Expose
    private Double buy_none_cash;

    @SerializedName("sell_cash")
    @Expose
    private Double sell_cash;

    @SerializedName("sell_none_cash")
    @Expose
    private Double sell_none_cash;

    @SerializedName("name")
    @Expose
    private String name;

    public RateBody() {

    }

    public RateBody(String code, Double buy_cash, Double buy_none_cash, Double sell_cash, Double sell_none_cash, String name) {
        this.code = code;
        this.buy_cash = buy_cash;
        this.buy_none_cash = buy_none_cash;
        this.sell_cash = sell_cash;
        this.sell_none_cash = sell_none_cash;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Double getBuy_cash() {
        return buy_cash;
    }

    public Double getBuy_none_cash() {
        return buy_none_cash;
    }

    public Double getSell_cash() {
        return sell_cash;
    }

    public Double getSell_none_cash() {
        return sell_none_cash;
    }

    public String getName() {
        return name;
    }

    public void setBuy_cash(Double buy_cash) {
        this.buy_cash = buy_cash;
    }

    public void setBuy_none_cash(Double buy_none_cash) {
        this.buy_none_cash = buy_none_cash;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSell_cash(Double sell_cash) {
        this.sell_cash = sell_cash;
    }

    public void setSell_none_cash(Double sell_none_cash) {
        this.sell_none_cash = sell_none_cash;
    }
}
