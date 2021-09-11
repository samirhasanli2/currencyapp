package az.bank.currencyapp.util;

import android.content.Context;

import java.io.IOException;

import az.bank.currencyapp.R;

public class ErrorHandler {
    private Context context;

    public ErrorHandler(Context context) {

        this.context = context;
    }

    public String getStringError(String error) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(error, "string", packageName);
        if(resId > 0) {
            return context.getString(resId);
        }else {
            return context.getString(R.string.internal_error);
        }
    }

    public String getThrowError(Throwable throwable) {
        if(throwable instanceof IOException) {
//                        IOException exception = (IOException) throwable;
            return context.getString(R.string.internet_connection_error);
        }else {
            return context.getString(R.string.server_connection_error);
        }
    }


}
