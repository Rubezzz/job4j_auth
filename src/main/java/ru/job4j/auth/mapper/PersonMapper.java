package ru.job4j.auth.mapper;

import org.mapstruct.Mapper;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.dto.PersonPasswordDto;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonPasswordDto getDtoFromEntity(Person person);

    Person getEntityFromDto(PersonPasswordDto passwordDto);
}
