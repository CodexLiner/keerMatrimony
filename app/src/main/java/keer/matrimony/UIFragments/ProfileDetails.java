package keer.matrimony.UIFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.databinding.FragmentProfileDetailsBinding;
import keer.matrimony.models.data;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.SearchResult;

public class ProfileDetails extends Fragment {
    @NonNull FragmentProfileDetailsBinding binding;
    data data;
    Activity activity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileDetails(data data , Activity activity) {
       this.data = data;
       this.activity = activity;
    }

    public ProfileDetails() {

    }

    public static ProfileDetails newInstance(String param1, String param2) {
        ProfileDetails fragment = new ProfileDetails();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = CONSTANTS.DATAs;
        binding = FragmentProfileDetailsBinding.inflate(inflater);
        // Inflate the layout for this fragment
       if (activity==null){
           ((HomeActivity) getActivity()).setActionBarTitle(data.getFirst_name()+" "+data.getLast_name());
       }else {
           ((SearchResult) getActivity()).setActionBarTitle(data.getFirst_name()+" "+data.getLast_name());

       }
        if (data.getDob()!=null){
            binding.dob.setText(data.getDob());
            binding.dob2.setText(data.getDob());
        }
        if (data.getSubcaste()!=null){
            binding.subcast.setText(data.getSubcaste());
        }
        if (data.getHeight()!=null){
            binding.height.setText(data.getHeight());
        }
        if (data.getWeight()!=null){
            binding.weight.setText(data.getHeight());
        }
        if (data.getProfile()!=null){
            Picasso.with(getContext()).load(CONSTANTS.BASEURLPROFILE + data.getProfile()).into(binding.profile);
        }






        return binding.getRoot();
    }
}