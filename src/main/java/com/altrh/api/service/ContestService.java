package com.altrh.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altrh.api.model.Contest;
import com.altrh.api.repository.ContestRepository;

@Service
public class ContestService {
    @Autowired
    private ContestRepository contestRepository;

    /**
     * Récupérer toutes les parties
     */
    public List<Contest> getAllContests() {
        return (List<Contest>) contestRepository.findAll();
    }

    /**
     * Récupérer une partie à partir de son identifiant
     */
    public Contest getContest(int id) {
        Optional<Contest> optional = contestRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    /**
     * La méthode saveContest sera utilisée pour les inserts et les updates.
     * @param g Contest
     * @return Contest : retourne la partie insérée ou actualisée
     */
    public Contest saveContest(Contest g) {
        return contestRepository.save(g);
    }

    /**
     * Supprime une partie selon son id
     */
    public void deleteContest(int id) {
        contestRepository.deleteById(id);
    }

}