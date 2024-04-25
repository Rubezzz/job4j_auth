package ru.job4j.auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.job4j.auth.marker.Operation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Person {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null",
            groups = { Operation.OnUpdate.class, Operation.OnDelete.class })
    private Integer id;
    @NotBlank(message = "Login must be not empty")
    private String login;
    @Size(min = 6, message = "Password length must be more than 6 characters.")
    private String password;
}