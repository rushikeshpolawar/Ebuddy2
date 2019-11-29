package gsheets1.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;


public class InternetConnection {


    public static boolean checkConnection(@NonNull Context context) {
        return  ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}