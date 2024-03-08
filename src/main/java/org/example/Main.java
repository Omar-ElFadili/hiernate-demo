package org.example;

import org.example.dao.DepartementDao;
import org.example.dao.EmployeeDao;
import org.example.entities.Departement;
import org.example.entities.Employee;
import org.example.utils.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            // Créer un département
            Departement departement = new Departement("Informatique", null);
            DepartementDao departementDao = new DepartementDao();
            departementDao.addDepartement(departement);

            // Créer un employé
            Employee employee = new Employee("omar", "el fadili", "omar.elfadili@gmail.com", departement);
            EmployeeDao employeeDao = new EmployeeDao();
            employeeDao.addEmployeeToDepartement(employee);

            // Afficher tous les employés d'un département spécifique
            List<Employee> employeesInDepartment = employeeDao.findAllEmployeesByDepartement(departement.getId());
            System.out.println("Employees in department " + departement.getNom() + ":");
            for (Employee emp : employeesInDepartment) {
                System.out.println(emp.getNom() + " " + emp.getPrenom());
            }

            // Supprimer un employé
            employeeDao.deleteEmployee(employee.getId());

            // Afficher tous les départements auxquels appartient un employé spécifique
            List<Departement> departmentsForEmployee = departementDao.findDepartementsByEmployee(employee.getId());
            System.out.println("Departments for employee " + employee.getNom() + " " + employee.getPrenom() + ":");
            for (Departement dep : departmentsForEmployee) {
                System.out.println(dep.getNom());
            }

            // Supprimer un département
            departementDao.deleteDepartement(departement.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }
}