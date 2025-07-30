package tests;

import DTO.TasksCreateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specification.TaskCreationSpecification;
import specification.TaskDeleteSpecification;
import java.io.IOException;

public class DeleteTaskTest extends BaseTests {

    @DisplayName("Delete task test")
    @Test
    void deleteTaskTest() throws IOException {

        TasksCreateDTO task = new TasksCreateDTO(taskSummary, project, taskDescription);

        String taskID = TaskCreationSpecification.correctTaskCreationWithIDExtract(baseUrl, authToken, task);

        TaskDeleteSpecification.deleteTask(baseUrl, authToken, taskID);
    }
}
