package keer.matrimony.ui.search;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.databinding.SearchFragmentBinding;
import keer.matrimony.models.data;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.SearchResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchFragment extends Fragment {
    SearchFragmentBinding binding;
    String mGender = ""  , toDate = "" , fromDate = "" , mSubcast= "" ,  mCountry = "" , mState= "" , mStatus= "" , Height = "";

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SearchFragmentBinding.inflate(inflater);
        ((HomeActivity) getActivity()).setActionBarTitle("Search Profile");
        String[] genderAray= {
                "Select Gender" , "Male" , "Female"
        };
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, genderAray);
        binding.gender.setAdapter(genderAdapter);
        binding.gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mGender = genderAray[position];
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


        //        subcast status
        String[] subcast= {
                "Select Subcast" , "नमचूड़िया " , "वाण्या" , "दायमा " , "राठौर  ","आकड़ा  ","वामण ","गाडरी",
                "उवाड  " , "नायर " , "लोहरया  " , "हिकडया  ","जाट   ","सोलंकी  ","ब्यल्छा ",
                "माली  " , "रात्या " , "भलंग  " , "टोक "
        };
        ArrayAdapter<String> subcastAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, subcast);
        binding.subcast.setAdapter(subcastAdapter);
        binding.subcast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mSubcast = subcast[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String[] country= {
                "Select Country" , "India"
        };
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, country);
        binding.country.setAdapter(countryAdapter);
        binding.country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mCountry = country[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] state = {"Select State" ,  "Andhra Pradesh",
                "Arunachal Pradesh",
                "Assam",
                "Bihar",
                "Chhattisgarh",
                "Goa",
                "Gujarat",
                "Haryana",
                "Himachal Pradesh",
                "Jammu and Kashmir",
                "Jharkhand",
                "Karnataka",
                "Kerala",
                "Madhya Pradesh",
                "Maharashtra",
                "Manipur",
                "Meghalaya",
                "Mizoram",
                "Nagaland",
                "Odisha",
                "Punjab",
                "Rajasthan",
                "Sikkim",
                "Tamil Nadu",
                "Telangana",
                "Tripura",
                "Uttarakhand",
                "Uttar Pradesh",
                "West Bengal",
                "Andaman and Nicobar Islands",
                "Chandigarh",
                "Dadra and Nagar Haveli",
                "Daman and Diu",
                "Delhi",
                "Lakshadweep",
                "Puducherry" };
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, state);
        binding.state.setAdapter(stateAdapter);
        binding.state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mState = state[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //        martial status
        String[] maritalStatus= {
                "Marital Status" , "Married" , "UnMarried" , "Divorced"
        };
        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, maritalStatus);
        binding.maritalStatus.setAdapter(maritalStatusAdapter);
        binding.maritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mStatus = maritalStatus[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] heightArray = { "Height", "5.0 feet", "5.5 feet", "6.0 feet", "6.5 feet"};
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, heightArray);
        binding.height.setAdapter(heightAdapter);
        binding.height.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Height = heightArray[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearch(getContext() , mGender ,fromDate , toDate , mSubcast , mStatus , Height , "" , "" , mCountry , mState ,
                        "" , "" , "" , "", "");
            }
        });
        return binding.getRoot();
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
        userDatabaseModel model;
        userDatabaseHelper db = new userDatabaseHelper(getContext());
        model = db.getUser(0);
        String id = "";
        if (model!=null){
            id = String.valueOf(model.getId());
        }
        String url = CONSTANTS.BASEURL + "get-profiles/"+id+"?page=1"+gender+min_age+max_age+subcaste+maretial_status+height_from+height_to+manglic+country+state+city+education+occupation+profession+anu_income;
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
                        List<data> dataList = gson.fromJson(jsonResponse.getJSONObject("detail").optString("data"), type);
                        CONSTANTS.SEARCHRESULT = dataList;
                        startActivity(new Intent(getContext() , SearchResult.class));
                        dialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}