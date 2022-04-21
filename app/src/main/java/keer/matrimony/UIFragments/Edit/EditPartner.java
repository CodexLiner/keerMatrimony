package keer.matrimony.UIFragments.Edit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentEditPartnerBinding;
import keer.matrimony.databinding.FragmentPartnerPrefrencesBinding;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditPartner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditPartner extends Fragment {
    FragmentEditPartnerBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditPartner() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditPartner.
     */
    // TODO: Rename and change types and number of parameters
    public static EditPartner newInstance(String param1, String param2) {
        EditPartner fragment = new EditPartner();
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
        binding = FragmentEditPartnerBinding.inflate(inflater);
        ((HomeActivity) requireActivity()).hide(View.INVISIBLE);
        ((HomeActivity) requireActivity()).setActionBarTitle("Edit Partnert Details");
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partner = binding.partnerPref.getText().toString();
                if (TextUtils.isEmpty(partner)){
                    binding.partnerPref.setError("Required");
                    return;
                }
                Map<String  , String> map = new HashMap<>();
                map.put("partner_preference" , partner);
                userDatabaseHelper db = new userDatabaseHelper(getContext());
                userDatabaseModel model = db .getUser(0);
                ((HomeActivity) requireActivity()).setPersonalDetails(map , CONSTANTS.PARTNERPREF , model.getId());
                requireActivity().onBackPressed();
                Toast.makeText(getActivity(), "Details Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}