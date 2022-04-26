package keer.matrimony.ui.Activitys;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.databinding.ActivityHomeBinding;
import keer.matrimony.models.data;
import keer.matrimony.other.PermisionClass;
import keer.matrimony.utils.common;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getProfiles();
            binding = ActivityHomeBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            ActionBar actionBar = getSupportActionBar();
            PermissionCheck();
            // showing the back button in action bar
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
            navView = findViewById(R.id.nav_view);
            navView.setSelectedItemId(R.id.navigation_dashboard);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_search , R.id.navigation_dashboard, R.id.navigation_notifications)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home);

//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
        }catch (Exception e){
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }
    public void setActionBarTitle(String title){
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(title);
        }
    }
    public  void hide(int id){
        if (navView!=null){
            navView.setVisibility(id);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return false;
    }
    private void PermissionCheck() {
        if (!PermisionClass.hasPermision(HomeActivity.this , PermisionClass.permisions)){
            ActivityCompat.requestPermissions(HomeActivity.this, PermisionClass.permisions, 0);
        }
    }
    public void getProfiles(){
        Gson gson = new Gson();
        userDatabaseHelper db = new userDatabaseHelper(this);
        userDatabaseModel model = db.getUser(0);
//        final RequestBody requestBody = RequestBody.create(jsonString , MediaType.get(CONSTANTS.mediaType));
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
                    List<data> dataList = gson.fromJson(jsonResponse.getJSONObject("detail").optString("data"), type);
                    CONSTANTS.DATA = dataList;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
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
                    common.Verify(getApplicationContext() , String.valueOf(ID));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}