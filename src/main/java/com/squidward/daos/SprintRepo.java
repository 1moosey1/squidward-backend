package com.squidward.daos;

import com.squidward.beans.Project;
import com.squidward.beans.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepo extends CrudRepository<Sprint, Integer> {
    //Get all Sprints
    List<Sprint> getSprintsByProject(int project_id);

    //Add a Project
    Sprint saveSprint(Sprint sprint);
}
