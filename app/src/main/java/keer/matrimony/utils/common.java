package keer.matrimony.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.models.data;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.HomeActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class common {
    public static void setParams(TextView view, String str) {
        if (str!=null){
            view.setText(str);
        }
    }
    public static void startCall(String mNum , Context context){
       try {
           Intent intent = new Intent(Intent.ACTION_CALL);
           intent.setData(Uri.parse("tel:"+mNum));
           context.startActivity(intent);
       }catch (Exception ignore){}
    }
    public static void Verify(Context context , String id) {
        Gson gson = new Gson();
        Map<String , String > map = new HashMap<>();

        String jsonString = gson.toJson(map);
        final RequestBody requestBody = RequestBody.create(jsonString , MediaType.get(CONSTANTS.mediaType));
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"user-detail/"+id).addHeader("authorization" , "[]").get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) { }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!jsonResponse.optBoolean("error")) {
                                data userData = gson.fromJson(jsonResponse.optString("data").toString() , data.class);
                                userDatabaseHelper db = new userDatabaseHelper(context);
                                db.insertUser(userData);
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
