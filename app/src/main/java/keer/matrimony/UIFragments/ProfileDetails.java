package keer.matrimony.UIFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import keer.matrimony.CONSTANTS;
import keer.matrimony.R;
import keer.matrimony.databinding.FragmentPersonalDetailsBinding;
import keer.matrimony.databinding.FragmentProfileDetailsBinding;
import keer.matrimony.models.data;

public class ProfileDetails extends Fragment {
    @NonNull FragmentProfileDetailsBinding binding;
    data data;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileDetails(data data) {
       this.data = data;
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
        binding = FragmentProfileDetailsBinding.inflate(inflater);
        // Inflate the layout for this fragment
        OnBackPressedCallback callback =  new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Toast.makeText(getContext(), "in profile", Toast.LENGTH_SHORT).show();
//                getActivity().onBackPressed();
            }

        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
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