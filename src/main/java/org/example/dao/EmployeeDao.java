package org.example.dao;

import org.example.entities.Employee;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
public class EmployeeDao {
    public List<Employee> findAllEmployeesByDepartement(Long departementId) {
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM Employee e WHERE e.departement.id = :departementId";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("departementId", departementId);
            return query.getResultList();
        } finally {
            session.close();
        }
    }
    public void addEmployeeToDepartement(Employee employee) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Persister l'employé dans la base de données
            session.save(employee);

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

    public void deleteEmployee(Long employeeId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Récupérer l'employé à supprimer
            Employee employee = session.get(Employee.class, employeeId);

            if (employee != null) {
                // Supprimer l'employé de la base de données
                session.delete(employee);
            } else {
                System.out.println("Employee with id " + employeeId + " not found.");
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
