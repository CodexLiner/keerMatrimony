package keer.matrimony.ui.dashboard;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import keer.matrimony.Adapters.ProfileListAdapters;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.databinding.FragmentDashboardBinding;
import keer.matrimony.models.data;
import keer.matrimony.ui.Activitys.HomeActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends Fragment {
    List<data> list;
    Activity activity;

    public DashboardFragment() {
    }

    public DashboardFragment(List<data> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        if (activity==null){
            ((HomeActivity) getActivity()).setActionBarTitle("Latest Profiles");
            ((HomeActivity) getActivity()).hide(View.VISIBLE);

            BottomNavigationView bm = getActivity().findViewById(R.id.nav_view);
            bm.setSelectedItemId(R.id.navigation_dashboard);
            binding.profileRec.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
            //   ProfileListAdapters adapters = new ProfileListAdapters(list);
            if (CONSTANTS.DATA!=null){
                ProfileListAdapters adapters = new ProfileListAdapters(CONSTANTS.DATA);
                binding.profileRec.setAdapter(adapters);
            }else {
                getProfiles();
            }
        }else {

            binding.profileRec.setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false));
            //   ProfileListAdapters adapters = new ProfileListAdapters(list);
            if (CONSTANTS.SEARCHRESULT!=null){
                ProfileListAdapters adapters = new ProfileListAdapters(CONSTANTS.SEARCHRESULT , activity);
                binding.profileRec.setAdapter(adapters);
            }else {
//                getProfiles();
            }
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void getProfiles(){
        Gson gson = new Gson();
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"get-profiles/2").addHeader("authorization" , "[]").get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("TAG", "getProfiles: f ");
                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
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
                            ProfileListAdapters adapter = new ProfileListAdapters(dataList);
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