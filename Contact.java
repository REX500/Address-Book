/**
 * Created by Filip on 09-03-2016.
 */
public class Contact {

    private String fname;
    private String lname;
    private String mail;
    private String number;
    private String date; // date of birth
    private String homeAdd; // home address

    public Contact(String fname, String lname, String mail, String number,String date, String homeAdd){
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.number = number;
        this.date = date;
        this.homeAdd = homeAdd;
    }

     public String getDate(){
     return date;
     }

     public void setDate(String date){
      this.date = date;
     }

     public String getHomeAdd(){
       return homeAdd;
      }

      public void setHomeAdd(String homeAdd){
       this.homeAdd = homeAdd;
      }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
