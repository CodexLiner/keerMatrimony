package keer.matrimony.models;

public class ContactDetails {
//     "fath_contact": "1234567890",
//             "whatsapp_no": "1234567890",
//             "present_addr": "na",
//             "permanent_addr": "na",
    String fath_contact , whatsapp_no , present_addr , permanent_addr ;

    public ContactDetails(String fath_contact, String whatsapp_no, String present_addr, String permanent_addr) {
        this.fath_contact = fath_contact;
        this.whatsapp_no = whatsapp_no;
        this.present_addr = present_addr;
        this.permanent_addr = permanent_addr;
    }

    public String getFath_contact() {
        return fath_contact;
    }

    public void setFath_contact(String fath_contact) {
        this.fath_contact = fath_contact;
    }

    public String getWhatsapp_no() {
        return whatsapp_no;
    }

    public void setWhatsapp_no(String whatsapp_no) {
        this.whatsapp_no = whatsapp_no;
    }

    public String getPresent_addr() {
        return present_addr;
    }

    public void setPresent_addr(String present_addr) {
        this.present_addr = present_addr;
    }

    public String getPermanent_addr() {
        return permanent_addr;
    }

    public void setPermanent_addr(String permanent_addr) {
        this.permanent_addr = permanent_addr;
    }
}
