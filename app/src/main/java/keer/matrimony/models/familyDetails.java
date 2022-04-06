package keer.matrimony.models;

public class familyDetails {
    /*"id": 2,
            "user_id": 10,
            "father_name": "2s",
            "father_occupation": "d",
            "mother_name": "d",
            "mother_occupation": "d",
            "no_married_bro": "5",
            "no_unmarried_bro": "4",
            "no_married_sis": "4",
            "no_unmarried_sis": "4",
            "maternal_name": "s",
            "maternal_gotra": "s",
            "house": "4",
            "car": "4",*/
    String father_name;
    String father_occupation;
    String mother_name;
    String no_married_bro;
    String mother_occupation;
    String no_unmarried_bro;
    String no_married_sis;
    String house;
    String car;
    String maternal_name;

    public familyDetails(String father_name, String father_occupation, String mother_name, String no_married_bro, String mother_occupation, String no_unmarried_bro, String no_married_sis, String house, String car, String maternal_name, String maternal_gotra) {
        this.father_name = father_name;
        this.father_occupation = father_occupation;
        this.mother_name = mother_name;
        this.no_married_bro = no_married_bro;
        this.mother_occupation = mother_occupation;
        this.no_unmarried_bro = no_unmarried_bro;
        this.no_married_sis = no_married_sis;
        this.house = house;
        this.car = car;
        this.maternal_name = maternal_name;
        this.maternal_gotra = maternal_gotra;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getFather_occupation() {
        return father_occupation;
    }

    public void setFather_occupation(String father_occupation) {
        this.father_occupation = father_occupation;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getNo_married_bro() {
        return no_married_bro;
    }

    public void setNo_married_bro(String no_married_bro) {
        this.no_married_bro = no_married_bro;
    }

    public String getMother_occupation() {
        return mother_occupation;
    }

    public void setMother_occupation(String mother_occupation) {
        this.mother_occupation = mother_occupation;
    }

    public String getNo_unmarried_bro() {
        return no_unmarried_bro;
    }

    public void setNo_unmarried_bro(String no_unmarried_bro) {
        this.no_unmarried_bro = no_unmarried_bro;
    }

    public String getNo_married_sis() {
        return no_married_sis;
    }

    public void setNo_married_sis(String no_married_sis) {
        this.no_married_sis = no_married_sis;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getMaternal_name() {
        return maternal_name;
    }

    public void setMaternal_name(String maternal_name) {
        this.maternal_name = maternal_name;
    }

    public String getMaternal_gotra() {
        return maternal_gotra;
    }

    public void setMaternal_gotra(String maternal_gotra) {
        this.maternal_gotra = maternal_gotra;
    }

    String maternal_gotra;
}
