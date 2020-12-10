package GUI;

import MyFile.FileAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import Classes.*;

public class ProjectController
{
  @FXML private Tab projects;
  @FXML private Tab projectInfo;
  @FXML private Tab requirements;
  @FXML private Tab requirementsInfo;
  @FXML private Tab tasks;
  @FXML private Tab taskInfo;
  @FXML private TabPane tabPane;

  @FXML private Menu position;
  @FXML private RadioMenuItem scrumMaster;
  @FXML private RadioMenuItem projectCreator;
  @FXML private RadioMenuItem teamMember;
  @FXML private ToggleGroup MenuItemToggleGroup;
  @FXML private MenuItem about;
  @FXML private MenuBar menuBar;


  @FXML private Button addProject;
  @FXML private Button deleteProject;
  @FXML private Button addRequirement;
  @FXML private Button deleteRequirement;
  @FXML private Button addTask;
  @FXML private Button deleteTask;

  @FXML private TextField projectName;
  @FXML private TextField projectID;
  @FXML private TextField productOwner;
  @FXML private TextField projectEstimatedTime;
  @FXML private TextField projectDeadline;
  @FXML private TextArea projectDescription;
  @FXML private ComboBox projectStatus;
  @FXML private TextField projectHoursWorked;


  @FXML private TextField requirementName;
  @FXML private TextField requirementID;
  @FXML private ToggleButton requirementPriority ;
  @FXML private RadioButton priority1;
  @FXML private RadioButton priority2;
  @FXML private RadioButton priority3;
  @FXML private ToggleGroup ToggleGroup;
  @FXML private TextField requirementEstimatedTime;
  @FXML private TextField requirementDeadline;
  @FXML private TextArea requirementDescription;
  @FXML private ComboBox requirementStatus;


  @FXML private TextField taskName;
  @FXML private TextField taskID;
  @FXML private ComboBox respTeamMember;
  @FXML private TextField taskEstimatedTime;
  @FXML private TextField taskDeadline;
  @FXML private TextArea taskDescription;
  @FXML private TextField taskHoursWorked;
  @FXML private ComboBox taskStatus;

  @FXML private Button taskSave;
  @FXML private Button taskChange;
  @FXML private Button requirementSave;
  @FXML private Button requirementChange;
  @FXML private Button projectSave;
  @FXML private Button projectChange;

  @FXML private ListView projectList;
  @FXML private ListView requirementList;
  @FXML private ListView taskList;

  private FileAdapter adapter;

  public void initialize()
  {
    adapter = new FileAdapter("employees.bin");
    InfoRespMember();
  }

  public void handleActions(ActionEvent e)
  {
      if (scrumMaster.isSelected())
      {

      }
      if (projectCreator.isSelected())
      {
          if(e.getSource() == addProject)
          {
              tabPane.getSelectionModel().selectNext();
              projectInfo.setDisable(false);
              projectChange.setVisible(false);

              requirements.setDisable(false);


          }
        if(e.getSource() == addRequirement){
          tabPane.getSelectionModel().selectNext();
          requirementsInfo.setDisable(false);
          requirementChange.setVisible(false);

          tasks.setDisable(false);
        }
        if(e.getSource() == addTask){
          tabPane.getSelectionModel().selectNext();
          taskInfo.setDisable(false);
          taskChange.setVisible(false);

          taskSave.setDisable(false);
        }
      }
      if (teamMember.isSelected())
      {

      }


  }


  public void InfoRespMember()
  {
      ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();
      projectManagementSystem.getEmployees().addAll(adapter.getAllEmployees());
      respTeamMember.getItems().clear();
      for (int i = 0; i < projectManagementSystem.getEmployees().size(); i++)
      {
        respTeamMember.getItems().add(projectManagementSystem.getEmployees().get(i));
      }
    }
    
  }