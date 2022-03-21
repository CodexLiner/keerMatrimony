package keer.matrimony.UIFragments.onBoarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentContactInformationBinding;
import keer.matrimony.ui.Activitys.MainActivity;
import keer.matrimony.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInformation extends Fragment {
    FragmentContactInformationBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactInformation() {
        // Required empty public constructor
    }

    public static ContactInformation newInstance(String param1, String param2) {
        ContactInformation fragment = new ContactInformation();
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
        binding = FragmentContactInformationBinding.inflate(inflater);
        ((MainActivity) getActivity()).setActionBarTitle("Contact Details");
        final String[] fatherNumber = new String[1];
        final String[] whatsappNumber = new String[1];
        final String[] Pmadd = new String[1];
        final String[] perAdd = new String[1];
        fatherNumber[0] = binding.father.getText().toString();
        whatsappNumber[0] = binding.whatsapp.getText().toString();
        Pmadd[0] = binding.PermanentAddress.getText().toString();
        perAdd[0] = binding.PresentAddress.getText().toString();


        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fatherNumber[0] = binding.father.getText().toString();
                whatsappNumber[0] = binding.whatsapp.getText().toString();
                Pmadd[0] = binding.PermanentAddress.getText().toString();
                perAdd[0] = binding.PresentAddress.getText().toString();
                if (TextUtils.isEmpty(fatherNumber[0])){
                    Toast.makeText(getActivity(), "Enter Father Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(whatsappNumber[0])){
                    Toast.makeText(getActivity(), "Enter Whatsapp Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(perAdd[0])){
                    Toast.makeText(getActivity(), "Enter Present Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fatherNumber[0])){
                    Toast.makeText(getActivity(), "Enter Permanent Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String , String> map = new HashMap<>();
                map.put("fath_contact" , fatherNumber[0]);
                map.put("whatsapp_no" , whatsappNumber[0]);
                map.put("present_addr" , perAdd[0]);
                map.put("permanent_addr" , Pmadd[0]);
                userDatabaseHelper db = new userDatabaseHelper(getContext());
                userDatabaseModel model = db .getUser(0);
                ((MainActivity) getActivity()).setPersonalDetails(map , CONSTANTS.CONTACTINFO , model.getId());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                EducationDetails educationDetails = new EducationDetails();
                transaction.replace(R.id.ContainerMain , educationDetails);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return binding.getRoot();
    }
}