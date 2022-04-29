package com.bluesoft.todoapp.adapter;

import com.bluesoft.todoapp.model.TaskGroupRepository;
import com.bluesoft.todoapp.model.TaskGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository<TaskGroups,Integer> {

    @Override
    // HQL
    @Query("select distinct g from TaskGroups g join fetch g.tasks")
    List<TaskGroups> findAll();
}
