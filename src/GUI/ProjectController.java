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
    ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();

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
        });
    }



    public void handleActions(ActionEvent e) {

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
            }
            if (e.getSource() == requirementChange) {
                unlockRequirement();
                requirementSave.setDisable(false);
                requirementAddClicked = false;
            }
            if (e.getSource() == taskChange) {
                unlockTask();
                taskSave.setDisable(false);
                taskAddClicked = false;

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
                    project.addRequirement(requirementListView.getItems().get(i));
                }
                projectListView.getItems().set(projectListView.getSelectionModel().getSelectedIndex(),
                        project);
                String projectHoursWorkedString = project.getHoursWorked() + "";
                projectHoursWorked.setText(projectHoursWorkedString);

                lockProject();
                clearProject();
                clearRequirement();
                clearTask();
                saveToPMS.setDisable(false);
                projectInfo.setDisable(true);
                requirements.setDisable(true);
                requirementsInfo.setDisable(true);
                tasks.setDisable(true);
                taskInfo.setDisable(true);
                tabPane.getSelectionModel().select(projects);
            }
            if (e.getSource() == requirementSave) {
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
                    requirementListView.getItems().set(requirementListView.getSelectionModel().getSelectedIndex(), requirement);
                }

                String requirementHoursWorkedString = requirement.hoursWorkedOnRequirement() + "";
                requirementHoursWorked.setText(requirementHoursWorkedString);

                clearTask();
                clearRequirement();
                lockRequirement();
                taskListView.getItems().clear();
                tabPane.getSelectionModel().select(requirements);
                requirementSave.setDisable(true);
                requirementsInfo.setDisable(true);
                tasks.setDisable(true);
                taskInfo.setDisable(true);
            }
            if (e.getSource() == taskSave) {
                if (taskException() == true) {
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
                }
                taskSave.setDisable(true);
                taskChange.setDisable(true);

                clearTask();
                tabPane.getSelectionModel().select(tasks);
                taskInfo.setDisable(true);


            }
        }

        if (projectCreator.isSelected()) {
            addProject.setVisible(true);
            addProject.setDisable(false);
            deleteProject.setVisible(false);

            projectSave.setVisible(true);
            projectChange.setVisible(false);

            addRequirement.setVisible(true);
            requirementSave.setVisible(true);
            requirementChange.setVisible(false);

            addTask.setVisible(true);
            taskSave.setVisible(true);
            taskChange.setVisible(false);

            if (e.getSource() == addProject) {
                clearProject();
                clearRequirement();
                clearTask();
                unlockProject();
                requirementListView.getItems().clear();
                taskListView.getItems().clear();
                tabPane.getSelectionModel().select(projectInfo);
                projectInfo.setDisable(false);
                requirements.setDisable(false);
                requirementsInfo.setDisable(true);
                tasks.setDisable(true);
                taskInfo.setDisable(true);
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


            }
            if (e.getSource() == addTask) {
                clearTask();
                unlockTask();
                tabPane.getSelectionModel().select(taskInfo);
                taskInfo.setDisable(false);
                taskSave.setDisable(false);

            }
            if (e.getSource() == taskSave) {
                if(taskException()==true)
                {
                Deadline deadline = new Deadline(Integer.parseInt(taskDeadlineDd.getText()),
                        Integer.parseInt(taskDeadlineMm.getText()), Integer.parseInt(taskDeadlineYyyy.getText()));

                Task task = new Task(taskName.getText(), Integer.parseInt(taskID.getText()),
                    taskDescription.getText(), Integer.parseInt(taskEstimatedTime.getText()),
                    taskStatus.getSelectionModel().getSelectedItem(), Integer.parseInt(taskHoursWorked.getText()),
                    deadline, (Employee) respTeamMember.getSelectionModel().getSelectedItem());


                    taskListView.getItems().add(task);
                    tabPane.getSelectionModel().select(tasks);
                    requirementSave.setDisable(false);
                    taskInfo.setDisable(true);

                    taskNameLabel.setVisible(false);
                    taskIDLabel.setVisible(false);
                    taskEmployee.setVisible(false);
                    taskETime.setVisible(false);
                    taskHWorked.setVisible(false);
                    taskDeadline.setVisible(false);
                }

            }
            if (e.getSource() == requirementSave) {
                Requirement requirement = new Requirement(requirementName.getText(), Integer.parseInt(requirementID.getText()),
                        requirementDescription.getText(), Integer.parseInt(requirementEstimatedTime.getText()), requirementPriorityInteger,
                        new Deadline(Integer.parseInt(requirementDeadlineDd.getText()), Integer.parseInt(requirementDeadlineMm.getText()),
                                Integer.parseInt(requirementDeadlineYyyy.getText())), requirementStatus.getSelectionModel().getSelectedItem().toString());
                for (int i = 0; i < taskListView.getItems().size(); i++) {
                    requirement.addTask(taskListView.getItems().get(i));
                }
                String requirementHoursWorkedString = requirement.hoursWorkedOnRequirement() + "";
                if (requirementException()) {
                    requirementListView.getItems().add(requirement);
                    requirementHoursWorked.setText(requirementHoursWorkedString);
                    tabPane.getSelectionModel().select(requirements);
                    clearTask();
                    projectSave.setDisable(false);
                    requirementSave.setDisable(true);
                    requirementsInfo.setDisable(true);
                    tasks.setDisable(true);
                    taskInfo.setDisable(true);

                    reqNameLabel.setVisible(false);
                    reqIDLabel.setVisible(false);
                    reqPriority.setVisible(false);
                    reqETime.setVisible(false);
                    reqDeadline.setVisible(false);
                }


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
                String projectHoursWorkedString = project.getHoursWorked() + "";
                if (projectExceptions()) {
                    projectListView.getItems().add(project);
                    for (int i = 0; i < requirementListView.getItems().size(); i++) {
                        project.addRequirement(requirementListView.getItems().get(i));
                    }

                    projectHoursWorked.setText(projectHoursWorkedString);
                    saveToPMS.setVisible(true);
                    saveToPMS.setDisable(false);
                    clearRequirement();
                    projectInfo.setDisable(true);
                    requirements.setDisable(true);
                    requirementsInfo.setDisable(true);
                    tasks.setDisable(true);
                    taskInfo.setDisable(true);
                    tabPane.getSelectionModel().select(projects);

                    projNameLabel.setVisible(false);
                    projIDLabel.setVisible(false);
                    projETime.setVisible(false);
                    projDeadline.setVisible(false);
                    projProductOwner.setVisible(false);
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


        if (teamMember.isSelected()) {
            lockProject();
            lockRequirement();
            lockTask();

            addProject.setVisible(false);
            saveToPMS.setVisible(false);
            deleteProject.setVisible(false);

            projectSave.setVisible(false);
            projectChange.setVisible(false);

            addRequirement.setVisible(false);
            deleteRequirement.setVisible(false);
            requirementSave.setVisible(false);
            requirementChange.setVisible(false);

            addTask.setVisible(false);
            deleteTask.setVisible(false);
            taskSave.setVisible(false);
            taskChange.setVisible(false);
        }
        if (e.getSource() == saveToPMS) {

            projectManagementSystem.addProject(projectListView.getItems()
                    .get(projectListView.getItems().size() - 1));

            clearProject();
            adapter = new FileAdapter("ProjectManagementSystem.bin");
            adapter.saveToPMSFile(projectManagementSystem);
            System.out.println("Saved to file!");

            //Other actions

            //Other actions


            if (taskListView.getItems().isEmpty()) {
                requirementSave.setDisable(true);
            }
            if (requirementListView.getItems().isEmpty()) {
                projectSave.setDisable(true);
            }
        }
    }

        public void loadProjects()
        {
            projectManagementSystem = adapter.loadPMS();

                projectListView.getItems().addAll(projectManagementSystem.getProjects());

        }

        public void InfoRespMember ()
        {
            ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();
            projectManagementSystem.getEmployees().addAll(adapter.getAllEmployees());
            respTeamMember.getItems().clear();
            for (int i = 0; i < projectManagementSystem.getEmployees().size(); i++) {
                respTeamMember.getItems().add(projectManagementSystem.getEmployees().get(i));
            }
        }

        private void statusBox ()
        {
            String[] statuses = {"Started", "Not started", "Approved", "Rejected", "Ended"};
            taskStatus.getItems().addAll(statuses);
            requirementStatus.getItems().addAll(statuses);
            projectStatus.getItems().addAll(statuses);
        }




    //CLEAR, LOCK, UNLOCK methods

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
            projectDescription.clear();
            projectStatus.getSelectionModel().select("Not started");
        }
    private boolean taskException(){
        boolean truth = true;
        for (int i = 0; i < taskListView.getItems().size(); i++)
        {
            if(taskName.getText().equals(taskListView.getItems().get(i).getName())){
                taskNameLabel.setVisible(true);
                taskName.clear();
                truth = false;
            }
            if(Integer.parseInt(taskID.getText()) == taskListView.getItems().get(i).getID()){
                taskIDLabel.setVisible(true);
                taskID.clear();
                truth=false;
            }


        }
        if(Integer.parseInt(taskID.getText())<1000 || Integer.parseInt(taskID.getText())>9999){
            taskIDLabel.setVisible(true);
            taskID.clear();
            truth = false;
        }
        if(respTeamMember == null){
            taskEmployee.setVisible(true);
            truth = false;
        }
        if(Integer.parseInt(taskHoursWorked.getText())<0){
            taskHWorked.setVisible(true);
            taskHoursWorked.clear();
            truth = false;
        }
        if(Integer.parseInt(taskEstimatedTime.getText())<0){
            taskETime.setVisible(true);
            taskEstimatedTime.clear();
            truth = false;
        }
        if(Integer.parseInt(taskDeadlineDd.getText())<1 || Integer.parseInt(taskDeadlineDd.getText())>31){
            taskDeadline.setVisible(true);
            taskDeadlineDd.clear();
            truth = false;
        }

        if(Integer.parseInt(taskDeadlineMm.getText())<1 || Integer.parseInt(taskDeadlineMm.getText())>12){
            taskDeadline.setVisible(true);
            taskDeadlineMm.clear();
            truth = false;
        }

        if(Integer.parseInt(taskDeadlineYyyy.getText())<2020){
            taskDeadline.setVisible(true);
            taskDeadlineYyyy.clear();
            truth = false;
        }
        return truth;

    }
    private boolean requirementException(){
        boolean truth = true;
        for (int i = 0; i < requirementListView.getItems().size(); i++)
        {
            if(requirementName.getText().equals(requirementListView.getItems().get(i).getName())){
                reqNameLabel.setVisible(true);
                requirementName.clear();
                truth = false;
            }

            if(Integer.parseInt(requirementID.getText()) == requirementListView.getItems().get(i).getID()){
                reqIDLabel.setVisible(true);
                requirementID.clear();
                truth=false;
            }

        }
        if(Integer.parseInt(requirementID.getText())<1000 || Integer.parseInt(requirementID.getText())>9999){
            reqIDLabel.setVisible(true);
            requirementID.clear();
            truth = false;
        }

        if(priority1.isSelected()==false && priority2.isSelected()==false && priority3.isSelected()==false){
            reqPriority.setVisible(true);

        }
        if(Integer.parseInt(requirementEstimatedTime.getText())<0){
            reqETime.setVisible(true);
            requirementEstimatedTime.clear();
        }
        if(Integer.parseInt(requirementDeadlineDd.getText())<1 || Integer.parseInt(requirementDeadlineDd.getText())>31){
            reqDeadline.setVisible(true);
            requirementDeadlineDd.clear();
            truth = false;
        }

        if(Integer.parseInt(requirementDeadlineMm.getText())<1 || Integer.parseInt(requirementDeadlineMm.getText())>12){
            reqDeadline.setVisible(true);
            requirementDeadlineMm.clear();
            truth = false;
        }

        if(Integer.parseInt(requirementDeadlineYyyy.getText())<2020){
            reqDeadline.setVisible(true);
            requirementDeadlineYyyy.clear();
            truth = false;
        }
        return truth;
    }
    private boolean projectExceptions(){
        boolean truth = true;
        for (int i = 0; i < projectListView.getItems().size(); i++)
        {
            if(projectName.getText().equals(projectListView.getItems().get(i).getName())){
                projNameLabel.setVisible(true);
                projectName.clear();
                truth = false;
            }
            if(Integer.parseInt(projectID.getText()) == projectListView.getItems().get(i).getID()){
                projIDLabel.setVisible(true);
                projectID.clear();
                truth=false;
            }

        }
        if(Integer.parseInt(projectID.getText())<1000 || Integer.parseInt(projectID.getText())>9999){
            projIDLabel.setVisible(true);
            projectID.clear();
            truth = false;
        }

        if(!(productOwner.getText().contains(" "))){
            projProductOwner.setVisible(true);
            productOwner.clear();
            truth = false;
        }
        if(Integer.parseInt(projectEstimatedTime.getText())<0){
            projETime.setVisible(true);
            projectEstimatedTime.clear();
            truth = false;
        }
        if(Integer.parseInt(projectDeadlineDd.getText())<1 || Integer.parseInt(projectDeadlineDd.getText())>31){
            projDeadline.setVisible(true);
            projectDeadlineDd.clear();
            truth = false;
        }

        if(Integer.parseInt(projectDeadlineMm.getText())<1 || Integer.parseInt(projectDeadlineMm.getText())>12){
            projDeadline.setVisible(true);
            projectDeadlineMm.clear();
            truth = false;
        }

        if(Integer.parseInt(projectDeadlineYyyy.getText())<2020){
            projDeadline.setVisible(true);
            projectDeadlineYyyy.clear();
            truth = false;
        }
        return truth;
    }

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
}
