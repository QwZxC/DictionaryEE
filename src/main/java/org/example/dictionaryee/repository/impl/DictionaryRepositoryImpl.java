package org.example.dictionaryee.repository.impl;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.dictionaryee.entity.DictionaryType;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.repository.api.DictionaryRepository;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class DictionaryRepositoryImpl implements DictionaryRepository {

    private static final String VALUE_PARAM = "value";
    private static final String TYPE_PARAM = "type";

    @PersistenceContext(unitName = "PostgresDS")
    private EntityManager entityManager;

    @Override
    public List<Word> findWords(DictionaryType type) {
        return entityManager.createQuery("SELECT w FROM word w WHERE w.dictionaryType = :type")
                .setParameter(TYPE_PARAM, type)
                .getResultList();
    }

    @Override
    public Word findTranslation(Word word) {
        return (Word) entityManager.createQuery("SELECT w FROM word w WHERE w.value = :value")
                .setParameter(VALUE_PARAM, word.getValue())
                .getResultList().stream().findFirst().orElseThrow();
    }

    @Override
    public List<Word> findAllWordsByCreationDate(LocalDate date) {
        return entityManager.createQuery("SELECT w FROM word w WHERE DATE(w.creationDate) = :date")
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public Word findWordByValue(String value) {
        return (Word) entityManager.createQuery("SELECT w FROM word w WHERE w.value = :value")
                .setParameter(VALUE_PARAM, value)
                .getResultList().stream().findFirst().orElseThrow();
    }

    @Override
    @Transactional
    public void createWord(Word word) {
        entityManager.persist(word);
    }

    @Override
    public void updateWord(Word word) {
        getUuid(word);
        entityManager.merge(word);
    }

    @Override
    @Transactional
    public void deleteWord(Word word) {
        getUuid(word);
        entityManager.remove(entityManager.find(Word.class, word.getId()));
    }

    private void getUuid(Word word) {
        word.setId(entityManager.createQuery("SELECT w.id FROM word w WHERE w.value = :value AND w.dictionaryType = :type", Long.class)
                .setParameter(VALUE_PARAM, word.getValue())
                .setParameter(TYPE_PARAM, word.getDictionaryType())
                .getResultList()
                .stream().findFirst().orElseThrow());
    }
}
