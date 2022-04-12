package View;


import Model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.JsonReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class GuiLauncher extends Application {

    String url = "http://localhost:8080";

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createInitScene(stage));
        stage.setScene(scene);
        stage.show();
    }



    /**
     *
     * @param name   should be != empty string
     * @param surname should be != empty string
     * @return true if inserting was a success
     */
    private boolean insertPerson(String name, String surname) throws IOException {

        JSONObject myJason = new JSONObject();
        myJason.put("name",name);
        myJason.put("surname",surname);


        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost(url+"/persons/create");
        request.setHeader("accept","application/json");
        request.setHeader("content-type","application/json");
        request.setEntity(new StringEntity(myJason.toString()));

        CloseableHttpResponse response = client.execute(request);
        client.close();

        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() !=200) {
            response.close();
            return false;
        }else{
            response.close();
            return true;
        }



        //TODO create Json Object with structure {
        //                                          "name": "Peter",
        //                                          "surname": "Koch"
        //                                        }
        //


        //TODO create HTTP-Post Request that sends Json Object to the endpoint at "<url>/persons/create"
        //TODO then handle  HTTP-Response


    }

    /**
     *
     * @return a List that might be empty but should never be null
     */
    private List<Person> getAllPersons() throws IOException {
        List<Person> myList = new ArrayList<>();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url+"/persons/select");
        request.setHeader("accept","application/json");
        CloseableHttpResponse response = client.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        JSONArray myJsonArray = new JSONArray(result);
        for(int i = 0; i<myJsonArray.length();i++){

            JSONObject loopJson = myJsonArray.getJSONObject(i);
            String tempID = Integer.toString(loopJson.getInt("personId"));
            String tempName = loopJson.getString("name");
            String tempSurname = loopJson.getString("surname");

            myList.add(new Person(tempID, tempName, tempSurname));
        }
        client.close();
        response.close();
        return myList;





        //TODO create HTTP-Get Request that retrieves all persons from endpoint "<url>/persons/select"


    }

    /**
     *
     * @return a List that might be empty but should never be null
     */
    private List<Person> searchPersons(String searchInput) throws IOException {
        ArrayList<Person> myList = new ArrayList<>();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url+"/persons/select?search=<"+searchInput+">");
        request.setHeader("accept","application/json");
        CloseableHttpResponse response = client.execute(request);
        JSONArray myJsonArray = new JSONArray(response.getEntity().toString());

        for(int i = 0; i < myJsonArray.length(); i++ ){
            JSONObject jsonObject = new JSONObject(myJsonArray.getJSONObject(i));
            String tempId = Integer.toString(jsonObject.getInt("personId"));
            String tempName = jsonObject.getString("name");
            String tempSurname = jsonObject.getString("surname");
            myList.add(new Person(tempId,tempName,tempSurname));
        }
        //TODO create HTTP-Get Request that retrieves all persons from endpoint "<url>/persons/select?search=<searchInput>"

        return myList;
    }

    private VBox createInitScene(Stage stage) throws IOException {
        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);


        TableView table = new TableView();
        table.setEditable(false);

        TableColumn idColl = new TableColumn("PersonId");
        idColl.setCellValueFactory(new PropertyValueFactory<>("personId"));

        TableColumn nameColl = new TableColumn("Name");
        nameColl.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn surnameColl = new TableColumn("Surname");
        surnameColl.setCellValueFactory(new PropertyValueFactory<>("surname"));


        table.getColumns().addAll(idColl,nameColl,surnameColl);

        table.getItems().addAll(getAllPersons());

        //searchBar + searchButton
        final HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        TextField search_input = new TextField();
        Button searchBttn = new Button("search");
        searchBttn.setDisable(true);
        searchBttn.setOnAction(actionEvent -> {
            List<Person> p = null;
            try {
                p = searchPersons(search_input.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            table.getItems().clear();
            table.getItems().addAll(p);
        });

        search_input.textProperty().addListener((observable,oldValue, newValue) -> {
            if(oldValue.equals(""))searchBttn.setDisable(false);
            if(newValue.equals(""))searchBttn.setDisable(true);
        });

        hbox.getChildren().addAll(search_input, searchBttn);


        Button resetFilter = new Button("reset Filter");
        resetFilter.setOnAction(actionEvent -> {
            table.getItems().clear();
            List<Person> allPersons = null;
            try {
                allPersons = getAllPersons();
            } catch (IOException e) {
                e.printStackTrace();
            }
            table.getItems().addAll(allPersons);

        });

        //InsertPerson
        final HBox hbox1 = new HBox();
        hbox1.setAlignment(Pos.CENTER);
        TextField name = new TextField();
        name.setPromptText("name");
        TextField surname = new TextField();
        surname.setPromptText("surname");
        Button add_bttn = new Button("add");
        add_bttn.setOnAction(actionEvent -> {
            try {
                if(insertPerson(name.getText(), surname.getText())){
                    table.getItems().clear();
                    table.getItems().addAll(getAllPersons());
                    name.setText("");
                    surname.setText("");
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("something went wrong while trying to insert!");

                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        hbox1.getChildren().addAll(name, surname, add_bttn);

        vbox.getChildren().setAll(hbox, resetFilter, table, hbox1);

        return vbox;
    }




}
