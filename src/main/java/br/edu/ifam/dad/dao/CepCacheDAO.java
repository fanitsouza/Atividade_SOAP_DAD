package br.edu.ifam.dad.dao;


import br.edu.ifam.dad.entity.Cep;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CepCacheDAO {

    public Optional<Cep> getCep(EntityManager em, String cep) {

        Cep cepObject = em.find(Cep.class, cep);
        return Optional.ofNullable(cepObject);
    }

    public void insertCep(EntityManager em ,Cep cep) {

        em.merge(cep);
    }

}
