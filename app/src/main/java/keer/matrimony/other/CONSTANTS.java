package keer.matrimony.other;

import java.util.List;

import keer.matrimony.models.ContactDetails;
import keer.matrimony.models.data;
import keer.matrimony.models.education;
import keer.matrimony.models.familyDetails;
import keer.matrimony.models.personal_details;
import keer.matrimony.models.religious_details;

public class CONSTANTS {
    public static final String BASEURL = "https://matrimony.keersamaj.in/api/";
    public static final String BASEURLPROFILE = "https://jaikisankgn.org/matrimonial/public/profile/";
    public static final String PERSONALDETAILS = "update-personal-details/";
    public static final String EDUCATIONDETAILS = "update-edu-details/";
    public static final String PARTNERPREF = "partner-preference/";
    public static final String RELIGIOUS = "update-religious-details/";
    public static final String CONTACTINFO = "update-contact-details/";
    public static final String FAMILYDETAILS = "update-family-details/";
    public static final String CALLMESSAGE = "Are you sure want to call on this number ?<br>Click Confirm to Call ";
    public static  education EDUCATIONDETAILSEDIT ;
    public static  ContactDetails CONTACTDETAILS ;
    public static  religious_details RELIGIOUSDETAIL;
    public static  personal_details PERSONALDETAIL ;
    public static List<data> SEARCHRESULT ;
    public static List<data> DATA ;
    public static data DATAs ;
    public static String mediaType = "application/json; charset=utf-8";
    public static familyDetails family;
}
