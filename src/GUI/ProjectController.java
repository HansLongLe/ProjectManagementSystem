package GUI;

import MyFile.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import Classes.*;
import parser.ParserException;
import parser.XmlJsonParser;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
    @FXML private TextField projectDeadlineDd;
    @FXML private TextField projectDeadlineMm;
    @FXML private TextField projectDeadlineYyyy;
    @FXML private TextArea projectDescription;
    @FXML private ComboBox projectStatus;
    @FXML private TextField projectHoursWorked;


    @FXML private TextField requirementName;
    @FXML private TextField requirementID;
    @FXML private RadioButton priority1;
    @FXML private RadioButton priority2;
    @FXML private RadioButton priority3;
    @FXML private ToggleGroup ToggleGroup;
    @FXML private TextField requirementEstimatedTime;
    @FXML private TextField requirementDeadlineDd;
    @FXML private TextField requirementDeadlineMm;
    @FXML private TextField requirementDeadlineYyyy;
    @FXML private TextArea requirementDescription;
    @FXML private ComboBox requirementStatus;
    @FXML private TextField requirementHoursWorked;


    @FXML private TextField taskName;
    @FXML private TextField taskID;
    @FXML private ComboBox respTeamMember;
    @FXML private TextField taskEstimatedTime;
    @FXML private TextField taskDeadlineDd;
    @FXML private TextField taskDeadlineMm;
    @FXML private TextField taskDeadlineYyyy;
    @FXML private TextArea taskDescription;
    @FXML private TextField taskHoursWorked;
    @FXML private ComboBox<String> taskStatus;

    @FXML private Button taskSave;
    @FXML private Button taskChange;
    @FXML private Button requirementSave;
    @FXML private Button requirementChange;
    @FXML private Button projectSave;
    @FXML private Button projectChange;

    @FXML private ListView<Project> projectListView;
    @FXML private ListView<Requirement> requirementListView;
    @FXML private ListView<Task> taskListView;
    @FXML private Button saveToPMS;

    @FXML private Label taskIDLabel;
    @FXML private Label taskNameLabel;
    @FXML private Label taskEmployee;
    @FXML private Label taskHWorked;
    @FXML private Label taskETime;
    @FXML private Label taskDeadline;

    @FXML private Label reqIDLabel;
    @FXML private Label reqNameLabel;
    @FXML private Label reqPriority;
    @FXML private Label reqETime;
    @FXML private Label reqDeadline;

    @FXML private Label projIDLabel;
    @FXML private Label projNameLabel;
    @FXML private Label projProductOwner;
    @FXML private Label projETime;
    @FXML private Label projDeadline;

    private FileAdapter adapter;
    private FileAdapter adapter2;
    private int requirementPriorityInteger;
    private boolean requirementAddClicked = false;
    private boolean taskAddClicked = false;
    private boolean proTruth = true;
    private boolean reqTruth = true;
    private boolean taskTruth = true;
    private String rightMonthNumber = "";

    ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();

    /**
     * This method is activated when the GUI is opened.
     */
    public void initialize()
    {
        adapter = new FileAdapter("employees.bin");
        adapter2 = new FileAdapter("ProjectManagementSystem.bin");
        loadProjects();
        InfoRespMember();
        statusBox();
        taskStatus.getSelectionModel().select("Not started");
        requirementStatus.getSelectionModel().select("Not started");
        projectStatus.getSelectionModel().select("Not started");

        numericOnly();

        if (priority1.isSelected())
        {
            requirementPriorityInteger = 1;
        }
        if (priority2.isSelected())
        {
            requirementPriorityInteger = 2;
        }
        if (priority3.isSelected())
        {
            requirementPriorityInteger = 3;
        }


        deleteProject.setDisable(true);
        deleteTask.setDisable(true);
        deleteRequirement.setDisable(true);
        lockTask();

        taskListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Task> task, Task old_task, Task new_task) -> {
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

            if (projectCreator.isSelected())
            {
                taskSave.setVisible(false);
            }
            if (scrumMaster.isSelected()){
                taskChange.setVisible(true);
                taskChange.setDisable(false);
            }

            lockTask();

            taskSave.setDisable(true);
            taskInfo.setDisable(false);
            tabPane.getSelectionModel().select(taskInfo);
        });

        requirementListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Requirement> ov, Requirement old_requirement, Requirement new_requirement) -> {

            Requirement selectedRequirement = requirementListView.getSelectionModel().getSelectedItem();

            requirementName.setText(selectedRequirement.getName());
            requirementID.setText(selectedRequirement.getID() + "");
            if (selectedRequirement.getPriority() == 1)
            {
                priority1.setSelected(true);
            }
            if (selectedRequirement.getPriority() == 2)
            {
                priority2.setSelected(true);
            }
            if (selectedRequirement.getPriority() == 3)
            {
                priority3.setSelected(true);
            }
            requirementHoursWorked.setText(selectedRequirement.hoursWorkedOnRequirement() + "");
            requirementEstimatedTime.setText(selectedRequirement.getEstimatedTime() + "");
            requirementDeadlineDd.setText(selectedRequirement.getDeadline().getDay() + "");
            requirementDeadlineMm.setText(selectedRequirement.getDeadline().getMonth() + "");
            requirementDeadlineYyyy.setText(selectedRequirement.getDeadline().getYear() + "");
            requirementStatus.getSelectionModel().select(selectedRequirement.getStatus());
            requirementDescription.setText(selectedRequirement.getDescription());
            if (projectCreator.isSelected())
            {
                requirementSave.setVisible(false);
            }
            if (scrumMaster.isSelected())
            {
                requirementChange.setVisible(true);
                requirementChange.setDisable(false);
            }

            lockRequirement();

            taskListView.getItems().clear();
            clearTask();
            for (int i = 0; i < selectedRequirement.getTask().size(); i++)
            {
                taskListView.getItems().add(selectedRequirement.getTask().get(i));
            }
            taskInfo.setDisable(true);
            requirementSave.setDisable(true);
            requirementsInfo.setDisable(false);
            tasks.setDisable(false);
            tabPane.getSelectionModel().select(requirementsInfo);
        });

        projectListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Project> project, Project old_project, Project new_project) -> {
            Project selectedProject = projectListView.getSelectionModel().getSelectedItem();
            projectName.setText(selectedProject.getName());
            projectID.setText(selectedProject.getID() + "");
            productOwner.setText(selectedProject.getProductOwner().toString());
            projectHoursWorked.setText(selectedProject.getHoursWorked() + "");
            projectEstimatedTime.setText(selectedProject.getEstimatedTime() + "");
            projectDeadlineDd.setText(selectedProject.getDeadline().getDay() + "");
            projectDeadlineMm.setText(selectedProject.getDeadline().getMonth() + "");
            projectDeadlineYyyy.setText(selectedProject.getDeadline().getYear() + "");
            projectStatus.getSelectionModel().select(selectedProject.getStatus());
            projectDescription.setText(selectedProject.getDescription());

           lockProject();

            requirementListView.getItems().clear();
            clearTask();
            clearRequirement();
            for (int i = 0; i < selectedProject.getRequirements().size(); i++) {
                requirementListView.getItems().add(selectedProject.getRequirements().get(i));
            }
            projectSave.setDisable(true);
            projectInfo.setDisable(false);
            requirements.setDisable(false);
            requirementsInfo.setDisable(true);
            taskInfo.setDisable(true);
            tasks.setDisable(true);
            tabPane.getSelectionModel().select(projectInfo);
            if (projectCreator.isSelected())
            {
                projectSave.setVisible(false);
            }
        });
    }



    public void handleActions(ActionEvent e) {

        if (e.getSource() == about)
        {
            showAlert();
        }
        if (scrumMaster.isSelected()) {

            lockProject();

            lockRequirement();
            lockTask();

            addProject.setVisible(false);
            deleteProject.setVisible(true);

            projectSave.setVisible(true);
            projectChange.setVisible(true);

            addRequirement.setVisible(true);
            deleteRequirement.setVisible(true);
            requirementSave.setVisible(true);
            requirementChange.setVisible(true);

            addTask.setVisible(true);
            deleteTask.setVisible(true);
            taskSave.setVisible(true);
            taskChange.setVisible(true);

            if (e.getSource() == projectChange) {
                unlockProject();
                projectSave.setDisable(false);
                deleteProject.setDisable(false);
                projectChange.setDisable(true);
            }
            if (e.getSource() == requirementChange) {
                unlockRequirement();
                requirementSave.setDisable(false);
                requirementAddClicked = false;
                deleteRequirement.setDisable(false);
                requirementChange.setDisable(true);
            }
            if (e.getSource() == taskChange) {
                unlockTask();
                taskSave.setDisable(false);
                taskAddClicked = false;
                deleteTask.setDisable(false);
                taskChange.setDisable(true);

            }
            if (e.getSource() == addRequirement) {
                clearRequirement();
                clearTask();
                unlockRequirement();
                taskListView.getItems().clear();
                tabPane.getSelectionModel().select(requirementsInfo);
                requirementsInfo.setDisable(false);
                tasks.setDisable(false);
                taskInfo.setDisable(true);

                requirementAddClicked = true;
                requirementChange.setVisible(false);


            }
            if (e.getSource() == addTask) {
                clearTask();
                unlockTask();
                tabPane.getSelectionModel().select(taskInfo);
                taskInfo.setDisable(false);
                taskSave.setDisable(false);

                taskAddClicked = true;
                taskChange.setVisible(false);

            }
            if (e.getSource() == projectSave) {
                projectExceptions();
                if (proTruth == true) {
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


                    for (int i = 0; i < requirementListView.getItems().size(); i++) {
                        project.addRequirement(
                                requirementListView.getItems().get(i));
                    }
                    projectListView.getItems().set(
                            projectListView.getSelectionModel().getSelectedIndex(),
                            project);
                    String projectHoursWorkedString = project.getHoursWorked() + "";
                    projectHoursWorked.setText(projectHoursWorkedString);

                    lockProject();
                    clearProject();
                    clearRequirement();
                    clearTask();
                    projectChange.setDisable(false);
                    deleteProject.setDisable(true);
                    saveToPMS.setVisible(true);
                    saveToPMS.setDisable(false);
                    projectInfo.setDisable(true);
                    requirements.setDisable(true);
                    requirementsInfo.setDisable(true);
                    tasks.setDisable(true);
                    taskInfo.setDisable(true);
                    tabPane.getSelectionModel().select(projects);
                }
            }
            if (e.getSource() == requirementSave) {
                requirementException();
                if (reqTruth) {
                    Requirement requirement = new Requirement(requirementName.getText(), Integer.parseInt(requirementID.getText()),
                            requirementDescription.getText(), Integer.parseInt(requirementEstimatedTime.getText()), requirementPriorityInteger,
                            new Deadline(Integer.parseInt(requirementDeadlineDd.getText()), Integer.parseInt(requirementDeadlineMm.getText()),
                                    Integer.parseInt(requirementDeadlineYyyy.getText())), requirementStatus.getSelectionModel().getSelectedItem().toString());


                    for (int i = 0; i < taskListView.getItems().size(); i++) {
                        requirement.addTask(taskListView.getItems().get(i));
                    }

                    if (requirementAddClicked) {
                        requirementListView.getItems().add(requirement);

                    } else {
                        requirementListView.getItems().set(
                                requirementListView.getSelectionModel().getSelectedIndex(), requirement);
                    }

                    String requirementHoursWorkedString =
                            requirement.hoursWorkedOnRequirement() + "";
                    requirementHoursWorked.setText(requirementHoursWorkedString);

                    clearTask();
                    clearRequirement();
                    lockRequirement();

                    requirementChange.setDisable(false);
                    deleteRequirement.setDisable(true);

                    taskListView.getItems().clear();
                    tabPane.getSelectionModel().select(requirements);
                    requirementSave.setDisable(true);
                    requirementsInfo.setDisable(true);
                    tasks.setDisable(true);
                    taskInfo.setDisable(true);
                }
            }
            if (e.getSource() == taskSave) {
                taskException();
                if (taskTruth) {
                    Deadline deadline = new Deadline(Integer.parseInt(taskDeadlineDd.getText()),
                            Integer.parseInt(taskDeadlineMm.getText()), Integer.parseInt(taskDeadlineYyyy.getText()));

                    Task task = new Task(taskName.getText(), Integer.parseInt(taskID.getText()),
                            taskDescription.getText(), Integer.parseInt(taskEstimatedTime.getText()),
                            taskStatus.getSelectionModel().getSelectedItem(),
                            Integer.parseInt(taskHoursWorked.getText()), deadline,
                            (Employee) respTeamMember.getSelectionModel().getSelectedItem());

                    if (taskAddClicked) {
                        taskListView.getItems().add(task);

                        requirementSave.setDisable(false);

                    }
                    if (!taskAddClicked) {

                        taskListView.getItems().set(taskListView.getSelectionModel().getSelectedIndex(), task);
                        requirementSave.setDisable(true);
                    }


                    taskSave.setDisable(true);
                    taskChange.setDisable(true);

                    clearTask();

                    taskChange.setDisable(false);
                    deleteTask.setDisable(true);

                    tabPane.getSelectionModel().select(tasks);
                    taskInfo.setDisable(true);
                }
            }
            if (e.getSource() == deleteProject)
            {
                clearTask();
                clearRequirement();
                clearProject();

                lockTask();
                lockRequirement();
                lockProject();

                projectManagementSystem.getProjects().remove(projectListView.getSelectionModel().getSelectedIndex());
                projectListView.getItems().remove(projectListView.getSelectionModel().getSelectedIndex());

                tasks.setDisable(true);
                taskInfo.setDisable(true);
                requirementsInfo.setDisable(true);
                requirements.setDisable(true);
                projectInfo.setDisable(true);

                tabPane.getSelectionModel().select(projects);

                projectChange.setDisable(false);
                projectSave.setDisable(true);
                deleteProject.setDisable(true);

            }
            if (e.getSource() == deleteRequirement)
            {
                clearTask();
                clearRequirement();

                lockTask();
                lockRequirement();

                projectListView.getSelectionModel().getSelectedItem().getRequirements().remove(requirementListView.getSelectionModel().getSelectedItem());
                requirementListView.getItems().remove(requirementListView.getSelectionModel().getSelectedIndex());

                tasks.setDisable(true);
                taskInfo.setDisable(true);
                requirementsInfo.setDisable(true);

                tabPane.getSelectionModel().select(requirements);

                requirementChange.setDisable(false);
                requirementSave.setDisable(true);
                deleteRequirement.setDisable(true);

            }
            if (e.getSource() == deleteTask)
            {
                clearTask();

                lockTask();

                requirementListView.getSelectionModel().getSelectedItem().getTask().remove(taskListView.getSelectionModel().getSelectedItem());
                taskListView.getItems().remove(taskListView.getSelectionModel().getSelectedIndex());

                taskInfo.setDisable(true);

                tabPane.getSelectionModel().select(tasks);

                taskChange.setDisable(false);
                taskSave.setDisable(true);
                deleteTask.setDisable(true);

            }
        }

        if (projectCreator.isSelected()) {

            lockProject();
            lockRequirement();
            lockTask();

            addProject.setVisible(true);
            addRequirement.setVisible(true);
            addTask.setVisible(true);

            addProject.setDisable(false);
            addRequirement.setDisable(true);
            addTask.setDisable(true);

            saveToPMS.setVisible(true);
            saveToPMS.setDisable(true);

            projectSave.setVisible(true);
            requirementSave.setVisible(true);
            taskSave.setVisible(true);

            projectSave.setDisable(true);
            requirementSave.setDisable(true);
            taskSave.setDisable(true);

            projectChange.setVisible(false);
            requirementChange.setVisible(false);
            taskChange.setVisible(false);

            deleteProject.setVisible(false);
            deleteRequirement.setVisible(false);
            deleteTask.setVisible(false);

            if (e.getSource() == projectCreator)
            {

                lockProject();
                lockRequirement();
                lockTask();

                addProject.setVisible(true);
                addRequirement.setVisible(true);
                addTask.setVisible(true);

                addProject.setDisable(false);
                addRequirement.setDisable(true);
                addTask.setDisable(true);

                saveToPMS.setVisible(true);
                saveToPMS.setDisable(true);

                projectSave.setVisible(true);
                requirementSave.setVisible(true);
                taskSave.setVisible(true);

                projectSave.setDisable(true);
                requirementSave.setDisable(true);
                taskSave.setDisable(true);

                projectChange.setVisible(false);
                requirementChange.setVisible(false);
                taskChange.setVisible(false);

                deleteProject.setVisible(false);
                deleteRequirement.setVisible(false);
                deleteTask.setVisible(false);
            }


            if (e.getSource() == addProject)
            {
                clearProject();
                clearRequirement();
                clearTask();
                unlockProject();

                requirementListView.getItems().clear();
                taskListView.getItems().clear();

                addRequirement.setDisable(false);

                projectInfo.setDisable(false);
                requirements.setDisable(false);

                requirementsInfo.setDisable(true);
                tasks.setDisable(true);
                taskInfo.setDisable(true);
                addProject.setDisable(true);

                tabPane.getSelectionModel().select(projectInfo);

            }
            if (e.getSource() == addRequirement)
            {
                clearRequirement();
                clearTask();
                unlockRequirement();

                taskListView.getItems().clear();

                addTask.setDisable(false);

                requirementsInfo.setDisable(false);
                tasks.setDisable(false);

                taskInfo.setDisable(true);
                addRequirement.setDisable(true);

                tabPane.getSelectionModel().select(requirementsInfo);

            }
            if (e.getSource() == addTask)
            {
                clearTask();
                unlockTask();

                taskInfo.setDisable(false);
                taskSave.setDisable(false);

                addTask.setDisable(true);

                tabPane.getSelectionModel().select(taskInfo);
            }
            if (e.getSource() == taskSave)
            {
                taskException();
                if(taskTruth)
                {
                Deadline deadline = new Deadline(Integer.parseInt(taskDeadlineDd.getText()),
                        Integer.parseInt(taskDeadlineMm.getText()), Integer.parseInt(taskDeadlineYyyy.getText()));

                Task task = new Task(taskName.getText(), Integer.parseInt(taskID.getText()),
                    taskDescription.getText(), Integer.parseInt(taskEstimatedTime.getText()),
                    taskStatus.getSelectionModel().getSelectedItem(), Integer.parseInt(taskHoursWorked.getText()),
                    deadline, (Employee) respTeamMember.getSelectionModel().getSelectedItem());


                    taskListView.getItems().add(task);

                    requirementSave.setDisable(false);
                    taskInfo.setDisable(true);

                    taskNameLabel.setVisible(false);
                    taskIDLabel.setVisible(false);
                    taskEmployee.setVisible(false);
                    taskETime.setVisible(false);
                    taskHWorked.setVisible(false);
                    taskDeadline.setVisible(false);
                    requirementSave.setDisable(false);

                    addTask.setDisable(false);
                    taskSave.setDisable(true);

                    tabPane.getSelectionModel().select(tasks);
                }
            }

            if (e.getSource() == requirementSave)
            {
                requirementException();
                if (reqTruth) {
                Requirement requirement = new Requirement(requirementName.getText(), Integer.parseInt(requirementID.getText()),
                        requirementDescription.getText(), Integer.parseInt(requirementEstimatedTime.getText()), requirementPriorityInteger,
                        new Deadline(Integer.parseInt(requirementDeadlineDd.getText()), Integer.parseInt(requirementDeadlineMm.getText()),
                                Integer.parseInt(requirementDeadlineYyyy.getText())), requirementStatus.getSelectionModel().getSelectedItem().toString());
                for (int i = 0; i < taskListView.getItems().size(); i++) {
                    requirement.addTask(taskListView.getItems().get(i));
                }
                String requirementHoursWorkedString = requirement.hoursWorkedOnRequirement() + "";

                    requirementListView.getItems().add(requirement);
                    requirementHoursWorked.setText(requirementHoursWorkedString);

                    clearTask();
                    projectSave.setDisable(false);
                    requirementSave.setDisable(true);
                    requirementsInfo.setDisable(true);
                    tasks.setDisable(true);
                    taskInfo.setDisable(true);
                    addRequirement.setDisable(false);

                    reqNameLabel.setVisible(false);
                    reqIDLabel.setVisible(false);
                    reqPriority.setVisible(false);
                    reqETime.setVisible(false);
                    reqDeadline.setVisible(false);

                    tabPane.getSelectionModel().select(requirements);
                }


            }
            if (e.getSource() == projectSave)
            {
                projectExceptions();
                if (proTruth == true)
                {
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
                String projectHoursWorkedString = project.getHoursWorked() + "";

                    projectListView.getItems().add(project);
                    for (int i = 0; i < requirementListView.getItems().size(); i++) {
                        project.addRequirement(requirementListView.getItems().get(i));
                    }
                    projectHoursWorked.setText(projectHoursWorkedString);

                    clearRequirement();
                    clearTask();

                    saveToPMS.setDisable(false);

                    projectInfo.setDisable(true);
                    requirements.setDisable(true);
                    requirementsInfo.setDisable(true);
                    tasks.setDisable(true);
                    taskInfo.setDisable(true);

                    projectSave.setDisable(true);
                    addProject.setDisable(false);

                    projNameLabel.setVisible(false);
                    projIDLabel.setVisible(false);
                    projETime.setVisible(false);
                    projDeadline.setVisible(false);
                    projProductOwner.setVisible(false);

                    tabPane.getSelectionModel().select(projects);
                }
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
        }


        if (teamMember.isSelected())
        {
            lockProject();
            lockRequirement();
            lockTask();

            addProject.setVisible(false);
            addRequirement.setVisible(false);
            addTask.setVisible(false);

            projectSave.setVisible(false);
            requirementSave.setVisible(false);
            taskSave.setVisible(false);
            saveToPMS.setVisible(false);

            projectChange.setVisible(false);
            requirementChange.setVisible(false);
            taskChange.setVisible(false);

            deleteProject.setVisible(false);
            deleteRequirement.setVisible(false);
            deleteTask.setVisible(false);
        }

        if (e.getSource() == saveToPMS)
        {
            projectManagementSystem.getProjects().clear();
            for (int i = 0; i < projectListView.getItems().size(); i++) {
                projectManagementSystem.addProject(projectListView.getItems().get(i));
            }
            clearProject();
            adapter = new FileAdapter("ProjectManagementSystem.bin");
            adapter.saveToPMSFile(projectManagementSystem);
            System.out.println("Saved to file!");

            //Other actions

            //Other actions
            try{
                XmlJsonParser xjp = new XmlJsonParser();
                xjp.toXml(projectManagementSystem, "pms.xml");
            }
            catch( ParserException ex){
                System.out.println("IO exception");
            }


            if (taskListView.getItems().isEmpty()) {
                requirementSave.setDisable(true);
            }
            if (requirementListView.getItems().isEmpty()) {
                projectSave.setDisable(true);
            }
        }
        priority();
    }

    /**
     * This method loads the existent projects into a .bin file.
     */
    public void loadProjects()
        {
            projectManagementSystem = adapter.loadPMS();

                projectListView.getItems().addAll(projectManagementSystem.getProjects());

        }

    /**
     * This method load the Employees from the file into the ComboBox of employees.
     */
    public void InfoRespMember ()
        {
            ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();
            projectManagementSystem.getEmployees().addAll(adapter.getAllEmployees());
            respTeamMember.getItems().clear();
            for (int i = 0; i < projectManagementSystem.getEmployees().size(); i++) {
                respTeamMember.getItems().add(projectManagementSystem.getEmployees().get(i));
            }
        }

    /**
     * This method loads the 5 different statuses into the ComboBox of statuses.
     */
        private void statusBox ()
        {
            taskStatus.getItems().clear();
            requirementStatus.getItems().clear();
            projectStatus.getItems().clear();
            String[] statuses = {"Started", "Not started", "Approved", "Rejected", "Ended"};
            taskStatus.getItems().addAll(statuses);
            requirementStatus.getItems().addAll(statuses);
            projectStatus.getItems().addAll(statuses);
        }




    //CLEAR, LOCK, UNLOCK methods

    /**
     * This method clears all the information inside the text fields of a task tab.
     */
    private void clearTask ()
    {
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

    /**
     * This method clears all the information inside the text fields of a requirement tab.
     */
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

    /**
     * This method clears all the information inside the text fields of a project tab.
     */
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
        projectDescription.clear();
        projectStatus.getSelectionModel().select("Not started");
    }

    /**
     * This method sets all the text fields inside a project tab to uneditable.
     */
    private void lockProject()
    {
        projectName.setEditable(false);
        projectHoursWorked.setEditable(false);
        projectID.setEditable(false);
        productOwner.setEditable(false);
        projectEstimatedTime.setEditable(false);
        projectStatus.setDisable(true);
        projectDeadlineDd.setEditable(false);
        projectDeadlineMm.setEditable(false);
        projectDeadlineYyyy.setEditable(false);
        projectDescription.setEditable(false);
    }

    /**
     * This methods sets all the text fields inside a requirement tab to uneditable.
     */
    private void lockRequirement()
    {
        requirementName.setEditable(false);
        requirementID.setEditable(false);
        priority1.setDisable(true);
        priority2.setDisable(true);
        priority3.setDisable(true);
        requirementEstimatedTime.setEditable(false);
        requirementHoursWorked.setEditable(false);
        requirementStatus.setDisable(true);
        requirementDeadlineDd.setEditable(false);
        requirementDeadlineMm.setEditable(false);
        requirementDeadlineYyyy.setEditable(false);
        requirementDescription.setEditable(false);
    }

    /**
     * this method sets all the text fields inside a task tab to uneditable.
     */
    private void lockTask()
    {
        taskName.setEditable(false);
        taskID.setEditable(false);
        respTeamMember.setDisable(true);
        taskHoursWorked.setEditable(false);
        taskDeadlineDd.setEditable(false);
        taskDeadlineMm.setEditable(false);
        taskDeadlineYyyy.setEditable(false);
        taskStatus.setDisable(true);
        taskDescription.setEditable(false);
        taskEstimatedTime.setEditable(false);
    }

    /**
     * This method sets all the text fields ina project tab to editable.
     */
    private void unlockProject()
    {
        projectName.setEditable(true);
        projectID.setEditable(true);
        productOwner.setEditable(true);
        projectEstimatedTime.setEditable(true);
        projectStatus.setDisable(false);
        projectDeadlineDd.setEditable(true);
        projectDeadlineMm.setEditable(true);
        projectDeadlineYyyy.setEditable(true);
        projectDescription.setEditable(true);
    }

    /**
     * This method sets all the text fields inside a requirement tab to editable.
     */
    private void unlockRequirement()
    {
        requirementName.setEditable(true);
        requirementID.setEditable(true);
        priority1.setDisable(false);
        priority2.setDisable(false);
        priority3.setDisable(false);
        requirementEstimatedTime.setEditable(true);
        requirementStatus.setDisable(false);
        requirementDeadlineDd.setEditable(true);
        requirementDeadlineMm.setEditable(true);
        requirementDeadlineYyyy.setEditable(true);
        requirementDescription.setEditable(true);
    }

    /**
     * This method sets all the text fields inside a task tab to editable.
     */
    private void unlockTask()
    {
        taskName.setEditable(true);
        taskID.setEditable(true);
        respTeamMember.setDisable(false);
        taskHoursWorked.setEditable(true);
        taskDeadlineDd.setEditable(true);
        taskDeadlineMm.setEditable(true);
        taskDeadlineYyyy.setEditable(true);
        taskStatus.setDisable(false);
        taskDescription.setEditable(true);
        taskEstimatedTime.setEditable(true);
    }

    private void taskException() {
        if (taskID.getText().isEmpty() || taskID.getText().length() != 4) {
                    taskIDLabel.setVisible(true);
                    taskID.clear();
                    taskTruth = false;
            }

        if (respTeamMember.getSelectionModel().getSelectedItem() == null) {
            taskEmployee.setVisible(true);
            taskTruth = false;
        }
        if (taskHoursWorked.getText().isEmpty()) {
            taskHWorked.setVisible(true);
            taskHoursWorked.clear();
            taskTruth = false;
        }
        if (taskEstimatedTime.getText().isEmpty()) {
            taskETime.setVisible(true);
            taskEstimatedTime.clear();
            taskTruth = false;
        }
        if (!taskDeadlineDd.getText().isEmpty())
        {
            if (Integer.parseInt(taskDeadlineDd.getText()) < 1 || Integer.parseInt(taskDeadlineDd.getText()) >31){
                taskDeadline.setVisible(true);
                taskDeadlineDd.clear();
                taskTruth = false;
            }
        }

        if (taskDeadlineDd.getText().isEmpty()) {
            taskDeadline.setVisible(true);
            taskDeadlineDd.clear();
            taskTruth = false;
        }

        if (taskDeadlineMm.getText().isEmpty()) {
            taskDeadline.setVisible(true);
            taskDeadlineMm.clear();
            taskTruth = false;
        }
        if (!taskDeadlineMm.getText().isEmpty())
        {
            if(Integer.parseInt(taskDeadlineMm.getText())<1 || Integer.parseInt(taskDeadlineMm.getText())>12) {
                taskDeadline.setVisible(true);
                taskDeadlineMm.clear();
                taskTruth = false;
            }
        }
        if (!taskDeadlineYyyy.getText().isEmpty())
        {
            if(Integer.parseInt(taskDeadlineYyyy.getText())<2020){
                taskDeadline.setVisible(true);
                taskDeadlineYyyy.clear();
                taskTruth = false;
            }
        }
        if (taskDeadlineYyyy.getText().isEmpty())
        {
            taskDeadline.setVisible(true);
            taskDeadlineYyyy.clear();
            taskTruth = false;
        }

        if (taskName.getText().isEmpty())
        {
            taskNameLabel.setVisible(true);
            taskName.clear();
            taskTruth = false;
        }
        for (int i = 0; i < taskListView.getItems().size(); i++)
        {
            if (i != taskListView.getSelectionModel().getSelectedIndex()) {
                if (taskName.getText().equals(taskListView.getItems().get(i).getName())) {
                    taskNameLabel.setVisible(true);
                    taskName.clear();
                    taskTruth = false;
                }
                if (!taskID.getText().isEmpty())
                {
                    if (Integer.parseInt(taskID.getText()) == taskListView.getItems().get(i).getID())
                    {
                        taskIDLabel.setVisible(true);
                        taskID.clear();
                        taskTruth = false;
                    }
                }
            }
        }
        if (taskTruth == false)
        {
            unlockTask();
            taskSave.setDisable(false);
            tabPane.getSelectionModel().select(taskInfo);
        }
        if (!(taskName.getText().isEmpty() || taskID.getText().isEmpty() && respTeamMember.getSelectionModel().isEmpty()
                || taskEstimatedTime.getText().isEmpty() ||taskHoursWorked.getText().isEmpty() || taskDeadlineDd.getText().isEmpty()
        || taskDeadlineMm.getText().isEmpty() || taskDeadlineYyyy.getText().isEmpty()))
        {
            taskTruth = true;
            taskNameLabel.setVisible(false);
            taskIDLabel.setVisible(false);
            taskEmployee.setVisible(false);
            taskETime.setVisible(false);
            taskHWorked.setVisible(false);
            taskDeadline.setVisible(false);
        }
    }
    private void requirementException() {

        if (requirementID.getText().isEmpty()) {
            reqIDLabel.setVisible(true);
            requirementID.clear();
            reqTruth = false;
        }
        if (requirementID.getText().length() != 4) {
            reqIDLabel.setVisible(true);
            requirementID.clear();
            reqTruth = false;
        }
        if (requirementEstimatedTime.getText().isEmpty()) {
            reqETime.setVisible(true);
            requirementEstimatedTime.clear();
            reqTruth = false;
        }
        if (!requirementDeadlineDd.getText().isEmpty())
        {
            if (Integer.parseInt(requirementDeadlineDd.getText()) < 1 || Integer.parseInt(requirementDeadlineDd.getText()) >31){
                reqDeadline.setVisible(true);
                requirementDeadlineDd.clear();
                reqTruth = false;
            }
        }

        if (requirementDeadlineDd.getText().isEmpty()) {
            reqDeadline.setVisible(true);
            requirementDeadlineDd.clear();
            reqTruth = false;
        }

        if (requirementDeadlineMm.getText().isEmpty()) {
            reqDeadline.setVisible(true);
            requirementDeadlineMm.clear();
            reqTruth = false;
        }
        if (!requirementDeadlineMm.getText().isEmpty())
        {
            if(Integer.parseInt(requirementDeadlineMm.getText())<1 || Integer.parseInt(requirementDeadlineMm.getText())>12) {
                reqDeadline.setVisible(true);
                requirementDeadlineMm.clear();
                reqTruth = false;
            }
        }
        if (!requirementDeadlineYyyy.getText().isEmpty())
        {
            if(Integer.parseInt(requirementDeadlineYyyy.getText())<2020){
                reqDeadline.setVisible(true);
                requirementDeadlineYyyy.clear();
                reqTruth = false;
            }
        }
        if (requirementDeadlineYyyy.getText().isEmpty())
        {
            reqDeadline.setVisible(true);
            requirementDeadlineYyyy.clear();
            reqTruth = false;
        }
        if (requirementName.getText().isEmpty())
        {
            reqNameLabel.setVisible(true);
            requirementName.clear();
            reqTruth = false;
        }
        for (int i = 0; i < requirementListView.getItems().size(); i++)
        {
            if (i != requirementListView.getSelectionModel().getSelectedIndex())
            {
                if (requirementName.getText().equals(requirementListView.getItems().get(i).getName())) {
                    reqNameLabel.setVisible(true);
                    requirementName.clear();
                    reqTruth = false;
                }
                if (!requirementID.getText().isEmpty())
                {
                    if (Integer.parseInt(requirementID.getText()) == requirementListView.getItems().get(i).getID()) {
                        reqIDLabel.setVisible(true);
                        requirementID.clear();
                        reqTruth = false;
                    }
                }
            }
        }
        if (priority1.isSelected() == false && priority2.isSelected() == false && priority3.isSelected() == false) {
            reqPriority.setVisible(true);

        }
        if (reqTruth == false)
        {
            unlockRequirement();
            requirementSave.setDisable(false);
            tabPane.getSelectionModel().select(requirementsInfo);
        }
        if (!(requirementName.getText().isEmpty() || requirementID.getText().isEmpty() || requirementStatus.getSelectionModel().isEmpty()
                || requirementEstimatedTime.getText().isEmpty()  || requirementDeadlineDd.getText().isEmpty()
                || requirementDeadlineMm.getText().isEmpty() || requirementDeadlineYyyy.getText().isEmpty()))
        {
            reqTruth = true;
            reqNameLabel.setVisible(false);
            reqIDLabel.setVisible(false);
            reqPriority.setVisible(false);
            reqETime.setVisible(false);
            reqDeadline.setVisible(false);
        }
    }

    /**
     * This method is used to check the exceptions when the user is trying to save a project.
     * @return truth(boolean).
     */
    private void projectExceptions(){
        if (projectID.getText().isEmpty()) {
            projIDLabel.setVisible(true);
            projectID.clear();
            proTruth = false;
        }
        if (projectID.getText().length() != 4) {
            projIDLabel.setVisible(true);
            projectID.clear();
            proTruth = false;
        }
        if (projectEstimatedTime.getText().isEmpty()) {
            projETime.setVisible(true);
            projectEstimatedTime.clear();
            proTruth = false;
        }
        if (!projectDeadlineDd.getText().isEmpty())
        {
            if (Integer.parseInt(projectDeadlineDd.getText()) < 1 || Integer.parseInt(projectDeadlineDd.getText()) >31){
                projDeadline.setVisible(true);
                projectDeadlineDd.clear();
                proTruth = false;
            }
        }

        if (projectDeadlineDd.getText().isEmpty()) {
            projDeadline.setVisible(true);
            projectDeadlineDd.clear();
            proTruth = false;
        }

        if (projectDeadlineMm.getText().isEmpty()) {
            projDeadline.setVisible(true);
            projectDeadlineMm.clear();
            proTruth = false;
        }
        if (!projectDeadlineMm.getText().isEmpty())
        {
            if(Integer.parseInt(projectDeadlineMm.getText())<1 || Integer.parseInt(projectDeadlineMm.getText())>12) {
                projDeadline.setVisible(true);
                projectDeadlineMm.clear();
                proTruth = false;
            }
        }
        if (!projectDeadlineYyyy.getText().isEmpty())
        {
            if(Integer.parseInt(taskDeadlineYyyy.getText())<2020){
                projDeadline.setVisible(true);
                projectDeadlineYyyy.clear();
                proTruth = false;
            }
        }
        if (projectDeadlineYyyy.getText().isEmpty())
        {
            projDeadline.setVisible(true);
            projectDeadlineYyyy.clear();
            proTruth = false;
        }
        if (projectName.getText().isEmpty())
        {
            projNameLabel.setVisible(true);
            projectName.clear();
            proTruth = false;
        }
        for (int i = 0; i < projectListView.getItems().size(); i++)
        {
            if (i != projectListView.getSelectionModel().getSelectedIndex()) {
                if (projectName.getText().equals(projectListView.getItems().get(i).getName())) {
                    projNameLabel.setVisible(true);
                    projectName.clear();
                    proTruth = false;
                }
                if (!projectID.getText().isEmpty())
                {
                    if (Integer.parseInt(projectID.getText()) == projectListView.getItems().get(i).getID()) {
                        projIDLabel.setVisible(true);
                        projectID.clear();
                        proTruth = false;
                    }
                }
            }
        }
        if (productOwner.getText().isEmpty())
        {
            projProductOwner.setVisible(true);
            productOwner.clear();
            proTruth = false;
        }
        if (proTruth == false)
        {
            unlockProject();
            projectSave.setDisable(false);
            tabPane.getSelectionModel().select(projectInfo);
        }
        if (!(projectName.getText().isEmpty() || projectID.getText().isEmpty() || projectStatus.getSelectionModel().isEmpty()
                || projectEstimatedTime.getText().isEmpty()  || projectDeadlineDd.getText().isEmpty()
                || projectDeadlineMm.getText().isEmpty() || projectDeadlineYyyy.getText().isEmpty() || productOwner.getText().isEmpty()))
        {
            proTruth = true;
            projNameLabel.setVisible(false);
            projIDLabel.setVisible(false);
            projProductOwner.setVisible(false);
            projETime.setVisible(false);
            projDeadline.setVisible(false);
        }
    }


    /**
     * This method works when the menu item "About" is chosen in the "Help" menu.
     */
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the software");
        alert.setHeaderText("Color IT project management system version 1.0");
        alert.setContentText("SEP 1 program");
        alert.showAndWait();
    }

    /**
     * This method assignes the chosen priority for the requirement.
     */
    public void priority(){
    if (priority1.isSelected())
    {
        requirementPriorityInteger = 1;
    }
    if (priority2.isSelected())
    {
        requirementPriorityInteger = 2;
    }
    if (priority3.isSelected())
    {
        requirementPriorityInteger = 3;
    }
}
    public void numericOnly(){
    taskHoursWorked.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d{0,9}")) {
                taskHoursWorked.setText(oldValue);
            }
        }
    });
    taskID.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d{0,9}")) {
                taskID.setText(oldValue);
            }
        }
    });
    taskEstimatedTime.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d{0,9}")) {
                taskEstimatedTime.setText(oldValue);
            }
        }
    });
        taskDeadlineDd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    taskDeadlineDd.setText(oldValue);
                }
            }
        });
        taskDeadlineMm.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    taskDeadlineMm.setText(oldValue);
                }
            }
        });
        taskDeadlineYyyy.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    taskDeadlineYyyy.setText(oldValue);
                }
            }
        });
        requirementID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    requirementID.setText(oldValue);
                }
            }
        });
        requirementEstimatedTime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    requirementEstimatedTime.setText(oldValue);
                }
            }
        });
        requirementDeadlineDd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    requirementDeadlineDd.setText(oldValue);
                }
            }
        });
        requirementDeadlineMm.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    requirementDeadlineMm.setText(oldValue);
                }
            }
        });
        requirementDeadlineYyyy.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    requirementDeadlineYyyy.setText(oldValue);
                }
            }
        });
        projectID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    projectID.setText(oldValue);
                }
            }
        });
        projectEstimatedTime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    projectEstimatedTime.setText(oldValue);
                }
            }
        });
        projectDeadlineDd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    projectDeadlineDd.setText(oldValue);
                }
            }
        });
        projectDeadlineMm.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    projectDeadlineMm.setText(oldValue);
                }
            }
        });
        projectDeadlineYyyy.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}")) {
                    projectDeadlineYyyy.setText(oldValue);
                }
            }
        });
}
}
