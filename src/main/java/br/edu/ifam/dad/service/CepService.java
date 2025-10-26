package br.edu.ifam.dad.service;

import br.edu.ifam.dad.Util.HibernetUtil;
import br.edu.ifam.dad.dao.CepCacheDAO;
import br.edu.ifam.dad.dao.CepOriginalDAO;
import br.edu.ifam.dad.entity.Cep;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public class CepService {

    private final CepCacheDAO cepCacheDAO;
    private final CepOriginalDAO cepOriginalDAO;


    public CepService() {
        this.cepCacheDAO = new CepCacheDAO();
        this.cepOriginalDAO = new CepOriginalDAO();

    }

    public Cep buscarEnderecoPorCep(String cep){

        EntityManager em = HibernetUtil.getEntityManager();
        String cepChave = formatarCepParaChave(cep);

        if(cepChave == null){
            throw new IllegalArgumentException("CEP inválido.");
        }

        EntityTransaction tx = null;
        Cep cepEndereco = null;
        boolean salvarNocache =false;

        try{
            tx = em.getTransaction();
            tx.begin();

            Optional<Cep> cepEnderecoCache = cepCacheDAO.getCep(em, cepChave);

            if(cepEnderecoCache.isPresent()){
                System.out.println("CACHE HIT: Retornando cache");

                cepEndereco = cepEnderecoCache.get();
            }else{
                System.out.println("CACHE MISS: Buscando no fonte original...");
                Optional<Cep> enderecoOriginal = cepOriginalDAO.findByCep(em, cepChave);

                if(enderecoOriginal.isPresent()){
                     cepEndereco = enderecoOriginal.get();
                     salvarNocache = true;
                }else{
                    cepEndereco = null;
                }
            }

            if(salvarNocache){
                System.out.println("SALVANDO NO CACHE: " + cepChave);

                cepCacheDAO.insertCep(em, cepEndereco);
            }

            tx.commit();
        }catch(Exception e){

            if(tx != null && tx.isActive()){
                tx.rollback();
            }

            throw new RuntimeException("Erro ao buscar CEP",e);
        }finally {
            if(em != null){
                em.close();
            }
        }
        return cepEndereco;
    }

    private String formatarCepParaChave(String cep) {
        if (cep == null) return null;

        String cepLimpo = cep.replaceAll("[^0-9]", "");
        if (cepLimpo.length() != 8) {
            return null;
        }
        return cepLimpo.substring(0, 5) + "-" + cepLimpo.substring(5);
    }



}
