package com.altrh.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.altrh.api.model.Game;


@Repository
/** L'interface de CrudRepository va permettre de créer un objet qui aura accès à des méthodes correspondant
 * à touts les requêtes de base d'un CRUD (INSERT, UPDATE, DELETE, SELECT).
 *  Dans la déclaration de CrudRepository, on doit préciser la classe Entity pour laquelle on veut 
 * faire du crud et le type de la clé primaire.
 */
public interface  GameRepository extends CrudRepository<Game, Integer> {
    
}