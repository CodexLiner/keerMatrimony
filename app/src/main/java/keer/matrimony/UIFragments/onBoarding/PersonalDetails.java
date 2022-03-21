package keer.matrimony.UIFragments.onBoarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
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
import keer.matrimony.databinding.FragmentPersonalDetailsBinding;
import keer.matrimony.ui.Activitys.MainActivity;
import keer.matrimony.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalDetails extends Fragment {
    FragmentPersonalDetailsBinding binding;
    String Height , mStatus ,cStatus , Weight , dStatus  , Disabilty , bGroup , Diet;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PersonalDetails() {
        // Required empty public constructor
    }

    public static PersonalDetails newInstance(String param1, String param2) {
        PersonalDetails fragment = new PersonalDetails();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setActionBarTitle("Personal Details");

        binding = FragmentPersonalDetailsBinding.inflate(inflater);
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
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weight = binding.weight.getText().toString();
                if (TextUtils.isEmpty(Height)){
                    Toast.makeText(getActivity(), "Select Height", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mStatus)){
                    Toast.makeText(getActivity(), "Select Marital Status", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cStatus)){
                    Toast.makeText(getActivity(), "Select Complexion Status", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Weight)){
                    Toast.makeText(getActivity(), "Select Weight", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Diet)){
                    Toast.makeText(getActivity(), "Select Diet Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Disabilty)){
                    Toast.makeText(getActivity(), "Select Disability", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(bGroup)){
                    Toast.makeText(getActivity(), "Select Blood Group", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                ((MainActivity) getActivity()).setPersonalDetails(map , CONSTANTS.PERSONALDETAILS , model.getId());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ReligiuosInformation w = new ReligiuosInformation();
                transaction.replace(R.id.ContainerMain , w);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return binding.getRoot();
    }
}