package keer.matrimony.UIFragments.Edit;

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
import java.util.Objects;

import keer.matrimony.R;
import keer.matrimony.UIFragments.onBoarding.EducationDetails;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentEditContactBinding;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditContact extends Fragment {
    FragmentEditContactBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditContact.
     */
    // TODO: Rename and change types and number of parameters
    public static EditContact newInstance(String param1, String param2) {
        EditContact fragment = new EditContact();
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
        binding = FragmentEditContactBinding.inflate(inflater);
        final String[] fatherNumber = new String[1];
        final String[] whatsappNumber = new String[1];
        final String[] Pmadd = new String[1];
        final String[] perAdd = new String[1];
        fatherNumber[0] = binding.father.getText().toString();
        whatsappNumber[0] = binding.whatsapp.getText().toString();
        Pmadd[0] = binding.PermanentAddress.getText().toString();
        perAdd[0] = binding.PresentAddress.getText().toString();

        ((HomeActivity) requireActivity()).hide(View.INVISIBLE);


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
                ((HomeActivity) getActivity()).setPersonalDetails(map , CONSTANTS.CONTACTINFO , model.getId());
                getActivity().onBackPressed();
                Toast.makeText(getActivity(), "Details Updated Successfully", Toast.LENGTH_SHORT).show();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                EducationDetails educationDetails = new EducationDetails();
//                transaction.replace(R.id.ContainerMain , educationDetails);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });

        return binding.getRoot();
    }
}