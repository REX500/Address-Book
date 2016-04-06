import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Filip on 09-03-2016.
 */
public class ContactList {
    ArrayList<Contact> list = new ArrayList<Contact>();

    public ContactList(){

    }

    public void add(Contact c){
        list.add(c);
    }

    public void delete(int pick){
        list.remove(pick);
    }

    public void refresh()throws Exception{
        PrintWriter out = new PrintWriter(new FileWriter("MyContacts.txt",false));
        out.println();
        for(int i = 0; i < list.size() ; i++){
            out.print(list.get(i).getFname()+" "+list.get(i).getLname()+" "+list.get(i).getMail()+" "+list.get(i).getNumber()+" "+list.get(i).getDate()+" "+list.get(i).getHomeAdd());
            out.println();
        }
        out.close();
    }
    public String printView(int a){
        for(int i = a; i < list.size(); i++){
            String player = (i+1)+" Contact: "+list.get(i).getFname()+" "+list.get(i).getLname()+" "+list.get(i).getMail()+" "+list.get(i).getNumber()+" "+list.get(i).getDate()+" "+list.get(i).getHomeAdd();
            return player;
        }
        return "";
    }

    public int size(){
        return list.size();
    }

    public String sideView(int a){
        for(int i = a; i < list.size(); i++){
            String player = (i+1)+". Contact: "+list.get(i).getFname().charAt(0)+". "+list.get(i).getLname();
            return player;
        }
        return "";
    }

    public void refreshContact(int pick, String fname, String lname, String mail, String num,String date, String homeAdd){
        list.get(pick).setFname(fname);
        list.get(pick).setLname(lname);
        list.get(pick).setMail(mail);
        list.get(pick).setNumber(num);
        list.get(pick).setDate(date);
        list.get(pick).setHomeAdd(homeAdd);
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
