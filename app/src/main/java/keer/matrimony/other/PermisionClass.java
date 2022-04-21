package keer.matrimony.other;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermisionClass {
    public static boolean hasPermision(Context context , String[] permision){
        for (String s : permision){
            if (ActivityCompat.checkSelfPermission(context , s) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    };
    public static final String[] permisions = {
//            Manifest.permission.READ_CONTACTS,
//            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
//            android.Manifest.permission.READ_SMS,
//            android.Manifest.permission.CAMERA,
//            Manifest.permission.READ_CONTACTS,
//            android.Manifest.permission.RECORD_AUDIO,
//            android.Manifest.permission.ACCESS_COARSE_LOCATION,
//            android.Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.READ_CONTACTS,
//            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.READ_CALL_LOG,
//            Manifest.permission.READ_SYNC_SETTINGS,
//            Manifest.permission.WRITE_SYNC_SETTINGS,
            Manifest.permission.CALL_PHONE,
//            Manifest.permission.READ_SMS,
//            Manifest.permission.WRITE_CONTACTS

    };
}
