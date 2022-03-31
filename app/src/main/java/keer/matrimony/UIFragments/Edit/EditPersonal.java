package keer.matrimony.UIFragments.Edit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import keer.matrimony.R;
import keer.matrimony.UIFragments.onBoarding.ReligiuosInformation;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentEditPersonalBinding;
import keer.matrimony.databinding.FragmentPersonalDetailsBinding;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditPersonal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditPersonal extends Fragment {
    FragmentEditPersonalBinding binding;
    String Height , mStatus ,cStatus , Weight , dStatus  , Disabilty , bGroup , Diet;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditPersonal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditPersonal.
     */
    // TODO: Rename and change types and number of parameters
    public static EditPersonal newInstance(String param1, String param2) {
        EditPersonal fragment = new EditPersonal();
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
        // Inflate the layout for this fragment
        binding = FragmentEditPersonalBinding.inflate(inflater);
        ((HomeActivity) getActivity()).setActionBarTitle("Edit Personal Profile Details");
        Weight = binding.weight.getText().toString();
//        heightAdapter
        String[] heightArray = { "Height", "5.0 feet", "5.5 feet", "6.0 feet", "6.5 feet"};
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, heightArray);
        binding.height.setAdapter(heightAdapter);
        binding.height.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Height = heightArray[position];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        martial status
        String[] maritalStatus= {
                "Marital Status" , "Married" , "UnMarried" , "Divorced"
        };
        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, maritalStatus);
        binding.maritalStatus.setAdapter(maritalStatusAdapter);
        binding.maritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mStatus = maritalStatus[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        complexion status
        String[] complexion= {
                "Complexion Type" , "Extremely fair skin" , "Fair skin" , "Medium skin" , "Olive skin" , " Brown skin" , "Black skin"
        };
        ArrayAdapter<String> complexionAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, complexion);
        binding.complextion.setAdapter(complexionAdapter);
        binding.complextion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    cStatus = complexion[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        diet status
        String[] diet= {
                "Diet Status" , "vegetarians " , "non-vegetarians" , "Flexitarian "
        };
        ArrayAdapter<String> dietArray = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, diet);
        binding.diet.setAdapter(dietArray);
        binding.diet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Diet = diet[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        blood Group
        String[] bloodGroup= {
                "Blood Group" , "A RhD positive (A+)" , "A RhD negative (A-)" , "B RhD positive (B+)" , "B RhD positive (B+)",
                "O RhD positive (O+)","O RhD negative (O-)","AB RhD positive (AB+)","AB RhD negative (AB-)",
        };
        ArrayAdapter<String> bloodArray = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, bloodGroup);
        binding.bloodGroup.setAdapter(bloodArray);
        binding.bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    bGroup = bloodGroup[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        disAbility status
        String[] disAbility= {
                "Disability" , "Blindness." , "Handicap" , "Cerebral Palsy"
        };
        ArrayAdapter<String> disAbilityAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, disAbility);
        binding.disAbility.setAdapter(disAbilityAdapter);
        binding.disAbility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Disabilty = disAbility[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        Pre Filled data function
        if (CONSTANTS.PERSONALDETAIL!=null){
            if (CONSTANTS.PERSONALDETAIL.getWeight()!=null){
                binding.weight.setText(CONSTANTS.PERSONALDETAIL.getWeight());
            }
            for (int i = 0; i < heightArray.length; i++) {
                if (CONSTANTS.PERSONALDETAIL.getHeight()!=null && CONSTANTS.PERSONALDETAIL.getHeight().equals(heightArray[i])){
                    binding.height.setSelection(i);
                }
            }
            for (int i = 0; i < maritalStatus.length; i++) {
                if (CONSTANTS.PERSONALDETAIL.getMaretial_status()!=null && CONSTANTS.PERSONALDETAIL.getMaretial_status().equals(maritalStatus[i])){
                    binding.maritalStatus.setSelection(i);
                }
            }
            for (int i = 0; i < complexion.length; i++) {
                if (CONSTANTS.PERSONALDETAIL.getComplexion()!=null && CONSTANTS.PERSONALDETAIL.getComplexion().equals(complexion[i])){
                    binding.complextion.setSelection(i);
                }
            }
            for (int i = 0; i < disAbility.length; i++) {
                if (CONSTANTS.PERSONALDETAIL.getDisablity()!=null && CONSTANTS.PERSONALDETAIL.getDisablity().equals(disAbility[i])){
                    binding.disAbility.setSelection(i);
                }
            }
            for (int i = 0; i < bloodGroup.length; i++) {
                if (CONSTANTS.PERSONALDETAIL.getBlood_group()!=null && CONSTANTS.PERSONALDETAIL.getBlood_group().equals(bloodGroup[i])){
                    binding.bloodGroup.setSelection(i);
                }
            }
        }
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weight = binding.weight.getText().toString();
//                if (TextUtils.isEmpty(Height)){
//                    Toast.makeText(getActivity(), "Select Height", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(mStatus)){
//                    Toast.makeText(getActivity(), "Select Marital Status", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(cStatus)){
//                    Toast.makeText(getActivity(), "Select Complexion Status", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(Weight)){
//                    Toast.makeText(getActivity(), "Select Weight", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(Diet)){
//                    Toast.makeText(getActivity(), "Select Diet Type", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(Disabilty)){
//                    Toast.makeText(getActivity(), "Select Disability", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(bGroup)){
//                    Toast.makeText(getActivity(), "Select Blood Group", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Map<String , String> map = new HashMap<>();
                map.put("height" , Height);
                map.put("maretial_status" , mStatus);
                map.put("complexion" , cStatus);
                map.put("weight" , Weight);
                map.put("diet" , Diet);
                map.put("disablity" , Disabilty);
                map.put("blood_group" , bGroup);
                userDatabaseHelper db = new userDatabaseHelper(getContext());
                userDatabaseModel model = db .getUser(0);
                ((HomeActivity) getActivity()).setPersonalDetails(map , CONSTANTS.PERSONALDETAILS , model.getId());
                getActivity().onBackPressed();
                Toast.makeText(getActivity(), "Details Updated Successfully", Toast.LENGTH_SHORT).show();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                ReligiuosInformation w = new ReligiuosInformation();
//                transaction.replace(R.id.ContainerMain , w);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
        return binding.getRoot();
    }
}