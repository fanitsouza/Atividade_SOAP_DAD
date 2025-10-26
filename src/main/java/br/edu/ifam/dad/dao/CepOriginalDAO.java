package br.edu.ifam.dad.dao;


import br.edu.ifam.dad.entity.Cep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.regex.Pattern;

public class CepOriginalDAO {

    private static final Pattern UF_PATTERN = Pattern.compile("^[a-zA-Z]{2}$");

    public Optional<Cep> findByCep(EntityManager em ,String cepComTraco){

        String cepLimpo = cepComTraco.replaceAll("[^0-9]", "");
        String cep5 = cepLimpo.substring(0, 5);
        String uf;

        try{
            String sqlQuery1 = "SELECT uf  FROM cep_log_index WHERE cep5 = :cep5";

            uf = (String) em.createNativeQuery(sqlQuery1).setParameter("cep5", cep5).getSingleResult();
        }catch(NoResultException e){
            return Optional.empty();
        }

        if(uf == null || !UF_PATTERN.matcher(uf).matches()){
            throw new RuntimeException("UF inválida retornada do banco: " + uf);

        }

        try{
            String sqlQuery2 = String.format("SELECT logradouro, bairro, cidade FROM %s WHERE cep = :cep", uf.toLowerCase());

            Object[] result = (Object[]) em.createNativeQuery(sqlQuery2)
                                        .setParameter("cep",cepComTraco)
                                        .getSingleResult();
            Cep cep = new Cep(cepComTraco, (String) result[0], (String) result[1], (String) result[2],uf );
            return Optional.of(cep);
        }catch(NoResultException e){
            return Optional.empty();
        }
    }
}
