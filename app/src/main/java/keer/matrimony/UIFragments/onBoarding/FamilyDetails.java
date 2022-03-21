package keer.matrimony.UIFragments.onBoarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentFamilyDetailsBinding;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.MainActivity;
import keer.matrimony.R;

public class FamilyDetails extends Fragment {
    FragmentFamilyDetailsBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FamilyDetails() {
        // Required empty public constructor
    }

    public static FamilyDetails newInstance(String param1, String param2) {
        FamilyDetails fragment = new FamilyDetails();
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
        binding = FragmentFamilyDetailsBinding.inflate(inflater);
        ((MainActivity) getActivity()).setActionBarTitle("Family Details");
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  fatherName = binding.faherName.getText().toString();
                String  fatherOcu = binding.fatherOcu.getText().toString();
                String  motherName = binding.motherName.getText().toString();
                String  motherOcu = binding.motherOcu.getText().toString();
                String  mSister = binding.mSister.getText().toString();
                String  uSister = binding.umSister.getText().toString();
                String  mBrother = binding.mBrother.getText().toString();
                String  uBrother = binding.unBrother.getText().toString();
                String  uncleName = binding.uncleName.getText().toString();
                String  uncleGotra = binding.uncleGotra.getText().toString();
                String  car = binding.cars.getText().toString();
                String  house = binding.house.getText().toString();
                if (TextUtils.isEmpty(fatherName)){
                    Toast.makeText(getActivity(), "Enter Father Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fatherOcu)){
                    Toast.makeText(getActivity(), "Enter Fathers Occupation", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(motherName)){
                    Toast.makeText(getActivity(), "Enter Mother Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mBrother)){
                    Toast.makeText(getActivity(), "Enter No Of Married Brothers", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uBrother)){
                    Toast.makeText(getActivity(), "Enter No Of UnMarried Brothers", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mSister)){
                    Toast.makeText(getActivity(), "Enter No Of Married Sisters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uSister)){
                    Toast.makeText(getActivity(), "Enter No Of UnMarried Sisters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uncleName)){
                    Toast.makeText(getActivity(), "Enter Uncles Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(uncleGotra)){
                    Toast.makeText(getActivity(), "Enter Uncles Gotra", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(car)){
                    Toast.makeText(getActivity(), "Enter No Of Cars", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(house)){
                    Toast.makeText(getActivity(), "Enter No Of House", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String ,String> map = new HashMap<>();
                map.put("father_name" , fatherName);
                map.put("father_occupation" , fatherOcu);
                map.put("mother_name" , motherName);
                map.put("mother_occupation" , motherOcu);
                map.put("no_married_bro" , mBrother);
                map.put("no_unmarried_bro" , uBrother);
                map.put("no_married_sis" , mSister);
                map.put("no_unmarried_sis" , uSister);
                map.put("maternal_name" , uncleName);
                map.put("maternal_gotra" , uncleGotra);
                map.put("house" , house);
                map.put("car" , car);
                Log.d("TAG", "onResponseX: "+map.toString());
                userDatabaseHelper db = new userDatabaseHelper(getContext());
                userDatabaseModel model = db .getUser(0);
                ((MainActivity) getActivity()).setPersonalDetails(map , CONSTANTS.FAMILYDETAILS , model.getId());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                PartnerPrefrences educationDetails = new PartnerPrefrences();
                transaction.replace(R.id.ContainerMain , educationDetails);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });



        return binding.getRoot();
    }
}