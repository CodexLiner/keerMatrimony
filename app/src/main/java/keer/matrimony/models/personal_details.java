package keer.matrimony.models;

public class personal_details {
//             "height": "5.0 feet",
//              "maretial_status": "Married",
//              "complexion": "UnMarried",
//              "weight": "1",
//              "diet": "UnMarried",
//              "disablity": "Handicap",
//              "blood_group": "O RhD",
    String height , maretial_status , complexion , weight, disablity , blood_group;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMaretial_status() {
        return maretial_status;
    }

    public void setMaretial_status(String maretial_status) {
        this.maretial_status = maretial_status;
    }

    public String getComplexion() {
        return complexion;
    }

    public void setComplexion(String complexion) {
        this.complexion = complexion;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDisablity() {
        return disablity;
    }

    public void setDisablity(String disablity) {
        this.disablity = disablity;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public personal_details(String height, String maretial_status, String complexion, String weight, String disablity, String blood_group) {
        this.height = height;
        this.maretial_status = maretial_status;
        this.complexion = complexion;
        this.weight = weight;
        this.disablity = disablity;
        this.blood_group = blood_group;
    }

    @Override
    public String toString() {
        return "personal_details{" +
                "height='" + height + '\'' +
                ", maretial_status='" + maretial_status + '\'' +
                ", complexion='" + complexion + '\'' +
                ", weight='" + weight + '\'' +
                ", disablity='" + disablity + '\'' +
                ", blood_group='" + blood_group + '\'' +
                '}';
    }
}
