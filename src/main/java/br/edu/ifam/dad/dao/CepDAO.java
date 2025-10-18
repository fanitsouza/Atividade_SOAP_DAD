package br.edu.ifam.dad.dao;


import br.edu.ifam.dad.entity.Cep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CepDAO {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public Cep buscarPorCep(String cep) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Cep resultado = session.createQuery("from Cep where cep = :cep", Cep.class)
                .setParameter("cep", cep)
                .uniqueResult();

        session.getTransaction().commit();
        session.close();
        return resultado;
    }
}
