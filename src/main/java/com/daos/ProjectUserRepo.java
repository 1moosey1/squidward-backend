package com.daos;

import com.beans.ProjectUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepo extends CrudRepository<ProjectUser, Integer> {

}

