package com.daos;

import com.beans.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepo extends CrudRepository<Sprint, Integer> {

}
