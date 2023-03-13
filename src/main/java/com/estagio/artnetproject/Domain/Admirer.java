package com.estagio.artnetproject.Domain;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Admirer extends Users{
    private Boolean is_shark;

    @OneToMany( mappedBy = "admirer") //mappedby define quem eh a tabela pai -- nesse caso recipe eh a tabela pai e na relacao manytoone quem recebe a chave estrangeira eh ingredients que eh a tabela filho
    private Set<WorkOfArt> work = new HashSet<>();

}
