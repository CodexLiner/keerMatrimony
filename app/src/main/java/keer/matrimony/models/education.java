package keer.matrimony.models;

public class education {
//    "edu": "ww",
//            "edu_detail": "Higher Secondary",
//            "occupation": "Self Employed",
//            "profession": "Oil extraction",
//            "ocu_detail": "2 to 5 lacs / 2 से 5 लाख",
//            "anu_income": "12 rupay",
    String edu , edu_detail , occupation , profession , ocu_detail , anu_income ;

    public education(String edu, String edu_detail, String occupation, String profession, String ocu_detail, String anu_income) {
        this.edu = edu;
        this.edu_detail = edu_detail;
        this.occupation = occupation;
        this.profession = profession;
        this.ocu_detail = ocu_detail;
        this.anu_income = anu_income;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getEdu_detail() {
        return edu_detail;
    }

    public void setEdu_detail(String edu_detail) {
        this.edu_detail = edu_detail;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getOcu_detail() {
        return ocu_detail;
    }

    public void setOcu_detail(String ocu_detail) {
        this.ocu_detail = ocu_detail;
    }

    public String getAnu_income() {
        return anu_income;
    }

    public void setAnu_income(String anu_income) {
        this.anu_income = anu_income;
    }
}
