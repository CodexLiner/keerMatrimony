package keer.matrimony.UIFragments.Edit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
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

import keer.matrimony.R;
import keer.matrimony.UIFragments.onBoarding.ContactInformation;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.databinding.FragmentEditReligionBinding;
import keer.matrimony.databinding.FragmentReligiuosInformationBinding;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.ui.Activitys.HomeActivity;
import keer.matrimony.ui.Activitys.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditReligion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditReligion extends Fragment {
    FragmentEditReligionBinding binding;
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

    public EditReligion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditReligion.
     */
    // TODO: Rename and change types and number of parameters
    public static EditReligion newInstance(String param1, String param2) {
        EditReligion fragment = new EditReligion();
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
        ((HomeActivity) getActivity()).setActionBarTitle("Edit Religion Details");
        ((HomeActivity) requireActivity()).hide(View.INVISIBLE);
        binding = FragmentEditReligionBinding.inflate(inflater);
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
        String[] maritalStatus= {
                "Mother tongue" , "hindi / हिंदी" , "English / अंग्रेजी" , "Urdu / उर्दू"
        };
        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, maritalStatus);
        binding.motherTongue.setAdapter(maritalStatusAdapter);
        binding.motherTongue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    mTongue = maritalStatus[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        Zodiac signs
        String[] ZodiacSigns= {
                "Zodiac Sign" , "Aries / मेष" , "Taurus / वृषभ" , "Gemini / मिथुन","Cancer / कर्क"
                , "Leo / सिंह" , "Virgo / कन्या","Libra / तुला" , "Scorpius / वृश्चिक" , "Sagittarius / धनु",
                "Capricornus / मकर" , "Aquarius / कुंभ" , "Pisces / मीन"
        };
        ArrayAdapter<String> zodiac = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, ZodiacSigns);
        binding.zodiac.setAdapter(zodiac);
        binding.zodiac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    ZodiacSign = ZodiacSigns[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        Manglik Types
        String[] manglik= {
                "Manglik Type" , "Non Manglik" , "Manglik"
        };
        ArrayAdapter<String> manglikAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, manglik);
        binding.manglik.setAdapter(manglikAdapter);
        binding.manglik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    ManglikType = manglik[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        nakshatra Types
        String[] nakshatra= { "Nakshatra" ,
                "Rohini", "Mrigashirsha", "Magha", "Uttara Phalguni", "Hasta", "Swat", "Anuradha", "Mula or Moola", "Uttara Ashadha", "Uttara Bhadrapada" , "Revati" , "i don't know"
        };
        ArrayAdapter<String> nAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, nakshatra);
        binding.nakshatra.setAdapter(nAdapter);
        binding.nakshatra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    Nakshatra = nakshatra[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (CONSTANTS.RELIGIOUSDETAIL!=null){
            if (CONSTANTS.RELIGIOUSDETAIL.getBirth_time()!=null){
                binding.birthDate.setText(CONSTANTS.RELIGIOUSDETAIL.getBirth_time());
            }
            if (CONSTANTS.RELIGIOUSDETAIL.getBirth_place()!=null){
                binding.birthPlace.setText(CONSTANTS.RELIGIOUSDETAIL.getBirth_place());
            }
            if (CONSTANTS.RELIGIOUSDETAIL.getMother_tongue()!=null){
                for (int i = 0; i < maritalStatus.length; i++) {
                    Log.d("TAG", "onCreateView: mt "+CONSTANTS.RELIGIOUSDETAIL.getMother_tongue()+"  ->"+maritalStatus[i]);
                    if (CONSTANTS.RELIGIOUSDETAIL.getMother_tongue()!=null && CONSTANTS.RELIGIOUSDETAIL.getMother_tongue().equalsIgnoreCase(maritalStatus[i])){
                        Log.d("TAG", "onCreateView: mt ");
                        binding.motherTongue.setSelection(i);
                    }
                }
            }
            if (CONSTANTS.RELIGIOUSDETAIL.getZodiac()!=null){
                for (int i = 0; i < ZodiacSigns.length; i++) {
                    if (CONSTANTS.RELIGIOUSDETAIL.getZodiac()!=null && CONSTANTS.RELIGIOUSDETAIL.getZodiac().trim().equalsIgnoreCase(ZodiacSigns[i])){
                        binding.zodiac.setSelection(i);
                    }
                }
            }
            if (CONSTANTS.RELIGIOUSDETAIL.getManglic()!=null){
                for (int i = 0; i < manglik.length; i++) {
                    if (CONSTANTS.RELIGIOUSDETAIL.getManglic()!=null && CONSTANTS.RELIGIOUSDETAIL.getManglic().trim().equals(manglik[i])){
                        binding.manglik.setSelection(i);
                    }
                }
            }
            if (CONSTANTS.RELIGIOUSDETAIL.getNakshtra()!=null){
                for (int i = 0; i < nakshatra.length; i++) {
                    if (CONSTANTS.RELIGIOUSDETAIL.getNakshtra()!=null && CONSTANTS.RELIGIOUSDETAIL.getNakshtra().trim().equals(nakshatra[i])){
                        binding.nakshatra.setSelection(i);
                    }
                }
            }
        }
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                POB = binding.birthPlace.getText().toString();
                DOB = binding.birthDate.getText().toString();
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
                ((HomeActivity) getActivity()).setPersonalDetails(map , CONSTANTS.RELIGIOUS , model.getId());
                getActivity().onBackPressed();
                Toast.makeText(getActivity(), "Details Updated Successfully", Toast.LENGTH_SHORT).show();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                ContactInformation c = new ContactInformation();
//                transaction.replace(R.id.ContainerMain , c);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
        return binding.getRoot();
    }
}