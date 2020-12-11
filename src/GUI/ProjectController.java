package GUI;

import MyFile.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import Classes.*;

import java.util.ArrayList;

public class ProjectController {
    @FXML
    private Tab projects;
    @FXML
    private Tab projectInfo;
    @FXML
    private Tab requirements;
    @FXML
    private Tab requirementsInfo;
    @FXML
    private Tab tasks;
    @FXML
    private Tab taskInfo;
    @FXML
    private TabPane tabPane;

    @FXML
    private Menu position;
    @FXML
    private RadioMenuItem scrumMaster;
    @FXML
    private RadioMenuItem projectCreator;
    @FXML
    private RadioMenuItem teamMember;
    @FXML
    private ToggleGroup MenuItemToggleGroup;
    @FXML
    private MenuItem about;
    @FXML
    private MenuBar menuBar;


    @FXML
    private Button addProject;
    @FXML
    private Button deleteProject;
    @FXML
    private Button addRequirement;
    @FXML
    private Button deleteRequirement;
    @FXML
    private Button addTask;
    @FXML
    private Button deleteTask;

    @FXML
    private TextField projectName;
    @FXML
    private TextField projectID;
    @FXML
    private TextField productOwner;
    @FXML
    private TextField projectEstimatedTime;
    @FXML
    private TextField projectDeadlineDd;
    @FXML
    private TextField projectDeadlineMm;
    @FXML
    private TextField projectDeadlineYyyy;
    @FXML
    private TextArea projectDescription;
    @FXML
    private ComboBox projectStatus;
    @FXML
    private TextField projectHoursWorked;


    @FXML
    private TextField requirementName;
    @FXML
    private TextField requirementID;
    @FXML
    private RadioButton priority1;
    @FXML
    private RadioButton priority2;
    @FXML
    private RadioButton priority3;
    @FXML
    private ToggleGroup ToggleGroup;
    @FXML
    private TextField requirementEstimatedTime;
    @FXML
    private TextField requirementDeadlineDd;
    @FXML
    private TextField requirementDeadlineMm;
    @FXML
    private TextField requirementDeadlineYyyy;
    @FXML
    private TextArea requirementDescription;
    @FXML
    private ComboBox requirementStatus;
    @FXML
    private TextField requirementHoursWorked;


    @FXML
    private TextField taskName;
    @FXML
    private TextField taskID;
    @FXML
    private ComboBox respTeamMember;
    @FXML
    private TextField taskEstimatedTime;
    @FXML
    private TextField taskDeadlineDd;
    @FXML
    private TextField taskDeadlineMm;
    @FXML
    private TextField taskDeadlineYyyy;
    @FXML
    private TextArea taskDescription;
    @FXML
    private TextField taskHoursWorked;
    @FXML
    private ComboBox<String> taskStatus;

    @FXML
    private Button taskSave;
    @FXML
    private Button taskChange;
    @FXML
    private Button requirementSave;
    @FXML
    private Button requirementChange;
    @FXML
    private Button projectSave;
    @FXML
    private Button projectChange;

    @FXML
    private ListView<Project> projectListView;
    @FXML
    private ListView<Requirement> requirementListView;
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private Button saveToPMS;

    private FileAdapter adapter;
    private int requirementPriorityInteger;
    ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();

    public void initialize() {
        adapter = new FileAdapter("employees.bin");

        InfoRespMember();
        statusBox();
        taskStatus.getSelectionModel().select("Not started");
        requirementStatus.getSelectionModel().select("Not started");
        projectStatus.getSelectionModel().select("Not started");
    }

    public void handleActions(ActionEvent e) {
        if (scrumMaster.isSelected()) {
            addProject.setDisable(true);

        }
        if (projectCreator.isSelected()) {
            if (e.getSource() == addProject) {
                tabPane.getSelectionModel().selectNext();
                projectInfo.setDisable(false);
                projectChange.setVisible(false);

                requirements.setDisable(false);
                clearProject();


            }
            if (e.getSource() == addRequirement) {
                tabPane.getSelectionModel().selectNext();
                requirementsInfo.setDisable(false);
                requirementChange.setVisible(false);
                //Requirement requirement = new Requirement()

                clearRequirement();
                tasks.setDisable(false);
            }
            if (e.getSource() == addTask) {
                tabPane.getSelectionModel().selectNext();
                taskInfo.setDisable(false);
                taskChange.setVisible(false);
                taskSave.setDisable(false);

                clearTask();

            }
            if (e.getSource() == taskSave) {
                Deadline deadline = new Deadline(Integer.parseInt(taskDeadlineDd.getText()),
                        Integer.parseInt(taskDeadlineMm.getText()), Integer.parseInt(taskDeadlineYyyy.getText()));

                Task task = new Task(taskName.getText(), Integer.parseInt(taskID.getText()),
                        taskDescription.getText(), Integer.parseInt(taskEstimatedTime.getText()),
                        taskStatus.getSelectionModel().getSelectedItem(), Integer.parseInt(taskHoursWorked.getText()),
                        deadline, (Employee) respTeamMember.getSelectionModel().getSelectedItem());

                taskListView.getItems().add(task);

                //clearTask();

                tabPane.getSelectionModel().select(tasks);
                requirementSave.setDisable(false);


            }
            if (e.getSource() == requirementSave) {
                Requirement requirement = new Requirement(requirementName.getText(), Integer.parseInt(requirementID.getText()),
                        requirementDescription.getText(), Integer.parseInt(requirementEstimatedTime.getText()), requirementPriorityInteger,
                        new Deadline(Integer.parseInt(requirementDeadlineDd.getText()), Integer.parseInt(requirementDeadlineMm.getText()),
                                Integer.parseInt(requirementDeadlineYyyy.getText())), requirementStatus.getSelectionModel().getSelectedItem().toString());
                for (int i = 0; i < taskListView.getItems().size(); i++) {
                    requirement.addTask(taskListView.getItems().get(i));
                }
                requirementListView.getItems().add(requirement);
                String requirementHoursWorkedString = requirement.hoursWorkedOnRequirement() + "";
                requirementHoursWorked.setText(requirementHoursWorkedString);
                tabPane.getSelectionModel().select(requirements);
                clearTask();
                projectSave.setDisable(false);


            }
            if (e.getSource() == projectSave) {
                String temp = productOwner.getText();
                String[] stringArr = temp.split(" ");


                String firstName = stringArr[0];
                String lastName = stringArr[1];

                Project project = new Project(projectName.getText(),
                        Integer.parseInt(projectID.getText()), projectDescription.getText(),
                        Integer.parseInt(projectEstimatedTime.getText()),
                        projectStatus.getSelectionModel().getSelectedItem().toString(),
                        new Deadline(Integer.parseInt(projectDeadlineDd.getText()), Integer.parseInt(projectDeadlineMm.getText()),
                                Integer.parseInt(projectDeadlineYyyy.getText())), new ProductOwner(firstName, lastName));

                projectListView.getItems().add(project);
                for (int i = 0; i < requirementListView.getItems().size(); i++) {
                    project.addRequirement(requirementListView.getItems().get(i));
                }
                String projectHoursWorkedString = project.getHoursWorked() + "";
                projectHoursWorked.setText(projectHoursWorkedString);

//                clearProject();
              clearRequirement();
                tabPane.getSelectionModel().select(projects);


            }
            if (priority1.isSelected()) {
                requirementPriorityInteger = 1;
            }
            if (priority2.isSelected()) {
                requirementPriorityInteger = 2;
            }
            if (priority3.isSelected()) {
                requirementPriorityInteger = 3;
            }
            if (e.getSource() == saveToPMS) {
                for (int i = 0; i < projectListView.getItems().size(); i++) {
                    projectManagementSystem.addProject(projectListView.getItems()
                            .get(i));
                    System.out.println("Saved!");
                }
                clearProject();
                adapter = new FileAdapter("ProjectManagementSystem.bin");
                adapter.saveToPMSFile(projectManagementSystem);
                System.out.println("Saved to file!");

            }
            if (teamMember.isSelected()) {

            }
            taskListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Task> ov, Task old_val, Task new_val) -> {
                tabPane.getSelectionModel().selectNext();
                Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
                taskName.setText(selectedTask.getName());
                taskID.setText(selectedTask.getID() + "");
                respTeamMember.getSelectionModel().select(selectedTask.getEmployee());
                taskHoursWorked.setText(selectedTask.getHoursWorked() + "");
                taskEstimatedTime.setText(selectedTask.getEstimatedTime() + "");
                taskDeadlineDd.setText(selectedTask.getDeadline().getDay() + "");
                taskDeadlineMm.setText(selectedTask.getDeadline().getMonth() + "");
                taskDeadlineYyyy.setText(selectedTask.getDeadline().getYear() + "");
                taskStatus.getSelectionModel().select(selectedTask.getStatus());
                taskDescription.setText(selectedTask.getDescription());
            });
        }
    }


        public void InfoRespMember () {
            ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();
            projectManagementSystem.getEmployees().addAll(adapter.getAllEmployees());
            respTeamMember.getItems().clear();
            for (int i = 0; i < projectManagementSystem.getEmployees().size(); i++) {
                respTeamMember.getItems().add(projectManagementSystem.getEmployees().get(i));
            }
        }

        private void statusBox () {
            String[] statuses = {"Started", "Not started", "Approved", "Rejected", "Ended"};
            taskStatus.getItems().addAll(statuses);
            requirementStatus.getItems().addAll(statuses);
            projectStatus.getItems().addAll(statuses);
        }
        private void clearTask () {
            taskName.clear();
            taskID.clear();
            respTeamMember.getSelectionModel().clearSelection();
            respTeamMember.setPromptText("Choose employee");
            taskEstimatedTime.clear();
            taskHoursWorked.clear();
            taskDeadlineDd.clear();
            taskDeadlineMm.clear();
            taskDeadlineYyyy.clear();
            taskStatus.getSelectionModel().select("Not started");
            taskDescription.clear();
        }
        private void clearRequirement ()
        {
            taskListView.getItems().clear();
            requirementName.clear();
            requirementID.clear();
            priority1.setSelected(false);
            priority2.setSelected(false);
            priority3.setSelected(false);
            requirementEstimatedTime.clear();
            requirementHoursWorked.clear();
            requirementDeadlineDd.clear();
            requirementDeadlineMm.clear();
            requirementDeadlineYyyy.clear();
            requirementDescription.clear();
            requirementStatus.getSelectionModel().select("Not started");
        }
        private void clearProject()
        {
            requirementListView.getItems().clear();
            projectName.clear();
            projectHoursWorked.clear();
            projectID.clear();
            productOwner.clear();
            projectEstimatedTime.clear();
            projectDeadlineDd.clear();
            projectDeadlineMm.clear();
            projectDeadlineYyyy.clear();
            projectStatus.getSelectionModel().select("Not started");
        }
}
