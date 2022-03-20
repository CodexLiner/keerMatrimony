package keer.matrimony.UIFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import keer.matrimony.databinding.FragmentSignUpBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    String mState , mCountry , mSubcast , first , last , email , mobile , dob , password;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentSignUpBinding.inflate(inflater);
//        complexion status
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
//        country status
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
//        state status
        String[] state = { "Andhra Pradesh",
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

//        String[] state= {
//                "Select State" , "Bakery work" , "Oil extraction" , "NGO service"
//        };
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
        binding.selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
       binding.nextButton.setOnClickListener(new View.OnClickListener() {
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
               if (TextUtils.isEmpty(dob)){
                   Toast.makeText(getActivity(), "Select Birth Date First", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (TextUtils.isEmpty(city)){
                   Toast.makeText(getActivity(), "Enter City First", Toast.LENGTH_SHORT).show();
                   return;
               }

           }
       });
        return binding.getRoot();
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

}