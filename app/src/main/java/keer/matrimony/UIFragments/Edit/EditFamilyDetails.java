package keer.matrimony.UIFragments.Edit;

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

import keer.matrimony.R;
import keer.matrimony.UIFragments.onBoarding.PartnerPrefrences;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentEditFamilyDetailsBinding;
import keer.matrimony.models.familyDetails;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFamilyDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFamilyDetails extends Fragment {
    FragmentEditFamilyDetailsBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditFamilyDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFamilyDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFamilyDetails newInstance(String param1, String param2) {
        EditFamilyDetails fragment = new EditFamilyDetails();
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
        binding = FragmentEditFamilyDetailsBinding.inflate(inflater);
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setActionBarTitle("Edit Family Details");
        if (CONSTANTS.family!=null){
            familyDetails fd = CONSTANTS.family;
            binding.faherName.setText(fd.getFather_name());
            binding.fatherOcu.setText(fd.getFather_occupation());
            binding.motherName.setText(fd.getMother_name());
            binding.motherOcu.setText(fd.getMother_occupation());
            binding.mSister.setText(fd.getNo_married_sis());
//            binding.umSister.setText(fd.getFather_name());
            binding.mBrother.setText(fd.getNo_married_bro());
            binding.unBrother.setText(fd.getNo_unmarried_bro());
            binding.cars.setText(fd.getCar());
            binding.house.setText(fd.getHouse());
        }
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
                ((HomeActivity) getActivity()).setPersonalDetails(map , CONSTANTS.FAMILYDETAILS , model.getId());
                requireActivity().onBackPressed();


            }
        });


        return binding.getRoot();
    }
}