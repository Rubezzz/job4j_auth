package ru.job4j.auth.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.dto.PersonPasswordDto;
import ru.job4j.auth.mapper.PersonMapper;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    @NonNull
    private final PersonRepository repository;
    @NonNull
    private BCryptPasswordEncoder encoder;
    @NonNull
    private PersonMapper personMapper;
    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    public Optional<Person> save(Person person) {
        try {
            person.setPassword(encoder.encode(person.getPassword()));
            repository.save(person);
            return Optional.of(person);
        } catch (DataIntegrityViolationException e) {
            logger.info(e.getMessage(), e);
        }
        return Optional.empty();
    }

    public boolean update(Person person) {
        if (repository.findById(person.getId()).isEmpty()) {
            return false;
        }
        repository.save(person);
        return true;
    }

    public boolean delete(int id) {
        if (repository.findById(id).isEmpty()) {
            return false;
        }
        Person person = new Person();
        person.setId(id);
        repository.delete(person);
        return true;
    }

    public boolean updatePassword(PersonPasswordDto personDto) {
        Optional<Person> personOptional = repository.findById(personDto.getId());
        if (personOptional.isEmpty()) {
            return false;
        }
        Person person = personMapper.getEntityFromDto(personDto);
        person.setLogin(personOptional.get().getLogin());
        person.setPassword(encoder.encode(person.getPassword()));
        repository.save(person);
        return true;
    }
}
