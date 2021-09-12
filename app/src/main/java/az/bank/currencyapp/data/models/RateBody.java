package az.bank.currencyapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "rates")
public class RateBody {
    @Expose
    @SerializedName("id")
    @Id
    private Long id;

    @SerializedName("code")
    @Property(nameInDb = "code")
    @Expose
    private String code;

    @SerializedName("buy_cash")
    @Property(nameInDb = "buy_cash")
    @Expose
    private double buy_cash;

    @SerializedName("buy_none_cash")
    @Property(nameInDb = "buy_none_cash")
    @Expose
    private double buy_none_cash;

    @SerializedName("sell_cash")
    @Expose
    private double sell_cash;

    @SerializedName("sell_none_cash")
    @Property(nameInDb = "sell_none_cash")
    @Expose
    private double sell_none_cash;

    @SerializedName("name")
    @Property(nameInDb = "name")
    @Expose
    private String name;

    public RateBody() {

    }

    public RateBody(String code, double buy_cash, double buy_none_cash, double sell_cash, double sell_none_cash, String name) {
        this.code = code;
        this.buy_cash = buy_cash;
        this.buy_none_cash = buy_none_cash;
        this.sell_cash = sell_cash;
        this.sell_none_cash = sell_none_cash;
        this.name = name;
    }

    @Generated(hash = 611983025)
    public RateBody(Long id, String code, double buy_cash, double buy_none_cash, double sell_cash, double sell_none_cash,
            String name) {
        this.id = id;
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

    public double getBuy_cash() {
        return buy_cash;
    }

    public double getBuy_none_cash() {
        return buy_none_cash;
    }

    public double getSell_cash() {
        return sell_cash;
    }

    public double getSell_none_cash() {
        return sell_none_cash;
    }

    public String getName() {
        return name;
    }

    public void setBuy_cash(double buy_cash) {
        this.buy_cash = buy_cash;
    }

    public void setBuy_none_cash(double buy_none_cash) {
        this.buy_none_cash = buy_none_cash;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSell_cash(double sell_cash) {
        this.sell_cash = sell_cash;
    }

    public void setSell_none_cash(double sell_none_cash) {
        this.sell_none_cash = sell_none_cash;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
