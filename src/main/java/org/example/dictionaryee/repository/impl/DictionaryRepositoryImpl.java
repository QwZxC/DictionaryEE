package org.example.dictionaryee.repository.impl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.repository.api.DictionaryRepository;

import java.util.List;

@Stateless
public class DictionaryRepositoryImpl implements DictionaryRepository {

    @PersistenceContext(unitName = "PostgresDS")
    private EntityManager entityManager;

    @Override
    public List<Word> getWords() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Word> criteriaQuery = criteriaBuilder.createQuery(Word.class);
        Root<Word> wordRoot = criteriaQuery.from(Word.class);
        criteriaQuery.select(wordRoot);
        TypedQuery<Word> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
}
