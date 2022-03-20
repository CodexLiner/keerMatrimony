package keer.matrimony.ui.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import keer.matrimony.databinding.SearchFragmentBinding;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.LoginActivity;

public class SearchFragment extends Fragment {
    SearchFragmentBinding binding;


    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SearchFragmentBinding.inflate(inflater);
        ((HomeActivity) getActivity()).setActionBarTitle("Search Profile");
        binding.buttonPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext() , LoginActivity.class));
            }
        });
        return binding.getRoot();
    }


}