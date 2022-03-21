package keer.matrimony.UIFragments.onBoarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentPartnerPrefrencesBinding;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartnerPrefrences#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartnerPrefrences extends Fragment {
    FragmentPartnerPrefrencesBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PartnerPrefrences() {
        // Required empty public constructor
    }

    public static PartnerPrefrences newInstance(String param1, String param2) {
        PartnerPrefrences fragment = new PartnerPrefrences();
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
        ((MainActivity) getActivity()).setActionBarTitle("Partner Preferences");
        binding = FragmentPartnerPrefrencesBinding.inflate(inflater);
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
                ((MainActivity) getActivity()).setPersonalDetails(map , CONSTANTS.PARTNERPREF , model.getId());
                startActivity(new Intent(getContext() , HomeActivity.class));
                getActivity().finishAffinity();
            }
        });

        return binding.getRoot();
    }
}