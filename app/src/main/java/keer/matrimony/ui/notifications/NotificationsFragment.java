package keer.matrimony.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentNotificationsBinding;
import keer.matrimony.models.data;
import keer.matrimony.models.personal_details;
import keer.matrimony.models.religious_details;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.HomeActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationsFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ((HomeActivity) getActivity()).setActionBarTitle("Your Profile");
        sharedPreferences = getContext().getSharedPreferences("profile" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.contains("rd")){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("rd", "");
            religious_details rd = gson.fromJson(json, religious_details.class);
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
        }else { getProfiles(); }
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
        binding.dob.setText(model.getDob());
        binding.dob2.setText(model.getDob());
        String name = model.getFirst_name()+" "+model.getLast_name();
        binding.fullName.setText(name);
        binding.subcast.setText(model.getSubcaste());
        binding.personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_EditPerosnal);
            }
        });
        binding.religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_notifications_to_EditReligion);
            }
        });
//        binding.motherTongue.setText(model.ge);
        return root;
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
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"user-detail/2").addHeader("authorization" , "[]").get().build();
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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

}