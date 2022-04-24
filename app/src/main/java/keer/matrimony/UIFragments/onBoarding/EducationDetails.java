package keer.matrimony.UIFragments.onBoarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentEducationDetailsBinding;
import keer.matrimony.ui.Activitys.MainActivity;
import keer.matrimony.R;
import keer.matrimony.utils.onBoardingList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EducationDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EducationDetails extends Fragment {
    FragmentEducationDetailsBinding binding;
    String Edu , EduD , Ocu , OcuT , Prof , Annual;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EducationDetails() {
        // Required empty public constructor
    }

    public static EducationDetails newInstance(String param1, String param2) {
        EducationDetails fragment = new EducationDetails();
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
        ((MainActivity) getActivity()).setActionBarTitle("Education Details");

        binding = FragmentEducationDetailsBinding.inflate(inflater);
        Prof = binding.occupationDetails.getText().toString();
        EduD = binding.educationDetails.getText().toString();
//        heightAdapter
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.Education);
        binding.Education.setAdapter(heightAdapter);
        binding.Education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Edu = onBoardingList.Education[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        martial status
        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.Occupation);
        binding.occupation.setAdapter(maritalStatusAdapter);
        binding.occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Ocu = onBoardingList.Occupation[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        complexion status
        String[] complexion= {
                "Occupation Type" , "Bakery work" , "Oil extraction" , "NGO service"
        };
        ArrayAdapter<String> complexionAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, complexion);
        binding.profession.setAdapter(complexionAdapter);
        binding.profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    OcuT = complexion[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        diet status

        ArrayAdapter<String> dietArray = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.AnnualIncome);
        binding.annualIncome.setAdapter(dietArray);
        binding.annualIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Annual = onBoardingList.AnnualIncome[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prof = binding.occupationDetails.getText().toString();
                EduD = binding.educationDetails.getText().toString();
                if (TextUtils.isEmpty(Edu)){
                    Toast.makeText(getActivity(), "Select Education", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(EduD)){
                    Toast.makeText(getActivity(), "Enter Education Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Ocu)){
                    Toast.makeText(getActivity(), "Select Occupation", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(OcuT)){
                    Toast.makeText(getActivity(), "Select Occupation Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Prof)){
                    Toast.makeText(getActivity(), "Enter Profession", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Annual)){
                    Toast.makeText(getActivity(), "Select Annual Income", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String , String> map = new HashMap<>();
                map.put("edu" , Edu);
                map.put("edu_detail" , EduD);
                map.put("occupation" , Ocu);
                map.put("profession" , OcuT);
                map.put("ocu_detail" , Prof);
                map.put("anu_income" , Annual);
                Log.d("TAG", "onResponseX: "+map.toString());
                userDatabaseHelper db = new userDatabaseHelper(getContext());
                userDatabaseModel model = db .getUser(0);
                ((MainActivity) getActivity()).setPersonalDetails(map , CONSTANTS.EDUCATIONDETAILS , model.getId());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                FamilyDetails educationDetails = new FamilyDetails();
                transaction.replace(R.id.ContainerMain , educationDetails);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return binding.getRoot();
    }
}