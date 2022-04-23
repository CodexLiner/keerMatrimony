package keer.matrimony.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentNotificationsBinding;
import keer.matrimony.models.ContactDetails;
import keer.matrimony.models.data;
import keer.matrimony.models.education;
import keer.matrimony.models.familyDetails;
import keer.matrimony.models.personal_details;
import keer.matrimony.models.religious_details;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.other.ImageUpload;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.utils.common;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationsFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    religious_details rd;
    personal_details pd;
    familyDetails fd ;
    education ed;
    ContactDetails cd;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.profile.setOnClickListener((View click)->{
            showFileChooser();
        });

        ((HomeActivity) requireActivity()).setActionBarTitle("Your Profile");
        ((HomeActivity) requireActivity()).hide(View.VISIBLE);

        sharedPreferences = requireContext().getSharedPreferences("profile" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.contains("rd")){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("rd", "");
            religious_details rd = gson.fromJson(json, religious_details.class);
            this.rd = rd;
            if (rd!=null){
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
        }else { getProfiles(); }
        if (sharedPreferences.contains("pd")){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("pd", "");
            personal_details pd = gson.fromJson(json, personal_details.class);
            this.pd = pd;
            if (pd!=null){
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
        }else {
            getProfiles();
        }
        if (sharedPreferences.contains("cd")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("cd", "");
            ContactDetails cd = gson.fromJson(json, ContactDetails.class);
            this.cd = cd;
            common.setParams(binding.fatherContact, cd.getFath_contact());
            common.setParams(binding.whatsapp, cd.getWhatsapp_no());
            common.setParams(binding.PermanentAddress, cd.getPermanent_addr());
            common.setParams(binding.PresentAddress, cd.getPresent_addr());

        }
        if (sharedPreferences.contains("ed")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("ed", "");
            education ed = gson.fromJson(json, education.class);
            this.ed = ed;
            common.setParams(binding.education, ed.getEdu());
            common.setParams(binding.educationDetails, ed.getEdu_detail());
            common.setParams(binding.occupation, ed.getOccupation());
            common.setParams(binding.occupationDetails, ed.getOcu_detail());
            common.setParams(binding.annualIncome, ed.getAnu_income());
        }
        if (sharedPreferences.contains("fd")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("fd", "");
            familyDetails fd = gson.fromJson(json, familyDetails.class);
            this.fd = fd;
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
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProfiles();
                binding.swipe.setRefreshing(true);
            }
        });
        //getProfiles();
        userDatabaseHelper db = new userDatabaseHelper(getContext());
        userDatabaseModel model = db .getUser(0);
        Log.d("TAG", "onCreateView: "+model.toString());
        if (model.getPartner_preference()!=null){
            binding.partnerPref.setText(model.getPartner_preference());
        }
        setProfile(model.getProfile());
        binding.dob.setText(model.getDob());
        binding.dob2.setText(model.getDob());
        String name = model.getFirst_name()+" "+model.getLast_name();
        binding.fullName.setText(name);
        binding.subcast.setText(model.getSubcaste());
        binding.personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CONSTANTS.PERSONALDETAIL = pd;
                Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_EditPerosnal);
            }
        });
        binding.religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CONSTANTS.RELIGIOUSDETAIL =rd ;
                Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_EditReligion);
            }
        });
        binding.editPartner.setOnClickListener((View part)->{
            Navigation.findNavController(part).navigate(R.id.action_navigation_notifications_to_EditPartner);

        });
        binding.contactDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CONSTANTS.CONTACTDETAILS =cd ;
                Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_EditContact);
            }
        });
        binding.EditEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CONSTANTS.EDUCATIONDETAILSEDIT =ed ;
                Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_EditEducation);
            }
        });
        binding.family.setOnClickListener((View family)->{
            CONSTANTS.family = fd;
            Navigation.findNavController(family).navigate(R.id.action_navigation_notifications_to_EditFamily);
        });
//        binding.motherTongue.setText(model.ge);
        return root;
    }

    private void setProfile(String profile) {
       try {
           File file = new File(requireContext().getExternalCacheDir(), "keerProfile.jpg");
           binding.profile.setImageURI(Uri.parse(file.getPath()));
       }catch (Exception ignored){}

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void getProfiles(){
        Gson gson = new Gson();
        userDatabaseHelper db = new userDatabaseHelper(getContext());
        userDatabaseModel model = db.getUser(0);
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"user-detail/"+model.getId()).addHeader("authorization" , "[]").get().build();
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
                    pd = gson.fromJson(jsonResponse.optString("personal_details"), personal_details.class);
                    rd  = gson.fromJson(jsonResponse.optString("religious_details"), religious_details.class);
                    fd  = gson.fromJson(jsonResponse.optString("family_details"), familyDetails.class);
                    ed  = gson.fromJson(jsonResponse.optString("education"), education.class);
                    cd  = gson.fromJson(jsonResponse.optString("contact_details"), ContactDetails.class);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.swipe.setRefreshing(false);
                            if (cd!=null){
                                Gson gson = new Gson();
                                String json = gson.toJson(cd);
                                editor.putString("cd", json);
                                editor.commit();
                                common.setParams(binding.fatherContact, cd.getFath_contact());
                                common.setParams(binding.whatsapp, cd.getWhatsapp_no());
                                common.setParams(binding.PermanentAddress, cd.getPermanent_addr());
                                common.setParams(binding.PresentAddress, cd.getPresent_addr());
                            }
                            if (ed!=null){
                                Gson gson = new Gson();
                                String json = gson.toJson(ed);
                                editor.putString("ed", json);
                                editor.commit();
                                common.setParams(binding.education, ed.getEdu());
                                common.setParams(binding.educationDetails, ed.getEdu_detail());
                                common.setParams(binding.occupation, ed.getOccupation());
                                common.setParams(binding.occupationDetails, ed.getOcu_detail());
                                common.setParams(binding.annualIncome, ed.getAnu_income());
                            }
                            if (fd!=null){
                                Gson gson = new Gson();
                                String json = gson.toJson(fd);
                                editor.putString("fd", json);
                                editor.commit();
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
                                editor.putString("rd", json);
                                editor.commit();
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
                               editor.putString("pd", json2);
                               editor.commit();
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
                        }

                    });

                } catch (JSONException e) {
                    Log.d("TAG", "onResponseXx: "+e);
                    e.printStackTrace();
                }

            }
        });
    }
    private void showFileChooser() {
        mGetContent.launch("image/*");
    }
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(uri!=null) {
                                userDatabaseModel model;
                                userDatabaseHelper db = new userDatabaseHelper(getContext());
                                model = db.getUser(0);
                                Bitmap bm = common.uriToBitmap(uri, requireContext());
                                common.uploadImage(bm, requireContext(), model.getId());
                                binding.profile.setImageURI(uri);
                            }
                        }
                    });
//                    Crop.of(uri, outputUri).asSquare().start(getActivity());
 }});
}