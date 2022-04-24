package keer.matrimony.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import keer.matrimony.Adapters.ProfileListAdapters;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentHomeBinding;
import keer.matrimony.models.data;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.SearchResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    String gender , fromDate , toDate ;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        keer.matrimony.ui.home.HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(keer.matrimony.ui.home.HomeViewModel.class);
        ((HomeActivity) requireActivity()).setActionBarTitle("Keer Matrimony");

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        ArrayList<String> list = new ArrayList<>();
        View root = binding.getRoot();
        binding.profileRec.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
      //   ProfileListAdapters adapters = new ProfileListAdapters(list);
        if (CONSTANTS.DATA!=null){
            ProfileListAdapters adapters = new ProfileListAdapters(CONSTANTS.DATA);
            binding.profileRec.setAdapter(adapters);
        }else {
            getProfiles();
        }
//        spinner
        String[] state= {
                "Select Gender" , "Male" , "Female"
        };
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, state);
        binding.gender.setAdapter(stateAdapter);

        binding.gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    gender = state[position];
                }else {
                    gender = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       ArrayList<String> arr = new ArrayList<>(99);
       arr.add("Select From Age");
       ArrayList<String> arr2 = new ArrayList<>(99);
        arr2.add("Select To Age");
        for (int i = 0; i < 99; i++) {
            arr.add(String.valueOf(i+1));
            arr2.add(String.valueOf(i+1));
        }
        ArrayAdapter<String> toAdapater = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, arr2);
        binding.toDate.setAdapter(toAdapater);
        binding.toDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    toDate = arr.get(position);
                }else {
                    toDate = null;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> startAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, arr);
        binding.fromDate.setAdapter(startAdapter);
        binding.fromDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    fromDate = arr2.get(position);
                }else {
                    fromDate = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(gender)){
                    Toast.makeText(getContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fromDate)){
                    Toast.makeText(getContext(), "Please Select From Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(toDate)){
                    Toast.makeText(getContext(), "Please To Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                startSearch(getContext() , gender , fromDate , toDate ,
                        "" , "" , "" ,
                        "" , "" , "" ,
                        "" ,"" , "" ,
                        "" ,"" ,"");

            }
        });
        // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void startSearch(Context context ,
                 String mGender , String minAge , String maxAge , String  subCast , String mStatus , String heightFrom , String heightTo ,
                 String mManglic , String mCountry , String mState , String mCity , String mEdu , String mOcu , String mPro , String mInc) {
        Dialog dialog = new Dialog(context);
        dialog.setTitle("Loading");
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCancelable(false);
        dialog.show();
        String gender = "&gender="+mGender;
        String min_age = "&min_age="+minAge;
        String max_age = "&max_age="+maxAge;
        String subcaste = "&subcaste="+subCast;
        String maretial_status = "&maretial_status="+mStatus;
        String height_from = "&height_from="+heightFrom;
        String height_to = "&height_to="+heightTo;
        String manglic = "&manglic="+mManglic;
        String country = "&country="+mCountry;
        String state = "&state="+mState;
        String city = "&city="+mCity;
        String education = "&education="+mEdu;
        String occupation = "&occupation="+mOcu;
        String profession = "&profession="+mPro;
        String anu_income = "&anu_income="+mInc;
        String url = CONSTANTS.BASEURL + "get-profiles/2?page=1"+gender+min_age+max_age+subcaste+maretial_status+height_from+height_to+manglic+country+state+city+education+occupation+profession+anu_income;
        Gson gson = new Gson();
        Request request = new Request.Builder().url(url).get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(response.body().string());
                    if (jsonResponse.getBoolean("error")){
                        JSONObject finalJsonResponse = jsonResponse;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                Toast.makeText(context, ""+ finalJsonResponse.optString("message"), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        Type type = new TypeToken<List<data>>(){}.getType();
                        CONSTANTS.SEARCHRESULT = gson.fromJson(jsonResponse.getJSONObject("detail").optString("data"), type);
                        startActivity(new Intent(getContext() , SearchResult.class));
                        dialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void getProfiles(){
        userDatabaseModel model;
        userDatabaseHelper db = new userDatabaseHelper(getContext());
        model = db.getUser(0);
        Gson gson = new Gson();
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProfileListAdapters  adapter = new ProfileListAdapters(dataList);
                            binding.profileRec.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}