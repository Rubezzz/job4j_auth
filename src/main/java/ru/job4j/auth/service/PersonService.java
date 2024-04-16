package ru.job4j.auth.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    @NonNull
    private final PersonRepository repository;
    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    public Optional<Person> save(Person person) {
        try {
            repository.save(person);
            return Optional.of(person);
        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public void delete(Person person) {
        repository.delete(person);
    }
}
