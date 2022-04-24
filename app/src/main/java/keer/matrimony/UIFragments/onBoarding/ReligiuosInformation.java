package keer.matrimony.UIFragments.onBoarding;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import keer.matrimony.other.CONSTANTS;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentReligiuosInformationBinding;
import keer.matrimony.ui.Activitys.MainActivity;
import keer.matrimony.R;
import keer.matrimony.utils.onBoardingList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReligiuosInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReligiuosInformation extends Fragment {
    FragmentReligiuosInformationBinding binding;
    Calendar myCalendar =Calendar.getInstance();
    SimpleDateFormat simpleDateFormat ;
    String Gotra , mTongue  , DOB , POB , ZodiacSign , ManglikType , Nakshatra ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReligiuosInformation() {
        // Required empty public constructor
    }

    public static ReligiuosInformation newInstance(String param1, String param2) {
        ReligiuosInformation fragment = new ReligiuosInformation();
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
        ((MainActivity) getActivity()).setActionBarTitle("Religious Details");
        binding = FragmentReligiuosInformationBinding.inflate(inflater);
//        date of birth picker
        Gotra = binding.gotra.getText().toString();
        POB = binding.birthPlace.getText().toString();
        DOB = binding.birthDate.getText().toString();

        DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="dd MMM yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                binding.birthDate.setText(dateFormat.format(myCalendar.getTime()));
                SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd" , Locale.US);
            }
        };
        binding.birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String s  = selectedHour + ":" + selectedMinute;
                        binding.birthDate.setText(s);
                    }
                }, hour, minute, true);//Yes 24 hour time
//              mTimePicker.setTitle("Select Time");
                mTimePicker.show();
//                new DatePickerDialog(getActivity(),startDate,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
//        mother tongue status
        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.motherTongue);
        binding.motherTongue.setAdapter(maritalStatusAdapter);
        binding.motherTongue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mTongue =  onBoardingList.motherTongue[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        Zodiac signs
        ArrayAdapter<String> zodiac = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.ZodiacSigns);
        binding.zodiac.setAdapter(zodiac);
        binding.zodiac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    ZodiacSign = onBoardingList.ZodiacSigns[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        Manglik Types

        ArrayAdapter<String> manglikAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.manglik);
        binding.manglik.setAdapter(manglikAdapter);
        binding.manglik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    ManglikType = onBoardingList.manglik[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        nakshatra Types
        ArrayAdapter<String> nAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, onBoardingList.nakshatra);
        binding.nakshatra.setAdapter(nAdapter);
        binding.nakshatra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Nakshatra = onBoardingList.nakshatra[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gotra = binding.gotra.getText().toString();
                POB = binding.birthPlace.getText().toString();
                DOB = binding.birthDate.getText().toString();
//                if (TextUtils.isEmpty(Gotra)){
//                    Toast.makeText(getActivity(), "Select Gotra", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (TextUtils.isEmpty(mTongue)){
                    Toast.makeText(getActivity(), "Select Mother Tongue", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(DOB)){
                    Toast.makeText(getActivity(), "Select DOB", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(POB)){
                    Toast.makeText(getActivity(), "Enter POB", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ZodiacSign)){
                    Toast.makeText(getActivity(), "Select Zodiac Sign", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ManglikType)){
                    Toast.makeText(getActivity(), "Select Manglik Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Nakshatra)){
                    Toast.makeText(getActivity(), "Select Nakshatra", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String , String> map = new HashMap<>();
                map.put("mother_tongue" , mTongue);
                map.put("birth_time" , DOB);
                map.put("birth_place" , POB);
                map.put("zodiac" , ZodiacSign);
                map.put("manglic" , ManglikType);
                map.put("nakshtra" , Nakshatra);
                userDatabaseHelper db = new userDatabaseHelper(getContext());
                userDatabaseModel model = db .getUser(0);
                ((MainActivity) getActivity()).setPersonalDetails(map , CONSTANTS.RELIGIOUS , model.getId());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ContactInformation c = new ContactInformation();
                transaction.replace(R.id.ContainerMain , c);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return binding.getRoot();
    }
}