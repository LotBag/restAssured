package tests;

import DTO.TasksCreateDTO;
import DTO.UpdateTaskDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specification.TaskCreationSpecification;
import specification.TaskDeleteSpecification;
import specification.TaskUpdateSpecification;
import java.io.IOException;

public class UpdateTaskTests extends BaseTests {

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
