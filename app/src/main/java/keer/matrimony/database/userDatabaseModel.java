package keer.matrimony.database;

public class userDatabaseModel {
    public static final String TABLE_NAME = "USER";

    public static final String ID = "id";
    public static final String P_ID = "pid";
    public static final String NAME = "name";
    public static final String LAST = "last_name";
    public static final String EMAIL = "email";
    public static final String COUNTRYCODE = "isd";
    public static final String MOBILE = "mobile";
    public static final String DOB = "dob";
    public static final String SUBCAST = "cast";
    public static final String COUNTRY = "country";
    public static final String STATE = "state";
    public static final String GENDER = "gen";
    public static final String PROFILE = "profile";
    public static final String PARTNER = "partner";
    public static final String STATUS = "status";
    public static final String CITY = "city";
    public static final String
            CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + ID + " INTEGER,"
                    + P_ID + " INTEGER,"
                    + NAME + " TEXT,"
                    + LAST + " TEXT,"
                    + EMAIL + " TEXT,"
                    + COUNTRYCODE + " TEXT DEFAULT "+"'+91',"
                    + MOBILE + " TEXT DEFAULT "+"'1234567890',"
                    + DOB + " TEXT,"
                    + SUBCAST + " TEXT DEFAULT "+"'KEER',"
                    + COUNTRY + " TEXT DEFAULT "+"'india',"
                    + STATE + " TEXT,"
                    + GENDER + " TEXT,"
                    + PROFILE + " TEXT,"
                    + PARTNER + " TEXT,"
                    + CITY + " TEXT,"
                    + STATUS + " TEXT"
                    + ")";

    private int id = 0;
    String  first_name;
    String last_name;
    String email;
    String country_code;
    String mobile;
    String dob;
    String subcaste;
    String country;
    String state;
    String city;
    String gender;

    public userDatabaseModel() {
    }

    String profile;
    String partner_preference;
    String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSubcaste() {
        return subcaste;
    }

    public void setSubcaste(String subcaste) {
        this.subcaste = subcaste;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPartner_preference() {
        return partner_preference;
    }

    public void setPartner_preference(String partner_preference) {
        this.partner_preference = partner_preference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public userDatabaseModel(int id, String first_name, String last_name, String email, String country_code, String mobile, String dob, String subcaste, String country, String state, String city, String gender, String profile, String partner_preference, String status) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.country_code = country_code;
        this.mobile = mobile;
        this.dob = dob;
        this.subcaste = subcaste;
        this.country = country;
        this.state = state;
        this.city = city;
        this.gender = gender;
        this.profile = profile;
        this.partner_preference = partner_preference;
        this.status = status;
    }

    @Override
    public String toString() {
        return "userDatabaseModel{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", country_code='" + country_code + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob='" + dob + '\'' +
                ", subcaste='" + subcaste + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", profile='" + profile + '\'' +
                ", partner_preference='" + partner_preference + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static String DbName = "KEER_USER";

}
