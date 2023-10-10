package com.altrh.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altrh.api.model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{
    
}