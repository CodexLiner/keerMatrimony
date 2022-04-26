package keer.matrimony.ui.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.models.data;
import keer.matrimony.utils.MyException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_splash);
            userDatabaseHelper db = new userDatabaseHelper(this);
            userDatabaseModel model = db.getUser(0);
            FirebaseAnalytics.getInstance(this).setSessionTimeoutDuration(10000000);
            if (model!=null){
                getProfiles();
            }

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    if (model!=null){
                        startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                        overridePendingTransition(0,0);
                    }else {
                        startActivity(new Intent(getApplicationContext() , LoginActivity.class));
                        overridePendingTransition(0,0);
                    }
                    finishAffinity();
                }
            },2000);
        }catch (Exception e){
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }
    public void getProfiles(){
        Log.d("TAG", "getProfiles: splash");
        Gson gson = new Gson();
        userDatabaseHelper db = new userDatabaseHelper(getApplicationContext());
        userDatabaseModel model = db.getUser(0);
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"get-profiles/"+model.getId()).addHeader("authorization" , "[]").get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TAG", "onResponse: failed ");
                    }
                });
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                try {
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    Type type = new TypeToken<List<data>>(){}.getType();
                    CONSTANTS.DATA = gson.fromJson(jsonResponse.getJSONObject("detail").optString("data"), type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}