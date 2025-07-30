package tests;

import DTO.TasksCreateDTO;
import org.junit.jupiter.api.*;
import specification.TaskCreationSpecification;
import specification.TaskDeleteSpecification;

public class TaskCreationTests extends BaseTests {

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
