package keer.matrimony.UIFragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentSignUpBinding;
import keer.matrimony.models.data;
import keer.matrimony.other.ImageUpload;
import keer.matrimony.ui.Activitys.MainActivity;
import keer.matrimony.utils.onBoardingList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    String mState , mCountry , mSubcast, mGender ,mBirth , first , last , email , mobile , dob , password;
    Calendar myCalendar =Calendar.getInstance();
    Uri outputUri = null;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
    //        date picker
    DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            binding.birthDate.setEnabled(true);
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            String myFormat="yyyy-MM-dd";
            SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
            binding.birthDate.setText(dateFormat.format(myCalendar.getTime()));
            mBirth = dateFormat.format(myCalendar.getTime()) ;

        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentSignUpBinding.inflate(inflater);
        binding.birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.birthDate.setEnabled(false);
                new DatePickerDialog(getActivity(),startDate,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                binding.birthDate.setEnabled(true);
            }
        });
        ArrayAdapter<String> subcastAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.subcast);
        binding.subcast.setAdapter(subcastAdapter);
        binding.subcast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mSubcast = onBoardingList.subcast[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        country status

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.country);
        binding.country.setAdapter(countryAdapter);
        binding.country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mCountry = onBoardingList.country[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //        country status

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.genderAray);
        binding.gender.setAdapter(genderAdapter);
        binding.gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mGender = onBoardingList.genderAray[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        state status

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.state);
        binding.state.setAdapter(stateAdapter);
        binding.state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mState = onBoardingList.state[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
       binding.nextButton.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.R)
           @Override
           public void onClick(View v) {

              //  startActivity(new Intent(getContext() , MainActivity.class));
               first =  binding.userFirst.getText().toString();
               last =  binding.userLastName.getText().toString();
               email =  binding.userEmail.getText().toString();
               mobile =  binding.userMobile.getText().toString();
               password =  binding.userPassword.getText().toString();
               String city =  binding.userCity.getText().toString();
               if (TextUtils.isEmpty(first)){
                   Toast.makeText(getActivity(), "Enter First Name", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(last)){
                   Toast.makeText(getActivity(), "Enter Last Name", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(email)){
                   Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(mobile)){
                   Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(mBirth)){
                   Log.d("TAG", "onClick: "+mBirth);
                   Toast.makeText(getActivity(), "Select Birth Date", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(mSubcast)){
                   Toast.makeText(getActivity(), "Select Subcast First", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(mCountry)){
                   Toast.makeText(getActivity(), "Select Country First", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(mState)){
                   Toast.makeText(getActivity(), "Select State First", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(city)){
                   Toast.makeText(getActivity(), "Enter City First", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(password)){
                   Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (outputUri==null){
                   Toast.makeText(getActivity(), "Select Profile Picture", Toast.LENGTH_SHORT).show();
                   return;
               }
               Map<String , String> map = new HashMap<>();
               map.put("first_name" , first);
               map.put("last_name" , last);
               map.put("email" , email);
               map.put("country_code" ,"+91" );
               map.put("mobile" , mobile);
               map.put("dob" , mBirth );
               map.put("subcaste" ,mSubcast );
               map.put("country" ,mCountry );
               map.put("state" , mState);
               map.put("city" , city);
               map.put("password" , password);
               map.put("gender" ,mGender );
              // map.put("profile" ,"null" );
               Register(map);


           }
       });
        return binding.getRoot();
    }

    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = getActivity().getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            binding.selectImage.setImageBitmap(image);
            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void Register(Map<String , String> map) {
        Gson gson = new Gson();
        Bitmap bm =  uriToBitmap(outputUri);
        File file = new File(getActivity().getExternalCacheDir(), "keerProfile.jpg");
        try {
            boolean f = file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        }catch (Exception e){
           e.printStackTrace();
        }
        if (map==null){
            return;
        }
        String jsonString = gson.toJson(map);
        final RequestBody requestBody2 = RequestBody.create(jsonString , MediaType.get(CONSTANTS.mediaType));
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"register").addHeader("authorization" , "[]").post(requestBody2).build();
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
                                Toast.makeText(getActivity(), jsonResponse.optString("message"), Toast.LENGTH_SHORT).show();
                            }else {
                                data userData = gson.fromJson(jsonResponse.optString("details").toString() , data.class);
                                userDatabaseHelper db = new userDatabaseHelper(getContext());
                                db.insertUser(userData);
                                userDatabaseModel model = db.getUser(0);
                                ImageUpload imageUpload = new ImageUpload(file , model.getId());
                                imageUpload.execute();
                                Toast.makeText(getActivity(), "Register SuccessFully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext() , MainActivity.class));
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
    //method to show file chooser
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
                            outputUri = uri;
                            binding.selectImage.setImageURI(uri);
                        }
                    });
//                    Crop.of(uri, outputUri).asSquare().start(getActivity());
 }});

}