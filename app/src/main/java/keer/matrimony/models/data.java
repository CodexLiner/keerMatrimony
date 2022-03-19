package keer.matrimony.models;

public class data {
//            "id": 2,
//                    "first_name": "sa",
//                    "last_name": "js",
//                    "email": "aa@gmail.com",
//                    "country_code": "91",
//                    "mobile": "1234567890",
//                    "dob": "1997-06-01",
//                    "subcaste": "Gurjar",
//                    "country": "india",
//                    "state": "Madhya Prades",
//                    "city": "Indore",
//                    "gender": "male",
//                    "profile": "1647001767.jpg",
//                    "partner_preference": "m m m m m",
//                    "status": 1,
//                    "created_at": "2022-03-11T12:29:27.000000Z",
//                    "updated_at": "2022-03-11T18:02:45.000000Z"
    String id , first_name , last_name ,email , country_code,
        mobile , dob , subcaste , country , state ,
        city , gender , profile ,partner_preference ,
        status , created_at ,  updated_at , height , weight ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public data(String id, String first_name, String last_name, String email, String country_code, String mobile, String dob, String subcaste, String country, String state, String city, String gender, String profile, String partner_preference, String status, String created_at, String updated_at, String height, String weight) {
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
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "data{" +
                "id='" + id + '\'' +
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
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
