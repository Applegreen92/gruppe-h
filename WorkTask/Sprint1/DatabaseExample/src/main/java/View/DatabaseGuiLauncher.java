package View;

import Model.Student;
import Model.Teacher;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseGuiLauncher extends Application {

    private String db_url = "jdbc:mysql://localhost/studentdatabase";
    private String user_name = "root";
    private String password = "";

    public static void CreateTable() throws SQLException {
        String db_url = "jdbc:mysql://localhost/studentdatabase";
        String user_name = "root";
        String password = "";

        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            String queryStudent = "CREATE TABLE IF NOT EXISTS STUDENT(" +
                    "STUDENTID INT PRIMARY KEY AUTO_INCREMENT,"+
                    "NAMR VARCHAR(255)," +
                    "STUDIENGANG VARCHAR(255)," +
                    "MATRIKELNUMMER VARCHAR(255)" +
                    ")";

            Statement stmt = con.createStatement();
            stmt.execute(queryStudent);
            //stmt.execute(queryTeacher);
            stmt.close();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createInitScene(stage));
        stage.setScene(scene);
        stage.show();
    }


    private List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();

        //TODO get DB-Connection
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //TODO create and execute SQL-Query to select all students from table "student"
            String query = "SELECT * FROM STUDENT";
            Statement getData = con.prepareStatement(query);
            ResultSet result = getData.executeQuery(query);
            while(result.next()){

                int studentID = result.getInt("STUDENTID");
                String studentIDCast = Integer.toString(studentID);
                String name= result.getString("NAMR");
                String matrNumber = result.getString("MATRIKELNUMMER");
                String degree = result.getString("STUDIENGANG");
                Student newStudent = new Student(studentIDCast,name,matrNumber,degree);
                //students.addAll((Collection<? extends Student>) result);
                students.add(newStudent);
                //students.add((Student) result);
            }
        }catch (SQLException sqe) {
            System.out.println("SQLException: getPreviousSelectedAnswer return -> " + sqe.getMessage());
            sqe.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException: setPreviousSelectedStarterInfo return -> " + npe.getMessage());
            npe.printStackTrace();
        }
        //TODO return them as list
        return students;
    }

    private void insertStudent(Student s){
        //TODO get DB-Connection
        try(Connection con = DriverManager.getConnection(db_url,user_name,password)){
            //TODO create and execute SQL-Statement to insert Student into DB
            String query = "INSERT INTO student(NAMR,STUDIENGANG,MATRIKELNUMMER) VALUES" +
                    ""+ "('" +s.getName()+"','"+s.getDegree()+"','"+s.getMatrNumber()+"')";
            Statement Insert = con.createStatement();
            Insert.execute(query);
            Insert.close();
        }catch (SQLException sqe) {
            System.out.println("SQLException: getPreviousSelectedAnswer return -> " + sqe.getMessage());
            sqe.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException: setPreviousSelectedStarterInfo return -> " + npe.getMessage());
            npe.printStackTrace();
        }

    }

    private List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();

        //TODO get DB-Connection
        try(JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(db_url,user_name,password)){
            TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
            Dao<Teacher, Integer> TeacherDao = DaoManager.createDao(connectionSource, Teacher.class);
        //TODO create DAO (Data Access Object) to read all teachers from table "Teacher"
            List<Teacher> TeacherList = TeacherDao.queryForAll();
            for(Teacher teacher : TeacherList){
                teachers.add(teacher);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO return list of all teachers
        return  teachers;
    }

    private void insertTeacher(Teacher t) throws SQLException {
        //TODO get DB-Connection
        try(JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(db_url,user_name,password)){
            TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
            Dao<Teacher, Integer> TeacherDao = DaoManager.createDao(connectionSource, Teacher.class);
            //TODO create DAO (Data Access Object) to read all teachers from table "teacher"
            TeacherDao.create(new Teacher(t.getName(),t.getMail(),t.getOffice()));
            //TODO return list of all teachers
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private VBox createInitScene(Stage stage) {
        final VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        TableView table = new TableView();
        table.setEditable(false);

        final HBox insertArea = new HBox();
        insertArea.setAlignment(Pos.CENTER);
        TextField x = new TextField();
        TextField y = new TextField();
        TextField z = new TextField();
        Button insertBttn = new Button("insert");
        insertArea.getChildren().addAll(x,y,z,insertBttn);

        Button b1 = new Button("Students");
        b1.setOnAction(actionEvent -> {
            table.getItems().clear();

            TableColumn a = new TableColumn("studentId");
            a.setCellValueFactory(new PropertyValueFactory<>("studentId"));

            TableColumn b = new TableColumn("name");
            b.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn c = new TableColumn("matrNumber");
            c.setCellValueFactory(new PropertyValueFactory<>("matrNumber"));

            TableColumn d = new TableColumn("degree");
            d.setCellValueFactory(new PropertyValueFactory<>("degree"));


            table.getColumns().clear();
            table.getColumns().addAll(a,b,c,d);

            try {
                table.getItems().addAll(getAllStudents());
            } catch (SQLException e) {
                e.printStackTrace();
            }


            x.setPromptText("name");
            y.setPromptText("matrNumber");
            z.setPromptText("degree");


            insertBttn.setOnAction(actionEvent1 -> {
                if (x.getText() != "" && y.getText() != "" && z.getText() != ""){
                    Student s = new Student(x.getText(), y.getText(),z.getText());
                    insertStudent(s);
                    x.clear();
                    y.clear();
                    z.clear();
                    table.getItems().clear();
                    try {
                        table.getItems().addAll(getAllStudents());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        Button b2 = new Button("Teachers");
        b2.setOnAction(actionEvent -> {
            table.getItems().clear();

            TableColumn a = new TableColumn("teacherId");
            a.setCellValueFactory(new PropertyValueFactory<>("teacherId"));

            TableColumn b = new TableColumn("name");
            b.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn c = new TableColumn("mail");
            c.setCellValueFactory(new PropertyValueFactory<>("mail"));

            TableColumn d = new TableColumn("office");
            d.setCellValueFactory(new PropertyValueFactory<>("office"));

            x.setPromptText("name");
            y.setPromptText("mail");
            z.setPromptText("office");


            insertBttn.setOnAction(actionEvent1 -> {
                if (x.getText() != "" && y.getText() != "" && z.getText() != ""){
                    Teacher t = new Teacher(x.getText(), y.getText(),z.getText());
                    try {
                        insertTeacher(t);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    x.clear();
                    y.clear();
                    z.clear();
                    table.getItems().clear();
                    table.getItems().addAll(getAllTeachers());

                }
            });

            table.getColumns().clear();
            table.getColumns().addAll(a,b,c,d);

            table.getItems().addAll(getAllTeachers());
        });

        final HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().setAll(b1,b2);

        vbox.getChildren().setAll(buttons, table, insertArea);

        return vbox;
    }

}
