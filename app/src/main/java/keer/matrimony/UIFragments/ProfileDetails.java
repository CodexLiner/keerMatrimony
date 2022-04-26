package keer.matrimony.UIFragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.models.ContactDetails;
import keer.matrimony.models.education;
import keer.matrimony.models.familyDetails;
import keer.matrimony.models.personal_details;
import keer.matrimony.models.religious_details;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.databinding.FragmentProfileDetailsBinding;
import keer.matrimony.models.data;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.SearchResult;
import keer.matrimony.utils.common;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProfileDetails extends Fragment {
    @NonNull FragmentProfileDetailsBinding binding;
    data data;
    Activity activity;
    familyDetails fd ;
    education ed;
    ContactDetails cd;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileDetails(data data , Activity activity) {
       this.data = data;
       this.activity = activity;
    }

    public ProfileDetails() {

    }

    public static ProfileDetails newInstance(String param1, String param2) {
        ProfileDetails fragment = new ProfileDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = CONSTANTS.DATAs;
        binding = FragmentProfileDetailsBinding.inflate(inflater);
        // Inflate the layout for this fragment
       if (activity==null){
           ((HomeActivity) getActivity()).hide(View.INVISIBLE);
           ((HomeActivity) getActivity()).setActionBarTitle(data.getFirst_name()+" "+data.getLast_name());
       }else {
           ((SearchResult) getActivity()).setActionBarTitle(data.getFirst_name()+" "+data.getLast_name());
       }
        if (data.getDob()!=null){
            getProfiles(data.getId());
            binding.dob.setText(data.getDob());
            binding.dob2.setText(data.getDob());
        }
        if (data.getSubcaste()!=null){
            binding.subcast.setText(data.getSubcaste());
        }
        if (data.getHeight()!=null){
            binding.height.setText(data.getHeight());
        }
        if (data.getWeight()!=null){
            binding.weight.setText(data.getHeight());
        }
        if (data.getProfile()!=null){
            Picasso.with(getContext()).load(CONSTANTS.BASEURLPROFILE + data.getProfile()).into(binding.profile);
        }
        binding.profile.setOnClickListener((View p)->{
            String name = CONSTANTS.BASEURLPROFILE + data.getProfile();
            common.showProfile(name, p.getContext());
        });

        return binding.getRoot();
    }
    public void getProfiles(String id){
        ProgressDialog progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Getting Profile Data....");
        progressDoalog.show();
        Gson gson = new Gson();
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"user-detail/"+id).addHeader("authorization" , "[]").get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("TAG", "getProfiles: f ");
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
                    personal_details pd = gson.fromJson(jsonResponse.optString("personal_details"), personal_details.class);
                    religious_details rd = gson.fromJson(jsonResponse.optString("religious_details"), religious_details.class);
                    fd  = gson.fromJson(jsonResponse.optString("family_details"), familyDetails.class);
                    ed  = gson.fromJson(jsonResponse.optString("education"), education.class);
                    cd  = gson.fromJson(jsonResponse.optString("contact_details"), ContactDetails.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (cd!=null){
                                common.setParams(binding.fatherContact, cd.getFath_contact());
                                common.setParams(binding.whatsapp, cd.getWhatsapp_no());
                                common.setParams(binding.PermanentAddress, cd.getPermanent_addr());
                                common.setParams(binding.PresentAddress, cd.getPresent_addr());
                            }
                            if (ed!=null){
                                common.setParams(binding.education, ed.getEdu());
                                common.setParams(binding.educationDetails, ed.getEdu_detail());
                                common.setParams(binding.occupation, ed.getOccupation());
                                common.setParams(binding.occupationDetails, ed.getOcu_detail());
                                common.setParams(binding.annualIncome, ed.getAnu_income());
                            }
                            if (fd!=null){
                                common.setParams(binding.cars, fd.getCar());
                                common.setParams(binding.house, fd.getHouse());
                                common.setParams(binding.mSister, fd.getNo_married_sis());
                                common.setParams(binding.umSister, fd.getNo_married_sis());
                                common.setParams(binding.fatherName, fd.getFather_name());
                                common.setParams(binding.fatherOcu, fd.getFather_occupation());
                                common.setParams(binding.motherName, fd.getMother_name());
                                common.setParams(binding.motherOcu, fd.getMother_occupation());
                                common.setParams(binding.unBrother, fd.getNo_unmarried_bro());
                                common.setParams(binding.marriedBorther, fd.getNo_married_bro());
                                common.setParams(binding.marriedBorther, fd.getNo_married_bro());
                                common.setParams(binding.marriedBorther, fd.getNo_married_bro());
                                common.setParams(binding.mamaji, fd.getMaternal_name());
                                common.setParams(binding.unclesGotra, fd.getMaternal_gotra());
                            }
                            if (rd!=null){
                                Gson gson = new Gson();
                                String json = gson.toJson(rd);
                                if (rd.getBirth_place()!=null){
                                    binding.bithPlace.setText(rd.getBirth_place());
                                }
                                if (rd.getBirth_time()!=null){
                                    binding.birthTime.setText(rd.getBirth_time());
                                }
                                if (rd.getManglic()!=null){
                                    binding.manglik.setText(rd.getManglic());
                                }
                                if (rd.getGotra()!=null){
                                    binding.gotra.setText(rd.getGotra());
                                }
                                if (rd.getMother_tongue()!=null){
                                    binding.motherTongue.setText(rd.getMother_tongue());
                                }
                            }
                            if (pd!=null){
                                Gson gson = new Gson();
                                String json2 = gson.toJson(pd);
                                if (pd.getBlood_group()!=null){
                                    binding.bloodGroup.setText(pd.getBlood_group());
                                }
                                if (pd.getComplexion()!=null){
                                    binding.complextion.setText(pd.getComplexion());
                                }
                                if (pd.getDisablity()!=null){
                                    binding.disAbility.setText(pd.getDisablity());
                                }
                                if (pd.getHeight()!=null){
                                    binding.height.setText(pd.getHeight());
                                }
                                if (pd.getWeight()!=null){
                                    binding.weight.setText(pd.getWeight());
                                }
                                if (pd.getMaretial_status()!=null){
                                    binding.maritalStatus.setText(pd.getMaretial_status());
                                }
                            }
                            progressDoalog.dismiss();
                        }
                    });

                } catch (JSONException e) {
                    Log.d("TAG", "onResponseXx: "+e);
                    e.printStackTrace();
                }

            }
        });
    }

}