/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateexample;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Capuchino
 */
public class QueryExample {
    private static SessionFactory factory;
    private static ServiceRegistry registry;
    
        public static void main(String[] args) {
        try{
           // factory = new Configuration().configure().buildSessionFactory();
            Configuration configuration = new Configuration().configure();
            registry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(registry);
        }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
        
        //HQL Examples
        Session session = factory.openSession();
        //Transaction tx = null;
      
      try{
         //tx = session.beginTransaction();
		 //Query query = session.createQuery("select e.firstName from Employee as e");
                 //Query query = session.createQuery("from Employee as e where e.firstName like 'R%' and salary > 80000");
                 
                 //String hql = "from Employee where salary > :salary";
                 //String hql = "select max(e.salary) from Employee as e";
                 
                 //Query query = session.createQuery(hql);
                 //query.setInteger("salary", 80000);
                 //int s = (int)query.uniqueResult();
                 //System.out.println("Max salary is: " + s);
                 /*
		 List employees = query.list();
         for (Iterator iterator = 
                         employees.iterator(); iterator.hasNext();){
            
            //String fname = (String) iterator.next();
            //System.out.println("First Name: " + fname); 
            
            Employee ee = (Employee) iterator.next(); 
            System.out.print("First Name: " + ee.getFirstName()); 
            System.out.print("  Last Name: " + ee.getLastName()); 
            System.out.println("  Salary: " + ee.getSalary()); 
        }
         */
                 
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.gt("salary", 80000));
        criteria.addOrder(Order.asc("firstName"));
        List employees = criteria.list();
        
        for (Iterator iterator = employees.iterator(); iterator.hasNext();){

            Employee ee = (Employee) iterator.next(); 
            System.out.print("First Name: " + ee.getFirstName()); 
            System.out.print("  Last Name: " + ee.getLastName()); 
            System.out.println("  Salary: " + ee.getSalary()); 
        }
         
      }catch (HibernateException e) {
        // if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
        
    
        StandardServiceRegistryBuilder.destroy(registry);
    
    }
}
