package keer.matrimony.models;

public class religious_details {
//      "gotra": "gsgs",
//              "mother_tongue": "English / अंग्रेजी",
//              "birth_time": "14 Mar 22",
//              "birth_place": "88",
//              "zodiac": "Virgo / कन्या",
//              "manglic": "Non Mangli",
//              "nakshtra": "Anuradha",
    String gotra,mother_tongue , birth_time , birth_place , zodiac , manglic , nakshtra ;

    public religious_details(String gotra, String mother_tongue, String birth_time, String birth_place, String zodiac, String manglic, String nakshtra) {
        this.gotra = gotra;
        this.mother_tongue = mother_tongue;
        this.birth_time = birth_time;
        this.birth_place = birth_place;
        this.zodiac = zodiac;
        this.manglic = manglic;
        this.nakshtra = nakshtra;
    }

    public String getGotra() {
        return gotra;
    }

    public void setGotra(String gotra) {
        this.gotra = gotra;
    }

    public String getMother_tongue() {
        return mother_tongue;
    }

    public void setMother_tongue(String mother_tongue) {
        this.mother_tongue = mother_tongue;
    }

    public String getBirth_time() {
        return birth_time;
    }

    public void setBirth_time(String birth_time) {
        this.birth_time = birth_time;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getManglic() {
        return manglic;
    }

    public void setManglic(String manglic) {
        this.manglic = manglic;
    }

    public String getNakshtra() {
        return nakshtra;
    }

    public void setNakshtra(String nakshtra) {
        this.nakshtra = nakshtra;
    }

    @Override
    public String toString() {
        return "religious_details{" +
                "gotra='" + gotra + '\'' +
                ", mother_tongue='" + mother_tongue + '\'' +
                ", birth_time='" + birth_time + '\'' +
                ", birth_place='" + birth_place + '\'' +
                ", zodiac='" + zodiac + '\'' +
                ", manglic='" + manglic + '\'' +
                ", nakshtra='" + nakshtra + '\'' +
                '}';
    }
}
