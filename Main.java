import com.sun.scenario.effect.impl.sw.java.JSWBlend_BLUEPeer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Filip on 09-03-2016.
 */
public class Main extends Application{
    public static void main(String[] args){
        launch(args);
    }

    Stage window;
    GridPane grid;
    Scene scene;
    TextField text1, text2, text3, text4,text5, text6;
    ListView list1, list2;
    Label l1,l2,l3,l4,l5,l6;
    MenuBar menu;
    Menu File, Edit, Help;
    BorderPane pane;
    Button b1,b2,b3,b4;
    @Override
    public void start(Stage primary) throws Exception {
        window = primary;
        window.setTitle("NameBook");

        pane = new BorderPane();

        menu = new MenuBar();
        File = new Menu("File");
        Edit = new Menu("Edit");
        Help = new Menu("Help");

        MenuItem newPerson = new MenuItem("Add New Contact");
        MenuItem viewList = new MenuItem("View Contacts");
        MenuItem exitProgram = new MenuItem("Exit");

        MenuItem getHelp = new MenuItem("Get Help");

        newPerson.setOnAction(e -> {
            try {
                newPerson();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        viewList.setOnAction(e -> {
            try {
                view();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        exitProgram.setOnAction(e-> System.exit(0));

        File.getItems().addAll(newPerson, viewList, exitProgram);

        MenuItem deletePerson = new MenuItem("Delete Contact");
        MenuItem changePerson = new MenuItem("Edit Contact");

        deletePerson.setOnAction(e -> {
            try {
                deletePerson();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        changePerson.setOnAction(e -> {
            try {
                change();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        getHelp.setOnAction(e-> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("Created by Filip Malek, All rights reserved\nIf you have any question write me on filamalek@hotmail.com");
            alert.showAndWait();
        });

        Edit.getItems().addAll(deletePerson, changePerson);
        Help.getItems().add(getHelp);

        menu.getMenus().addAll(File, Edit, Help);


        l1 = new Label();
        l1.setText("Build 1.0 Filip Malek");
        l1.setMaxSize(500, 10);

        try {
            sideView();
        } catch (Exception e) {
            e.printStackTrace();
        }

        l1.setAlignment(Pos.CENTER);

        pane.getStylesheets().add("style.css");
        pane.setTop(menu);
        pane.setCenter(grid);
        pane.setBottom(l1);
        pane.setId("pane");

        scene = new Scene(pane, 500,400);
        window.setScene(scene);
        window.show();

    }
    Alert alert;
    private void newPerson()throws Exception{
        sideView();


        grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(10);

        ContactList con = new ContactList();

        l1 = new Label("First Name:");
        l2 = new Label("Second Name:");
        l3 = new Label("E-mail:");
        l4 = new Label("Tel. Number:");
        l5 = new Label("Date Of Birth:");
        l6 = new Label("Home Address:");

        text1 = new TextField();
        text2 = new TextField();
        text3 = new TextField();
        text4 = new TextField();
        text5 = new TextField();
        text6 = new TextField();

        b1 = new Button("SAVE");

        text1.setPromptText("Enter First Name");
        text2.setPromptText("Enter Second Name");
        text3.setPromptText("Enter E-mail Address");
        text4.setPromptText("Enter Tel. Number");
        text5.setPromptText("Enter Date Of Birth");
        text6.setPromptText("Enter Home Address");

        grid.setConstraints(l1, 0, 0);
        grid.setConstraints(text1, 1, 0);
        grid.setConstraints(l2, 0, 1);
        grid.setConstraints(text2, 1, 1);
        grid.setConstraints(l3, 0, 2);
        grid.setConstraints(text3, 1, 2);
        grid.setConstraints(l4, 0, 3);
        grid.setConstraints(text4, 1, 3);
        grid.setConstraints(l5, 0,4);
        grid.setConstraints(text5, 1, 4);
        grid.setConstraints(l6, 0, 5);
        grid.setConstraints(text6, 1,5);
        grid.setConstraints(b1, 1, 6);

        grid.getChildren().addAll(text1, text2, text3, text4,text5, text6, l1,l2,l3,l4,l5,l6, b1);

        grid.setPadding(new Insets(50,10,10,20));

        pane.setCenter(grid);

        alert = new Alert(Alert.AlertType.INFORMATION);

        b1.setOnAction(e-> {
            fname = text1.getText();
            lname = text2.getText();
            mail = text3.getText();
            String num = text4.getText();
            String date = text5.getText();
            String home = text6.getText();


            if(fname.equals("") || lname.equals("") || mail.equals("") || num.equals("") || date.equals("") || home.equals("")){
                alert.setHeaderText("No Input Detected");
                alert.setContentText("You havent entered the data into the required fields!");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else {
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new FileWriter("MyContacts.txt",true));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                out.print(fname + " ");
                out.print(lname + " ");
                out.print(mail + " ");
                out.print(num+" ");
                out.print(date+" ");
                out.println(home);
                out.close();

                Contact c = new Contact(fname, lname,mail,num,date, home);
                con.add(c);

                text1.clear();
                text2.clear();
                text3.clear();
                text4.clear();
                text5.clear();
                text6.clear();

                alert.setTitle("Update information");
                alert.setContentText("Contact added to the list");
                alert.setHeaderText(null);
                alert.showAndWait();
                try {
                    sideView();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    String fname,lname,mail,number;

    private void view()throws Exception{
        pane.setRight(null);
        list1 = new ListView<String>();
        ContactList contact = new ContactList();
        StackPane stack = new StackPane();
        pane.setCenter(stack);

        Scanner read = new Scanner(new File("MyContacts.txt"));

        while(read.hasNextLine()){
            String line = read.nextLine();
            Scanner token = new Scanner(line);
            while (token.hasNext()){
                fname = token.next();
                lname = token.next();
                mail = token.next();
                number = token.next();
                date = token.next();
                home = token.next();
                Contact c = new Contact(fname,lname,mail,number,date,home);
                contact.add(c);
            }
        }

        for(int i = 0; i < contact.size(); i++){
            list1.getItems().add(contact.printView(i));
        }

        stack.getChildren().add(list1);

        pane.setCenter(stack);
    }
    String date,home;
    private void sideView()throws Exception{

        list2 = new ListView<String>();
        ContactList contact = new ContactList();
        StackPane stack = new StackPane();

        Scanner read = new Scanner(new File("MyContacts.txt"));

        while(read.hasNextLine()){
            String line = read.nextLine();
            Scanner token = new Scanner(line);
            while (token.hasNext()){
                fname = token.next();
                lname = token.next();
                mail = token.next();
                number = token.next();
                date = token.next();
                home = token.next();
                Contact c = new Contact(fname,lname,mail,number,date, home);
                contact.add(c);
            }
        }

        for(int i = 0; i < contact.size(); i++){
            list2.getItems().add(contact.sideView(i));
        }
        stack.setMaxSize(145,400);
        stack.getChildren().add(list2);
        list2.setStyle("-fx-background-color: lightslategray");
        stack.setStyle("-fx-background-color: lightslategray");
        pane.setRight(stack);
    }

    private void deletePerson()throws Exception{
        Scanner read = new Scanner(new File("MyContacts.txt"));
        ContactList contact = new ContactList();
        while(read.hasNextLine()){
            String line = read.nextLine();
            Scanner token = new Scanner(line);
            while (token.hasNext()){
                fname = token.next();
                lname = token.next();
                mail = token.next();
                number = token.next();
                date = token.next();
                home = token.next();
                Contact c = new Contact(fname,lname,mail,number,date,home);
                contact.add(c);
            }
        }

        VBox boxer = new VBox();
        boxer.setPadding(new Insets(5,5,5,5));
        boxer.setSpacing(5);
        b3 = new Button("Delete");
        b4 = new Button("Abort");
        TextField text = new TextField();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deleting a player");
        alert.setHeaderText("How to delete a player");
        alert.setContentText("Pick a number next to the player and type it into the field" +
                "\nOr simply select it from the list and click delete");
        alert.showAndWait();

        MsgBox box = new MsgBox();

        boxer.getChildren().addAll(b3,b4);
        view();

        list1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        pane.setRight(boxer);

        /*b3.setOnAction(event -> {
            String pick = text.getText();
            int a = Integer.parseInt(pick);
            contact.delete(a-1);
            try {
                contact.refresh();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            box.display("Action successful!", "Player has been deleted!");
            try {
                view();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });*/
        b4.setOnAction(event -> {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Action aborted");
            alert1.setHeaderText(null);
            alert1.setContentText("No contact have been deleted!");
            alert1.showAndWait();
            pane.setCenter(null);
            pane.setRight(null);
            try {
                sideView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        b3.setOnAction(event -> {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Are You Sure?");
            alert1.setHeaderText("Are you sure you want to delete a contact?");
            alert1.setContentText("Press OK to delete, press cancel to cancel");

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                String items = btnClick();
                Scanner line = new Scanner(items);
                while(line.hasNextLine()){
                    String oneLine = line.nextLine();
                    Scanner token = new Scanner(oneLine);
                    while(token.hasNext()){
                        int lineNum = token.nextInt();// for a number
                        String name1 = token.next(); // for a "Contact"
                        String name2 = token.next(); // for a First name
                        String name3 = token.next(); // for a Last name
                        String name4 = token.next(); // for a Mail address
                        String name5 = token.next(); // for a tel number
                        String name6 = token.next(); // for a date
                        String name7 = token.next(); // for a home address

                        contact.delete(lineNum-1);
                        try {
                            contact.refresh();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                box.display("Action successful!", "Contact/s have been deleted!");
                try {
                    view();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Action aborted");
                alert2.setHeaderText(null);
                alert2.setContentText("No contact have been deleted!");
                alert2.showAndWait();
                pane.setCenter(null);
                pane.setRight(null);
                try {
                    sideView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void change()throws Exception{
        Scanner read = new Scanner(new File("MyContacts.txt"));
        ContactList contact = new ContactList();
        ArrayList<Contact> array = new ArrayList<Contact>();

        while(read.hasNextLine()){
            String line = read.nextLine();
            Scanner token = new Scanner(line);
            while (token.hasNext()){
                fname = token.next();
                lname = token.next();
                mail = token.next();
                number = token.next();
                date = token.next();
                home = token.next();
                Contact c = new Contact(fname,lname,mail,number,date,home);
                contact.add(c);
                array.add(c);
            }
        }
        VBox vBox = new VBox();
        view();
        text1 = new TextField();
        text1.setMaxSize(100,10);

        text1.setPromptText("Enter Contact");

        b1 = new Button("Open");

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(4,4,4,4));
        vBox.getChildren().addAll(text1, b1);

        pane.setRight(vBox);

        b1.setOnAction(e-> {
            String pick = text1.getText();
            if(pick.equals("")){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Action aborted");
                alert1.setHeaderText(null);
                alert1.setContentText("No contacts have been selected!");
                alert1.showAndWait();
            }
            else {
                int num = Integer.parseInt(pick);
                fname = array.get(num - 1).getFname();
                lname = array.get(num - 1).getLname();
                mail = array.get(num - 1).getMail();
                number = array.get(num - 1).getNumber();
                date = array.get(num -1).getDate();
                home = array.get(num-1).getHomeAdd();

                GridPane grid = new GridPane();
                grid.setHgap(8);
                grid.setVgap(10);
                grid.setPadding(new Insets(5,5,5,5));

                l1 = new Label("First Name: ");
                l2 = new Label("Last Name: ");
                l3 = new Label("E-mail Address: ");
                l4 = new Label("Telephone Number: ");
                l5 = new Label("Date Of Birth: ");
                l6 = new Label("Home Address: ");

                text1 = new TextField(fname);
                text2 = new TextField(lname);
                text3 = new TextField(mail);
                text4 = new TextField(number);
                text5 = new TextField(date);
                text6 = new TextField(home);

                b2 = new Button("Save");
                b3 = new Button("Abort");

                l1.setPadding(new Insets(0,0,0,30));
                l2.setPadding(new Insets(0,0,0,30));
                l3.setPadding(new Insets(0,0,0,30));
                l4.setPadding(new Insets(0,0,0,30));

                grid.setConstraints(l1, 0,0);
                grid.setConstraints(text1, 1, 0);
                grid.setConstraints(l2, 0, 1);
                grid.setConstraints(text2, 1, 1);
                grid.setConstraints(l3, 0, 2);
                grid.setConstraints(text3, 1, 2);
                grid.setConstraints(l4, 0, 3);
                grid.setConstraints(text4, 1, 3);
                grid.setConstraints(l5, 0,4);
                grid.setConstraints(text5,1,4);
                grid.setConstraints(l6,0,5);
                grid.setConstraints(text6,1,5);

                HBox hBox = new HBox();
                hBox.setSpacing(8);
                hBox.setPadding(new Insets(5,5,5,5));
                hBox.getChildren().addAll(b2,b3);

                grid.setConstraints(hBox, 1,6);

                grid.getChildren().addAll(l1,l2,l3,l4,l5,l6, text1, text2, text3, text4,text5,text6, hBox);
                // adding functions to the buttons
                b3.setOnAction(a-> {     // b3 will abort the process of changing the contact data
                    pane.setCenter(null);
                    try {
                        sideView();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
                 // b2 will save the data into the text file and refresh the lists
                b2.setOnAction(b-> {
                    String firstName = text1.getText();
                    String lastName = text2.getText();
                    String mailAdd = text3.getText();
                    String telNum = text4.getText();
                    String dateBirth = text5.getText();
                    String homeAdd = text6.getText();

                    contact.refreshContact( (num-1),firstName, lastName, mailAdd, telNum,dateBirth,homeAdd);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Contact Data Changed");
                    alert.setHeaderText(null);
                    alert.setContentText("Contact has been changed!");
                    alert.showAndWait();
                    pane.setCenter(null);
                    try {
                        sideView();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                });

                pane.setRight(null);
                pane.setCenter(grid);
            }
        });


    }

    private String btnClick(){
        String message = "";
        ObservableList<String> movies;
        movies = list1.getSelectionModel().getSelectedItems();
        for(String m : movies){
            message += m + "\n";
        }
        return message;
    }
}
