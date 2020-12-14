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
    private FileAdapter adapter2;
    private int requirementPriorityInteger;
    ProjectManagementSystem projectManagementSystem = new ProjectManagementSystem();

    public void initialize() {
        adapter = new FileAdapter("employees.bin");
        adapter2 = new FileAdapter("ProjectManagementSystem.bin");

        InfoRespMember();
        statusBox();
        taskStatus.getSelectionModel().select("Not started");
        requirementStatus.getSelectionModel().select("Not started");
        projectStatus.getSelectionModel().select("Not started");
    }

    public void handleActions(ActionEvent e) {

        if (e.getSource() == scrumMaster) {
            loadProjects();
            System.out.println("done");

            addProject.setVisible(false);
            saveToPMS.setVisible(true);
            deleteProject.setVisible(true);

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

            projectSave.setVisible(true);
            projectChange.setVisible(true);

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

            addRequirement.setVisible(true);
            deleteRequirement.setVisible(true);
            requirementSave.setVisible(true);
            requirementChange.setVisible(true);

            taskName.setEditable(false);
            taskID.setEditable(false);
            respTeamMember.setDisable(false);
            taskHoursWorked.setEditable(false);
            taskDeadlineDd.setEditable(false);
            taskDeadlineMm.setEditable(false);
            taskDeadlineYyyy.setEditable(false);
            taskStatus.setDisable(true);
            taskDescription.setEditable(false);
            taskEstimatedTime.setEditable(false);

            addTask.setVisible(true);
            deleteTask.setVisible(true);
            taskSave.setVisible(true);
            taskChange.setVisible(true);

            if (e.getSource() == projectChange)
            {
                projectName.setEditable(true);
                projectHoursWorked.setEditable(true);
                projectID.setEditable(true);
                productOwner.setEditable(true);
                projectEstimatedTime.setEditable(true);
                projectStatus.setDisable(true);
                projectDeadlineDd.setEditable(true);
                projectDeadlineMm.setEditable(true);
                projectDeadlineYyyy.setEditable(true);
                projectDescription.setEditable(true);
            }
            if (e.getSource() == requirementChange)
            {
                requirementName.setEditable(true);
                requirementID.setEditable(true);
                priority1.setDisable(false);
                priority2.setDisable(false);
                priority3.setDisable(false);
                requirementEstimatedTime.setEditable(true);
                requirementHoursWorked.setEditable(true);
                requirementStatus.setDisable(true);
                requirementDeadlineDd.setEditable(true);
                requirementDeadlineMm.setEditable(true);
                requirementDeadlineYyyy.setEditable(true);
                requirementDescription.setEditable(true);
            }
            if (e.getSource() == taskChange)
            {
                taskName.setEditable(true);
                taskID.setEditable(true);
                respTeamMember.setDisable(true);
                taskHoursWorked.setEditable(true);
                taskDeadlineDd.setEditable(true);
                taskDeadlineMm.setEditable(true);
                taskDeadlineYyyy.setEditable(true);
                taskStatus.setDisable(false);
                taskDescription.setEditable(true);
                taskEstimatedTime.setEditable(true);
            }
            if (e.getSource() == projectSave)
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
                projectListView.getItems().set(projectListView.getEditingIndex(), project);
                for (int i = 0; i < requirementListView.getItems().size(); i++) {
                    project.addRequirement(requirementListView.getItems().get(i));
                }
                String projectHoursWorkedString = project.getHoursWorked() + "";
                projectHoursWorked.setText(projectHoursWorkedString);

                clearRequirement();
                tabPane.getSelectionModel().select(projects);

            }
            if (e.getSource() == requirementSave)
            {
                Requirement requirement = new Requirement(requirementName.getText(), Integer.parseInt(requirementID.getText()),
                        requirementDescription.getText(), Integer.parseInt(requirementEstimatedTime.getText()), requirementPriorityInteger,
                        new Deadline(Integer.parseInt(requirementDeadlineDd.getText()), Integer.parseInt(requirementDeadlineMm.getText()),
                                Integer.parseInt(requirementDeadlineYyyy.getText())), requirementStatus.getSelectionModel().getSelectedItem().toString());
                for (int i = 0; i < taskListView.getItems().size(); i++) {
                    requirement.addTask(taskListView.getItems().get(i));
                }
                requirementListView.getItems().set(requirementListView.getEditingIndex(), requirement);
                String requirementHoursWorkedString = requirement.hoursWorkedOnRequirement() + "";
                requirementHoursWorked.setText(requirementHoursWorkedString);
                tabPane.getSelectionModel().select(requirements);
                clearTask();
            }
            if (e.getSource() == taskSave)
            {
                Deadline deadline = new Deadline(Integer.parseInt(taskDeadlineDd.getText()),
                        Integer.parseInt(taskDeadlineMm.getText()), Integer.parseInt(taskDeadlineYyyy.getText()));

                Task task = new Task(taskName.getText(), Integer.parseInt(taskID.getText()),
                        taskDescription.getText(), Integer.parseInt(taskEstimatedTime.getText()),
                        taskStatus.getSelectionModel().getSelectedItem(), Integer.parseInt(taskHoursWorked.getText()),
                        deadline, (Employee) respTeamMember.getSelectionModel().getSelectedItem());

                taskListView.getItems().set(taskListView.getEditingIndex(), task);

                tabPane.getSelectionModel().select(tasks);
            }
        }
        if (e.getSource() == projectCreator) {
            //loadProjects();

            addProject.setVisible(true);
            saveToPMS.setVisible(true);
            deleteProject.setVisible(false);
            addProject.setDisable(false);


            projectName.setEditable(true);
            projectID.setEditable(true);
            productOwner.setEditable(true);
            projectEstimatedTime.setEditable(true);
            projectStatus.setDisable(false);
            projectDeadlineDd.setEditable(true);
            projectDeadlineMm.setEditable(true);
            projectDeadlineYyyy.setEditable(true);
            projectDescription.setEditable(true);

            projectSave.setVisible(true);
            projectChange.setVisible(false);

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

            addRequirement.setVisible(true);
            deleteRequirement.setVisible(true);
            requirementSave.setVisible(true);
            requirementChange.setVisible(false);

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

            addTask.setVisible(true);
            deleteTask.setVisible(true);
            taskSave.setVisible(true);
            taskChange.setVisible(false);

            if (e.getSource() == addProject) {
                clearProject();
                tabPane.getSelectionModel().select(projectInfo);
                projectInfo.setDisable(false);
                projectChange.setVisible(false);
                requirements.setDisable(false);
            }
            if (e.getSource() == addRequirement) {
                clearRequirement();
                tabPane.getSelectionModel().select(requirementsInfo);
                requirementsInfo.setDisable(false);
                requirementChange.setVisible(false);
                tasks.setDisable(false);
            }
            if (e.getSource() == addTask) {
                clearTask();
                tabPane.getSelectionModel().select(taskInfo);
                taskInfo.setDisable(false);
                taskChange.setVisible(false);
                taskSave.setDisable(false);

            }
            if (e.getSource() == taskSave) {
                Deadline deadline = new Deadline(Integer.parseInt(taskDeadlineDd.getText()),
                        Integer.parseInt(taskDeadlineMm.getText()), Integer.parseInt(taskDeadlineYyyy.getText()));

                Task task = new Task(taskName.getText(), Integer.parseInt(taskID.getText()),
                        taskDescription.getText(), Integer.parseInt(taskEstimatedTime.getText()),
                        taskStatus.getSelectionModel().getSelectedItem(), Integer.parseInt(taskHoursWorked.getText()),
                        deadline, (Employee) respTeamMember.getSelectionModel().getSelectedItem());

                taskListView.getItems().add(task);

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
        }
            if (teamMember.isSelected()) {
                addProject.setVisible(false);
                saveToPMS.setVisible(false);
                deleteProject.setVisible(false);

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

                projectSave.setVisible(false);
                projectChange.setVisible(false);

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

                addRequirement.setVisible(false);
                deleteRequirement.setVisible(false);
                requirementSave.setVisible(false);
                requirementChange.setVisible(false);

                taskName.setEditable(false);
                taskID.setEditable(false);
                respTeamMember.setDisable(false);
                taskHoursWorked.setEditable(false);
                taskDeadlineDd.setEditable(false);
                taskDeadlineMm.setEditable(false);
                taskDeadlineYyyy.setEditable(false);
                taskStatus.setDisable(true);
                taskDescription.setEditable(false);
                taskEstimatedTime.setEditable(false);

                addTask.setVisible(false);
                deleteTask.setVisible(false);
                taskSave.setVisible(false);
                taskChange.setVisible(false);
            }
            taskListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Task> task, Task old_task, Task new_task) -> {
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
                requirementListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Requirement> ov, Requirement old_requirement, Requirement new_requirement) -> {
                    tabPane.getSelectionModel().select(requirementsInfo);
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

                    taskListView.getItems().clear();
                    for (int i = 0; i < selectedRequirement.getTask().size(); i++) {
                        taskListView.getItems().add(selectedRequirement.getTask().get(i));
                    }
                    clearTask();
            });
        projectListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Project> project, Project old_project, Project new_project) -> {
            tabPane.getSelectionModel().select(projectInfo);
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

            requirementListView.getItems().clear();
            for (int i = 0; i < selectedProject.getRequirements().size(); i++) {
                requirementListView.getItems().add(selectedProject.getRequirements().get(i));
            }
            clearTask();
            clearRequirement();
        });
        }

        public void loadProjects(){
            projectManagementSystem = adapter2.loadPMS();
            System.out.println(projectManagementSystem.getProjects().size());

                projectListView.getItems().addAll(projectManagementSystem.getProjects());

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
