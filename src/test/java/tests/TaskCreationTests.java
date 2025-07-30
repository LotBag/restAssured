package tests;

import DTO.ProjectDTO;
import DTO.TasksCreateDTO;
import Utils.UtilsForTests;
import org.junit.jupiter.api.*;
import specification.TaskCreationSpecification;
import specification.TaskDeleteSpecification;
import java.io.IOException;
import java.util.Properties;

public class TaskCreationTests {

    private static String baseUrl;
    private static String authToken;
    private static String projectID;
    private static String taskSummary;
    private static String taskDescription;
    private static ProjectDTO project;

    @BeforeAll
    static void beforeAll() throws IOException {
        Properties prop = UtilsForTests.loadProperties();

        baseUrl = prop.getProperty("baseUrl");
        authToken = prop.getProperty("authToken");
        projectID = prop.getProperty("projectID");
        taskSummary = prop.getProperty("taskSummary");
        taskDescription = prop.getProperty("taskDescription");
        project = new ProjectDTO(projectID);

    }

    @DisplayName("Creation with all arg constructor")
    @Test
    void createTaskWithAllArgConstructorTest() {

        TasksCreateDTO task = new TasksCreateDTO(taskSummary, project, taskDescription);

        String taskID = TaskCreationSpecification.correctTaskCreationWithIDExtract(baseUrl, authToken, task);

        TaskDeleteSpecification.deleteTask(baseUrl, authToken, taskID);
    }

    @DisplayName("Creation with required only constructor")
    @Test
    void createTaskWithRequiredOnlyConstructorTest() {
        TasksCreateDTO task = new TasksCreateDTO(taskSummary, project);

        String taskID = TaskCreationSpecification.correctTaskCreationWithIDExtract(baseUrl, authToken, task);

        TaskDeleteSpecification.deleteTask(baseUrl, authToken, taskID);
    }

    @DisplayName("Create task with empty summary")
    @Test
    void createTaskWithEmptyFieldsTest() {
        TasksCreateDTO task = new TasksCreateDTO("", project);

        TaskCreationSpecification.inCorrectTaskCreationWithIDExtract(baseUrl, authToken, task);
    }



}
