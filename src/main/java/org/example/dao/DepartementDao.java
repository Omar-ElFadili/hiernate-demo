package org.example.dao;


import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.entities.Departement;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DepartementDao {
    public void addDepartement(Departement departement) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Persister le département dans la base de données
            session.save(departement);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public List<Departement> findDepartementsByEmployee(Long employeeId) {
        Session session = HibernateUtil.getSession();
        try {
            // Créer une requête HQL pour sélectionner tous les départements d'un employé spécifique
            String hql = "SELECT e.departement FROM Employee e WHERE e.id = :employeeId";
            Query<Departement> query = session.createQuery(hql, Departement.class);
            query.setParameter("employeeId", employeeId);
            return query.getResultList();
        } finally {
            session.close();
        }
    }
    public void deleteDepartement(Long departementId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Récupération de département à supprimer
            Departement departement = session.get(Departement.class, departementId);

            if (departement != null) {
                // Suppression de département de la base de données
                session.delete(departement);
            } else {
                System.out.println("Departement with id " + departementId + " not found.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
