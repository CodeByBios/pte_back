package com.sodifrance.pte.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sodifrance.pte.model.entity.Question;

/**
 * The Class AbstractService.<br>
 * Initialise ou met à jour la date de création et de modification lors d'une creation / modification.
 *
 * @param <T> the generic type
 */
public abstract class AbstractServiceImpl<T> {

	/**
     * Création d'une entité.
     *
     * @param entity Entité à créer
     * @return the entity
     */
    protected T createEntity(T entity) {
        return getEntityDao().save(entity);
    }

	/**
     * Mise à jour d'une entité
     *
     * @param entity Entité à mettre à jour
     * @return the entity
     */
    protected T updateEntity(T entity) {
        return getEntityDao().save(entity);
    }

    /**
     * Dao à utiliser
     *
     * @return Dao à utiliser
     */
    protected abstract JpaRepository<T, Long> getEntityDao();

}
