package tests;

import DTO.ProjectDTO;
import DTO.TasksCreateDTO;
import DTO.UpdateTaskDTO;
import Utils.UtilsForTests;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specification.TaskCreationSpecification;
import specification.TaskDeleteSpecification;
import specification.TaskUpdateSpecification;
import java.io.IOException;
import java.util.Properties;

public class UpdateTaskTests {

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

    @DisplayName("Update summary test")
    @Test
    void updateSummaryTest() throws IOException {

        TasksCreateDTO task = new TasksCreateDTO(taskSummary, project, taskDescription);

        String taskID = TaskCreationSpecification.correctTaskCreationWithIDExtract(baseUrl, authToken, task);

        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO("*_*");

        TaskUpdateSpecification.updateTask(baseUrl, authToken, updateTaskDTO, taskID);

        TaskDeleteSpecification.deleteTask(baseUrl, authToken, taskID);
    }
}
