package keer.matrimony.ui.list;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import keer.matrimony.Adapters.ProfileListAdapters;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
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

public class ListFragment extends Fragment {
    List<data> list;
    Activity activity;
    String nextPage = "";
    boolean refresh = false;
    boolean noLast = false;

    public ListFragment() {
    }

    public ListFragment(List<data> list, Activity activity) {
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
            ((HomeActivity) requireActivity()).setActionBarTitle("Latest Profiles");
            ((HomeActivity) requireActivity()).hide(View.VISIBLE);

            BottomNavigationView bm = requireActivity().findViewById(R.id.nav_view);
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
        binding.nestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY== v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    if (!refresh && !noLast && CONSTANTS.nextPage.length() >= 10){
                        binding.pBar.setVisibility(View.VISIBLE);
                        refresh = true;
                        Log.d("TAG", "onScrollChange: "+CONSTANTS.nextPage);
                        getProfiles(CONSTANTS.nextPage);
                    }else {
                        Snackbar.make(binding.profileRec ,"No More Profiles Left" , Snackbar.LENGTH_SHORT).setAction("Dismiss", x -> {}).show();
                    }
                }
            }
        });
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noLast = false;
                if (activity==null){
                    getProfiles();
                    binding.swipe.setRefreshing(true);
                    refresh = true;
                }else {
                    binding.swipe.setRefreshing(false);
                    refresh = false;
                }
            }
        });
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void getProfiles(){
        userDatabaseModel model;
        userDatabaseHelper db = new userDatabaseHelper(requireContext());
        model = db.getUser(0);
        Gson gson = new Gson();
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"get-profiles/"+model.getId()).addHeader("authorization" , "[]").get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    Type type = new TypeToken<List<data>>(){}.getType();
                    List<data> dataList = gson.fromJson(jsonResponse.getJSONObject("detail").optString("data"), type);
                    nextPage = jsonResponse.optString("next_page_url").toString();
                    CONSTANTS.nextPage = jsonResponse.optString("next_page_url").toString();
                    CONSTANTS.DATA = dataList;
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ProfileListAdapters adapter = new ProfileListAdapters(dataList);
                            binding.profileRec.setAdapter(adapter);
                            binding.swipe.setRefreshing(false);
                            refresh =false;
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void getProfiles(String url){
        userDatabaseModel model;
        userDatabaseHelper db = new userDatabaseHelper(requireContext());
        model = db.getUser(0);
        Gson gson = new Gson();
        Request request = new Request.Builder().url(CONSTANTS.nextPage).addHeader("authorization" , "[]").get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    Type type = new TypeToken<List<data>>(){}.getType();
                    List<data> dataList = gson.fromJson(jsonResponse.getJSONObject("detail").optString("data"), type);
                    nextPage = jsonResponse.optString("next_page_url").toString();
                    CONSTANTS.nextPage = jsonResponse.optString("next_page_url").toString();
                    if (CONSTANTS.DATA!=null){
                        CONSTANTS.DATA.addAll(dataList);
                    }else {
                        CONSTANTS.DATA = dataList;
                    }
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (binding.pBar.getVisibility()==View.VISIBLE && nextPage == null || nextPage.equals("")){
                                noLast = true;
                            }
                            refresh = false;
                            ProfileListAdapters adapter = new ProfileListAdapters(CONSTANTS.DATA);
                            binding.profileRec.setAdapter(adapter);
                            binding.pBar.setVisibility(View.GONE);

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}