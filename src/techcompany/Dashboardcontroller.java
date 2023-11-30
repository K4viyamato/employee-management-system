/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techcompany;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.collections.transformation.FilteredList;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javafx.scene.control.PasswordField;
import static techcompany.getData.getUserRole;

/**
 *
 * @author kavindu
 */
public class Dashboardcontroller implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField addEmployee_name;

    @FXML
    private Button logout;

    @FXML
    private TextField addEmployee_epfNo;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_desig;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_desig1;

    @FXML
    private ComboBox<?> addEmployee_desig;

    @FXML
    private ComboBox<?> addEmployee_gender;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_gender;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_gender1;

    @FXML
    private AnchorPane home_form;

    @FXML
    private TableView<employeeData> addEmployee_tableView;

    @FXML
    private TableView<employeeData> addEmployee_tableView1;

    @FXML
    private TextField addEmployee_employeeID;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_epfNo;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_epfNo1;

    @FXML
    private TableColumn<employeeData, Integer> addEmployee_col_employeeID;

    @FXML
    private TableColumn<employeeData, Integer> addEmployee_col_employeeID1;

    @FXML
    private AnchorPane addUser_form;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_phoneNum;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_phoneNum1;

    @FXML
    private ComboBox<?> addEmployee_depart;

    @FXML
    private Button addEmployee_btn;

    @FXML
    private TableView<departmentData> departmentTable_view;

    @FXML
    private TableView<designationData> designationTable_view;

    @FXML
    private TableColumn<designationData, String> des_col_designationName;

    @FXML
    private TextField designationName_textField;

    @FXML
    private TableColumn<departmentData, String> dep_col_departmentName;

    @FXML
    private TextField departmentName_textField;

    @FXML
    private TableColumn<employeeData, Double> addEmployee_col_salary;

    @FXML
    private TableColumn<employeeData, Double> addEmployee_col_salary1;

    @FXML
    private AnchorPane addEmployee_form;
 
    @FXML
    private Button addUser_btn;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_name;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_name1;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_depart;

    @FXML
    private TableColumn<employeeData, String> addEmployee_col_depart1;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TextField addEmployee_search1;

    @FXML
    private Button home_btn;

    @FXML
    private TextField addEmployee_salary;

    @FXML
    private AnchorPane depDesig_form;

    @FXML
    private Button addDepartment_btn;

    @FXML
    private TableColumn<adminData, String> user_col_username;

    @FXML
    private TableColumn<adminData, String> user_col_role;

    @FXML
    private TableView<adminData> addUser_tableView;

    @FXML
    private TextField addUserUsername;

    @FXML
    private PasswordField addUserPassword;


    @FXML
    private Label username;


    private double x = 0;
    private double y = 0;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    //TOTAL EMPLOYEES
    public void homeTotalEmployees() {

        String sql = "SELECT COUNT(id) FROM employee";

        connect = database.connectDb();

        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(ID)");
            }

            home_totalEmployees.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    //HANDLING DATA IN DEP TABLE AND DESIG TABLE
    
    private int getDesignationIDByName(String designationName) {
        String sql = "SELECT DesignationID FROM Designation WHERE DesignationName = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, designationName);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("DesignationID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if designation not found
    }

    private String getDesignationNameById(int designationID) {
        String designationName = null;
        try {
            Connection connection = database.connectDb();
            String query = "SELECT DesignationName FROM Designation WHERE DesignationID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, designationID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                designationName = resultSet.getString("DesignationName");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return designationName;
    }

    private int getDepartmentIDByName(String departmentName) {
        String sql = "SELECT DepartmentID FROM Department WHERE DepartmentName = ?";
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, departmentName);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("DepartmentID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if department not found
    }

    private String getDepartmentNameById(int departmentID) {
        String departmentName = null;
        try {
            Connection connection = database.connectDb();
            String query = "SELECT DepartmentName FROM Department WHERE DepartmentID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                departmentName = resultSet.getString("DepartmentName");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return departmentName;
    }
    
    
    
    //MANAGE EMPLOYEES

    public void addEmployeeAdd() {
        String sql = "INSERT INTO employee (EmployeeID, Name, Gender, DepartmentID, DesignationID, Salary, PhoneNumber, EPFNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        connect = database.connectDb();

        try {
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_name.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_depart.getSelectionModel().getSelectedItem() == null
                    || addEmployee_desig.getSelectionModel().getSelectedItem() == null
                    || addEmployee_salary.getText().isEmpty()
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_epfNo.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blanks");
                alert.showAndWait();

            } else {
                String departmentName = (String) addEmployee_depart.getSelectionModel().getSelectedItem();
                int departmentID = getDepartmentIDByName(departmentName);

                String designationName = (String) addEmployee_desig.getSelectionModel().getSelectedItem();
                int designationID = getDesignationIDByName(designationName);

                if (departmentID == -1 || designationID == -1) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Selected department not found");
                    alert.showAndWait();
                    return;
                } else {

                    String employeeIDText = addEmployee_employeeID.getText();

                    if (!employeeIDText.matches("\\d+")) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Employee ID should be a number");
                        alert.showAndWait();

                    } else {

                        String check = "SELECT EmployeeID FROM employee WHERE EmployeeID='" + addEmployee_employeeID.getText() + "'";
                        statement = connect.createStatement();
                        result = statement.executeQuery(check);

                        if (result.next()) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Employee ID :" + addEmployee_employeeID.getText() + " is already exist");
                            alert.showAndWait();
                        } else {
                            prepare = connect.prepareStatement(sql);
                            prepare.setString(1, addEmployee_employeeID.getText());
                            prepare.setString(2, addEmployee_name.getText());
                            prepare.setString(3, (String) addEmployee_gender.getSelectionModel().getSelectedItem());
                            prepare.setInt(4, departmentID); // Set department ID obtained from department name
                            prepare.setInt(5, designationID);
                            prepare.setString(6, addEmployee_salary.getText());
                            prepare.setString(7, addEmployee_phoneNum.getText());
                            prepare.setString(8, addEmployee_epfNo.getText());

                            prepare.executeUpdate();

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Successfully Added!");
                            alert.showAndWait();

                            addEmployeeShowListData();
                            addEmployeeReset();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeUpdate() {
        String sql = "UPDATE employee SET Name=?, Gender=?, DepartmentID=?, DesignationID=?, Salary=?, PhoneNumber=?, EPFNumber=? WHERE EmployeeID=?";

        connect = database.connectDb();

        try {
            if (addEmployee_employeeID.getText().isEmpty()
                    || addEmployee_name.getText().isEmpty()
                    || addEmployee_gender.getSelectionModel().getSelectedItem() == null
                    || addEmployee_depart.getSelectionModel().getSelectedItem() == null
                    || addEmployee_desig.getSelectionModel().getSelectedItem() == null
                    || addEmployee_salary.getText().isEmpty()
                    || addEmployee_phoneNum.getText().isEmpty()
                    || addEmployee_epfNo.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blanks");
                alert.showAndWait();

            } else {
                String departmentName = (String) addEmployee_depart.getSelectionModel().getSelectedItem();
                int departmentID = getDepartmentIDByName(departmentName);

                String designationName = (String) addEmployee_desig.getSelectionModel().getSelectedItem();
                int designationID = getDesignationIDByName(designationName);

                if (departmentID == -1 || designationID == -1) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Selected department or designation not found");
                    alert.showAndWait();
                    return;
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addEmployee_name.getText());
                    prepare.setString(2, (String) addEmployee_gender.getSelectionModel().getSelectedItem());
                    prepare.setInt(3, departmentID);
                    prepare.setInt(4, designationID);
                    prepare.setString(5, addEmployee_salary.getText());
                    prepare.setString(6, addEmployee_phoneNum.getText());
                    prepare.setString(7, addEmployee_epfNo.getText());
                    prepare.setString(8, addEmployee_employeeID.getText());

                    prepare.executeUpdate();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeDelete() {

        String sql = "DELETE FROM employee WHERE EmployeeID='" + addEmployee_employeeID.getText() + "'";

        connect = database.connectDb();

        try {

            if (addEmployee_employeeID.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a field!");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Delete Employee " + addEmployee_name.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addEmployeeShowListData();
                    addEmployeeReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEmployeeReset() {

        addEmployee_employeeID.setText("");
        addEmployee_name.setText("");
        addEmployee_gender.getSelectionModel().clearSelection();
        addEmployee_depart.getSelectionModel().clearSelection();
        addEmployee_desig.getSelectionModel().clearSelection();
        addEmployee_salary.setText("");
        addEmployee_phoneNum.setText("");
        addEmployee_epfNo.setText("");

    }

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> searchTask;

    public void addEmployeeSearch() {
        FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);

        addEmployee_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            if (searchTask != null && !searchTask.isDone()) {
                searchTask.cancel(true);
            }
            searchTask = scheduler.schedule(() -> Platform.runLater(() -> {
                filter.setPredicate(predicateEmployeeData -> {
                    if (newValue == null || newValue.isEmpty()) {

                        return true;
                    }

                    String searchKey = newValue.toLowerCase();

                    if (predicateEmployeeData.getEmployeeID().toString().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getName().toLowerCase().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getEpfNumber().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getSalary().toString().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getPhoneNumber().contains(searchKey)) {

                        return true;

                    } else {
                        // Search by department name using its ID
                        String departmentName = getDepartmentNameById(predicateEmployeeData.getDepartmentID());
                        if (departmentName != null && departmentName.toLowerCase().contains(searchKey)) {
                            return true;
                        }

                        // Search by designation name using its ID
                        String designationName = getDesignationNameById(predicateEmployeeData.getDesignationID());
                        return designationName != null && designationName.toLowerCase().contains(searchKey);
                    }

                });
            }), 1500, TimeUnit.MILLISECONDS);
        });

        SortedList<employeeData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addEmployee_tableView.comparatorProperty());
        addEmployee_tableView.setItems(sortList);
    }

    public void addEmployeeSearchsecond() {
        FilteredList<employeeData> filter = new FilteredList<>(addEmployeeList, e -> true);

        addEmployee_search1.textProperty().addListener((Observable, oldValue, newValue) -> {
            if (searchTask != null && !searchTask.isDone()) {
                searchTask.cancel(true);
            }
            searchTask = scheduler.schedule(() -> Platform.runLater(() -> {
                filter.setPredicate(predicateEmployeeData -> {
                    if (newValue == null || newValue.isEmpty()) {

                        return true;
                    }

                    String searchKey = newValue.toLowerCase();

                    if (predicateEmployeeData.getEmployeeID().toString().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getName().toLowerCase().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getEpfNumber().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getSalary().toString().contains(searchKey)) {

                        return true;

                    } else if (predicateEmployeeData.getPhoneNumber().contains(searchKey)) {

                        return true;

                    } else {
                        // Search by department name using its ID
                        String departmentName = getDepartmentNameById(predicateEmployeeData.getDepartmentID());
                        if (departmentName != null && departmentName.toLowerCase().contains(searchKey)) {
                            return true;
                        }

                        // Search by designation name using its ID
                        String designationName = getDesignationNameById(predicateEmployeeData.getDesignationID());
                        return designationName != null && designationName.toLowerCase().contains(searchKey);
                    }

                    
                });
            }), 500, TimeUnit.MILLISECONDS); 
        });

        SortedList<employeeData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addEmployee_tableView1.comparatorProperty());
        addEmployee_tableView1.setItems(sortList);
    }

    public ObservableList<employeeData> addEmployeeListData() {

        ObservableList<employeeData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            employeeData employeeDATA;

            while (result.next()) {
                employeeDATA = new employeeData(
                        result.getInt("employeeID"), result.getString("name"), result.getString("gender"), result.getInt("departmentID"), result.getInt("designationID"), result.getDouble("salary"), result.getString("phoneNumber"), result.getString("epfNumber"));

                listData.add(employeeDATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<employeeData> addEmployeeList;

    public void addEmployeeShowListData() {

        addEmployeeList = addEmployeeListData();

        addEmployee_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        addEmployee_col_employeeID1.setCellValueFactory(new PropertyValueFactory<>("employeeID"));

        addEmployee_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        addEmployee_col_name1.setCellValueFactory(new PropertyValueFactory<>("name"));

        addEmployee_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addEmployee_col_gender1.setCellValueFactory(new PropertyValueFactory<>("gender"));

        addEmployee_col_depart.setCellValueFactory(cellData -> {
            int departmentID = cellData.getValue().getDepartmentID();
            String departmentName = getDepartmentNameById(departmentID);
            return new SimpleStringProperty(departmentName);
        });
        addEmployee_col_depart1.setCellValueFactory(cellData -> {
            int departmentID = cellData.getValue().getDepartmentID();
            String departmentName = getDepartmentNameById(departmentID);
            return new SimpleStringProperty(departmentName);
        });

        addEmployee_col_desig.setCellValueFactory(cellData -> {
            int designationID = cellData.getValue().getDesignationID();
            String designationName = getDesignationNameById(designationID);
            return new SimpleStringProperty(designationName);
        });

        addEmployee_col_desig1.setCellValueFactory(cellData -> {
            int designationID = cellData.getValue().getDesignationID();
            String designationName = getDesignationNameById(designationID);
            return new SimpleStringProperty(designationName);
        });

        addEmployee_col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        addEmployee_col_salary1.setCellValueFactory(new PropertyValueFactory<>("salary"));

        addEmployee_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addEmployee_col_phoneNum1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        addEmployee_col_epfNo.setCellValueFactory(new PropertyValueFactory<>("epfNumber"));
        addEmployee_col_epfNo1.setCellValueFactory(new PropertyValueFactory<>("epfNumber"));

        addEmployee_tableView.setItems(addEmployeeList);
        addEmployee_tableView1.setItems(addEmployeeList);

    }

    
    
    //MANAGE DEPARTMENTS AND DESIGNATION
 
    
    // adding department table data to the list  
    private ObservableList<departmentData> departmentList = FXCollections.observableArrayList();

    public ObservableList<departmentData> getDepartmentListData() {
        String sql = "SELECT * FROM department";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                departmentData departmentDATA = new departmentData(
                        result.getInt("departmentID"),
                        result.getString("departmentName")
                );
                departmentList.add(departmentDATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return departmentList;
    }

    // adding department table data to the columns 
    public void showDepartmentListData() {
        departmentList.clear();
        departmentList = getDepartmentListData();

        dep_col_departmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        departmentTable_view.setItems(departmentList);

    }

    // adding designation table data to the list  
    private ObservableList<designationData> designationList = FXCollections.observableArrayList();

    public ObservableList<designationData> getDesignationListData() {
        String sql = "SELECT * FROM designation";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                designationData designationDATA = new designationData(
                        result.getInt("designationID"),
                        result.getString("designationName")
                );
                designationList.add(designationDATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return designationList;
    }

     // adding designation table data to the columns   
    public void showDesignationListData() {

        designationList.clear();
        designationList = getDesignationListData();

        des_col_designationName.setCellValueFactory(new PropertyValueFactory<>("designationName"));
        designationTable_view.setItems(designationList);

    }

    public void addDepandDesAdd() {
        String depsql = "INSERT INTO department (DepartmentName) VALUES (?)";
        String dessql = "INSERT INTO designation (DesignationName) VALUES (?)";

        connect = database.connectDb();

        String departmentText = departmentName_textField.getText();
        String designationText = designationName_textField.getText();

        try {
            if (!departmentText.isEmpty()) {
                String depCheck = "SELECT * FROM department WHERE departmentName ='" + departmentText + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(depCheck);

                if (result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Department: " + departmentText + " already exists");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(depsql);
                    prepare.setString(1, departmentText);
                    prepare.executeUpdate();

                    depandDesClear();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText(departmentText + " Successfully Added!");
                    alert.showAndWait();

                    showDepartmentListData();
                }
            } else if (!designationText.isEmpty()) {
                String desCheck = "SELECT * FROM designation WHERE designationName ='" + designationText + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(desCheck);

                if (result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Designation: " + designationText + " already exists");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(dessql);
                    prepare.setString(1, designationText);
                    prepare.executeUpdate();

                    depandDesClear();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText(designationText + " Successfully Added!");
                    alert.showAndWait();

                    showDesignationListData();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill at least one field!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DepandDesDelete() {
        String depsql = "DELETE FROM department WHERE departmentName = ?";
        String dessql = "DELETE FROM designation WHERE designationName = ?";

        connect = database.connectDb();

        String departmentText = departmentName_textField.getText();
        String designationText = designationName_textField.getText();

        try {
            if (!departmentText.isEmpty()) {
                String depCheck = "SELECT * FROM department WHERE departmentName = ?";
                prepare = connect.prepareStatement(depCheck);
                prepare.setString(1, departmentText);
                ResultSet result = prepare.executeQuery();

                if (!result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("There is no Department called " + departmentText);
                    alert.showAndWait();
                } else {
                    int departmentID = result.getInt("departmentID");

                    String checkEmployees = "SELECT * FROM employee WHERE departmentID = ?";
                    prepare = connect.prepareStatement(checkEmployees);
                    prepare.setInt(1, departmentID);
                    ResultSet employeeResult = prepare.executeQuery();

                    if (employeeResult.next()) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("There are associated employees with " + departmentText + " Please handle them first.");
                        alert.showAndWait();
                    } else {
                        prepare = connect.prepareStatement(depsql);
                        prepare.setString(1, departmentText);
                        prepare.executeUpdate();

                        depandDesClear();

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText(departmentText + " Successfully Deleted!");
                        alert.showAndWait();

                        showDepartmentListData();
                    }
                }
            } else if (!designationText.isEmpty()) {

                String desCheck = "SELECT * FROM designation WHERE designationName = ?";
                prepare = connect.prepareStatement(desCheck);
                prepare.setString(1, designationText);
                ResultSet result = prepare.executeQuery();

                if (!result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("There is no Designation called " + designationText);
                    alert.showAndWait();
                } else {
                    int departmentID = result.getInt("designationID");

                    String checkEmployees = "SELECT * FROM employee WHERE designationID = ?";
                    prepare = connect.prepareStatement(checkEmployees);
                    prepare.setInt(1, departmentID);
                    ResultSet employeeResult = prepare.executeQuery();

                    if (employeeResult.next()) {

                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("There are associated employees with " + designationText + ". Please handle them first.");
                        alert.showAndWait();
                    } else {
                        prepare = connect.prepareStatement(dessql);
                        prepare.setString(1, designationText);
                        prepare.executeUpdate();

                        depandDesClear();

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText(designationText + " Successfully Deleted!");
                        alert.showAndWait();

                        showDesignationListData();
                    }
                }
                // Similar check for the designation table...
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill at least one field!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void depandDesClear() {

        departmentName_textField.setText("");
        designationName_textField.setText("");

    }
    
 
    

    //MANAGING USERS

    //methanin thamai adminData file eken araganne get walin
    private ObservableList<adminData> adminList = FXCollections.observableArrayList();

    //methanin admin data ekata dagannawa table eken
    public ObservableList<adminData> getAdminListData() {
        String sql = "SELECT * FROM admin"; 

        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                adminData adminDATA = new adminData(

                        result.getString("username"),
                        result.getString("role")
                );
                adminList.add(adminDATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //methanin return kranawa data
        return adminList;
    }

    public void showAdminListData() {

        adminList.clear();
        adminList = getAdminListData();

        //table eke row tikata data tika danawa 
        //(meka system eke pennana table eka)
        user_col_username.setCellValueFactory(new PropertyValueFactory<>("adminUsername"));
        user_col_role.setCellValueFactory(new PropertyValueFactory<>("adminUserRole"));

        addUser_tableView.setItems(adminList);

    }

    public void createUser() {

        String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";

        connect = database.connectDb();

        String usernameText = addUserUsername.getText();
        String passwordText = addUserPassword.getText();

        try {
            if (!usernameText.isEmpty() || !passwordText.isEmpty()) {
                String check = "SELECT * FROM admin WHERE username ='" + usernameText + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User " + usernameText + " Is Already Exists");
                    alert.showAndWait();

                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, usernameText);
                    prepare.setString(2, passwordText);
                    prepare.executeUpdate();

                    userLogsClear();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText(usernameText + " Is Created Successfully");
                    alert.showAndWait();

                    showAdminListData();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill Input Fields!");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void userLogsClear() {

        addUserUsername.setText("");
        addUserPassword.setText("");

    }

    
    public void userDelete() {

        String sql = "DELETE FROM admin WHERE username = ? AND password = ?";

        connect = database.connectDb();

        String usernameText = addUserUsername.getText();
        String passwordText = addUserPassword.getText();

        try {
            if (!usernameText.isEmpty() || !passwordText.isEmpty()) {

                String check = "SELECT * FROM admin WHERE username = ? AND password = ?";

                String checkAdmin = "SELECT * FROM admin WHERE username = ? AND password = ? AND role = 'admin'";

                prepare = connect.prepareStatement(check);
                prepare.setString(1, usernameText);
                prepare.setString(2, passwordText);
                ResultSet result = prepare.executeQuery();

                prepare = connect.prepareStatement(checkAdmin);
                prepare.setString(1, usernameText);
                prepare.setString(2, passwordText);
                ResultSet resultAdmin = prepare.executeQuery();

                if (!result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("There is no " + usernameText + " in the database");
                    alert.showAndWait();

                } else if (resultAdmin.next()) {

                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("You can't Delete an Admin");
                    alert.showAndWait();

                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, usernameText);
                    prepare.setString(2, passwordText);
                    prepare.executeUpdate();

                    userLogsClear();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText(usernameText + " Deleted Successfully!");
                    alert.showAndWait();

                    showAdminListData();
                }

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill Input Fields!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
    
    //MANAGE DEPARTMENT AND DESIGNATIONS

    //select data rows to get data back to the text fields
    private void addEmployeeSelect(employeeData selectedEmployee) {
        addEmployee_employeeID.setText(String.valueOf(selectedEmployee.getEmployeeID()));
        addEmployee_name.setText(selectedEmployee.getName());
        addEmployee_salary.setText(String.valueOf(selectedEmployee.getSalary()));
        addEmployee_phoneNum.setText(selectedEmployee.getPhoneNumber());
        addEmployee_epfNo.setText(selectedEmployee.getEpfNumber());

    }

    //get Department Names
    public String[] getDepartmentNames() {
        List<String> departmentNames = new ArrayList<>();

        try (Connection connection = database.connectDb()) {
            String query = "SELECT DepartmentName FROM Department";

            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String departmentName = resultSet.getString("DepartmentName");
                    departmentNames.add(departmentName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the List to an array
        return departmentNames.toArray(new String[0]);
    }

    //Assign departmentnames to the combo box
    public void addEmployeeDepartmentList() {
        String[] departmentNames = getDepartmentNames();

        List<String> listDepartments = new ArrayList<>();

        for (String data : departmentNames) {
            listDepartments.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listDepartments);
        addEmployee_depart.setItems(listData);
    }

    //get Designation Names
    public String[] getDesignationNames() {
        List<String> designationNames = new ArrayList<>();

        try (Connection connection = database.connectDb()) {
            String query = "SELECT DesignationName FROM Designation";

            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String designationName = resultSet.getString("DesignationName");
                    designationNames.add(designationName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the List to an array
        return designationNames.toArray(new String[0]);
    }

    //Assign designation to the combo box
    public void addEmployeeDesignationList() {
        String[] designationNames = getDesignationNames();

        List<String> listDesignations = new ArrayList<>();

        for (String data : designationNames) {
            listDesignations.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listDesignations);
        addEmployee_desig.setItems(listData);
    }

    private String[] listGender = {"Male", "Female", "Others"};

    public void addEmployeeGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : listGender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        addEmployee_gender.setItems(listData);

    }
    

    
    
    //METHODS USED WHEN Dashboard.XML LOADED (from the intial moment)
    

    public void displayUsername() {
        username.setText(getData.username);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {

            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            addUser_form.setVisible(false);
            depDesig_form.setVisible(false);

            addEmployeeGenderList();
            addEmployeeDepartmentList();
            addEmployeeDesignationList();
            addEmployeeSearch();
            homeTotalEmployees();

            
        } else if (event.getSource() == addEmployee_btn) {
            if (isUserAdmin()) {

                home_form.setVisible(false);
                addEmployee_form.setVisible(true);
                addUser_form.setVisible(false);
                depDesig_form.setVisible(false);

                addEmployee_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        addEmployeeSelect(newSelection);
                    }
                });

                addEmployeeGenderList();
                addEmployeeDepartmentList();
                addEmployeeDesignationList();
                addEmployeeSearch();

            } else {
                showAccessError();
            }
        } else if (event.getSource() == addUser_btn) {
            if (isUserAdmin()) {

                home_form.setVisible(false);
                addEmployee_form.setVisible(false);
                addUser_form.setVisible(true);
                depDesig_form.setVisible(false);

                showAdminListData();

            } else {
                showAccessError();
            }
        } else if (event.getSource() == addDepartment_btn) {
            if (isUserAdmin()) {

                home_form.setVisible(false);
                addEmployee_form.setVisible(false);
                addUser_form.setVisible(true);
                depDesig_form.setVisible(true);

                showDepartmentListData();
                showDesignationListData();

            } else {
                showAccessError();
            }
        }
    }

    private boolean isUserAdmin() {
        // Get the user's role (stored during login)
        String userRole = getUserRole(); 

        // Check if the user is an admin
        return "Admin".equals(userRole);
    }

    private void showAccessError() {
        // Show an error message to the user
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Access Denied");
        alert.setHeaderText(null);
        alert.setContentText("You don't have access to this functionality.");
        alert.showAndWait();
    }

    public void logout() throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure ?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {

            logout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {

                x = event.getSceneX();
                y = event.getSceneY();

            });

            root.setOnMouseDragged((MouseEvent event) -> {

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

                stage.setOpacity(0.8);
            });

            root.setOnMouseReleased((MouseEvent event) -> {
                stage.setOpacity(1);
            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        }

    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayUsername();
        addEmployeeShowListData();
        addEmployeeGenderList();
        addEmployeeDepartmentList();
        addEmployeeDesignationList();

        homeTotalEmployees();

        showDepartmentListData();
        showDesignationListData();

        showAdminListData();

        addEmployee_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addEmployeeSelect(newSelection);
            }
        });

    }

}
