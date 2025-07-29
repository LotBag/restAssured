package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TasksCreateDTO {

    private final String summary;
    private final ProjectDTO project;
    private String description;
}
