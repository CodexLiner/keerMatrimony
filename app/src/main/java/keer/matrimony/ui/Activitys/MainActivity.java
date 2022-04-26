package keer.matrimony.ui.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import keer.matrimony.UIFragments.onBoarding.FamilyDetails;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.UIFragments.onBoarding.PersonalDetails;
import keer.matrimony.utils.MyException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setTheme(R.style.Theme_CustomeTheme);
            setContentView(R.layout.activity_main);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.ContainerMain, PersonalDetails.class, null)
                    .commit();
        }catch (Exception e){
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }
    public void setActionBarTitle(String title){
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(title);
        }
    }

    public void setPersonalDetails(Map<String, String> map , String endPoint , int ID) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        final RequestBody requestBody = RequestBody.create(jsonString , MediaType.get(CONSTANTS.mediaType));
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +endPoint+ID).addHeader("authorization" , "[]").post(requestBody).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}