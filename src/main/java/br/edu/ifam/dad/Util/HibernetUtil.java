package br.edu.ifam.dad.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernetUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banco01PU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void fecharFabrica(){
        emf.close();
    }



}
