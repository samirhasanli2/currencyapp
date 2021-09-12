package az.bank.currencyapp.data.db.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "rates")
public class RateLocalModel {
    @Expose
    @SerializedName("id")
    @Id
    private Long id;

    @Expose
    @SerializedName("code")
    @Property(nameInDb = "code")
    private String code;

    @Expose
    @SerializedName("name")
    @Property(nameInDb = "name")
    private String name;

    @Expose
    @SerializedName("buy_cash")
    @Property(nameInDb = "buy_cash")
    private double buy_cash;

    @Expose
    @SerializedName("buy_none_cash")
    @Property(nameInDb = "buy_none_cash")
    private double buy_none_cash;

    @Expose
    @SerializedName("sell_cash")
    @Property(nameInDb = "sell_cash")
    private double sell_cash;

    @Expose
    @SerializedName("sell_none_cash")
    @Property(nameInDb = "sell_none_cash")
    private double sell_none_cash;

    public RateLocalModel() {

    }

    public RateLocalModel(String code, String name, double buy_cash, double buy_none_cash, double sell_cash, double sell_none_cash) {
        this.code = code;
        this.name = name;
        this.buy_cash = buy_cash;
        this.buy_none_cash = buy_none_cash;
        this.sell_cash = sell_cash;
        this.sell_none_cash = sell_none_cash;
    }

    @Generated(hash = 782632786)
    public RateLocalModel(Long id, String code, String name, double buy_cash, double buy_none_cash, double sell_cash,
            double sell_none_cash) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.buy_cash = buy_cash;
        this.buy_none_cash = buy_none_cash;
        this.sell_cash = sell_cash;
        this.sell_none_cash = sell_none_cash;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
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

    public double getBuy_cash() {
        return buy_cash;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuy_cash(double buy_cash) {
        this.buy_cash = buy_cash;
    }

    public void setBuy_none_cash(double buy_none_cash) {
        this.buy_none_cash = buy_none_cash;
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
