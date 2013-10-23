/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.idao.DogServiceDAO;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.entity.Service;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author martin
 */
public class DogServiceDaoImpl implements DogServiceDAO{
    
    private EntityManager em;
    
//    @Autowired
    EntityManagerFactory emf;
    
    public void setEmFromEmf() {
        ApplicationContext context =
    new ClassPathXmlApplicationContext("META-INF/context.xml");
        emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
        em = emf.createEntityManager();
    }
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public DogService createDogService(DogService dogService) {
        em.persist(dogService);
        return dogService;
    }

    @Override
    public void deleteDogService(DogService dogService) {
        em.remove(dogService);
    }
    
    @Override
    public DogService getDogServiceById(Long id) {
        TypedQuery<DogService> query = em.createQuery("select d from DogService d "
                + "where d.id = :id", DogService.class)
                .setParameter("id", id);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<DogService> getDogServiceByDog(Dog dog) {
        TypedQuery<DogService> query = em.createQuery("select d from DogService d "
                + "where d.dog = :dog", DogService.class)
                .setParameter("dog", dog);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public List<DogService> getDogServiceByService(Service service) {
        TypedQuery<DogService> query = em.createQuery("select d from DogService d "
                + "where d.service = :service", DogService.class)
                .setParameter("service", service);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public List<DogService> getDogServiceByDate(Date date) {
        TypedQuery<DogService> query = em.createQuery("select d from DogService d "
                + "where d.serviceDate = :date", DogService.class)
                .setParameter("date", date);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public List<DogService> getDogServiceByEmployee(Employee employee) {
        TypedQuery<DogService> query = em.createQuery("select d from DogService d "
                + "where d.servedBy = :employee", DogService.class)
                .setParameter("employee", employee.getId());
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }

    @Override
    public List<DogService> getAllDogServices() {
        TypedQuery<DogService> query = em.createQuery("select d from DogService d",
                DogService.class);
        if (query.getResultList() == null){
            throw new RuntimeException("Query returned null.");
        }
        if (query.getResultList().size() < 1) {
            throw new RuntimeException("No record found");        
        }
        return query.getResultList();
    }
    
}
