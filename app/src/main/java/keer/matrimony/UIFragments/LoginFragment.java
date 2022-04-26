package keer.matrimony.UIFragments;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.databinding.FragmentLoginBinding;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.models.data;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) throws NullPointerException{
      binding = FragmentLoginBinding.inflate(inflater);
      binding.signUpButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
              SignUpFragment educationDetails = new SignUpFragment();
              transaction.replace(R.id.ContainerMain , educationDetails);
              transaction.addToBackStack(null);
              transaction.commit();
          }
      });
      binding.loginButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String email = binding.userEmail.getText().toString().trim();
              String pass = binding.userPassword.getText().toString().trim();
              if (TextUtils.isEmpty(email)){
                  binding.userEmail.setError("Required");
                  return;
              }
              if (TextUtils.isEmpty(pass)){
                  binding.userPassword.setError("Required");
                  return;
              }
              Verify(email , pass);
          }
      });

      return binding.getRoot();
    }
    private void Verify(String email, String pass) {
        Gson gson = new Gson();
        Map<String , String > map = new HashMap<>();
        map.put("email" , email);
        map.put("password" , pass);
        String jsonString = gson.toJson(map);
        final RequestBody requestBody = RequestBody.create(jsonString , MediaType.get(CONSTANTS.mediaType));
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"login").addHeader("authorization" , "[]").post(requestBody).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonResponse.optBoolean("error")){
                                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }else {
                                data userData = gson.fromJson(jsonResponse.optString("data").toString() , data.class);
                                userDatabaseHelper  db = new userDatabaseHelper(getContext());
                                Log.d("TAG", "insertUser: "+userData.toString());
                                db.insertUser(userData);
                                userDatabaseModel model = db.getUser(0);
                                Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext() , HomeActivity.class));
                                getActivity().overridePendingTransition(0,0);

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