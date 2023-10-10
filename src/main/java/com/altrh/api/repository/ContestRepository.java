package com.altrh.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altrh.api.model.Contest;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Integer> {

    
} 