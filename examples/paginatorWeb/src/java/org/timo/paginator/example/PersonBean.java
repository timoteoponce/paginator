/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.timo.paginator.example;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.timo.paginator.ListProvider;
import org.timo.paginator.Paginator;
import org.timo.paginator.Range;
import org.timo.paginator.RangeProvider;

/**
 *
 * @author timoteo
 */
@ManagedBean(name="personBean")
@RequestScoped
public class PersonBean implements ListProvider<Person>{


    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    private final Paginator<Person> paginator = new Paginator<Person>(this, 5);

    /** Creates a new instance of PersonBean */
    public PersonBean() {
    }

    public void createPerson(){
        Person person = new Person();
        person.setName(System.currentTimeMillis() + "-name");
        try {
            utx.begin();
            em.persist(person);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(),e);
        }
    }

    public List<Person> provideList(RangeProvider rangeProvider) {
        Query query = em.createQuery("SELECT COUNT(p) FROM Person p");
        int totalSize = ((Number)query.getSingleResult()).intValue();
        Range range = rangeProvider.getRange(totalSize);
        query = em.createQuery("SELECT p FROM Person p ORDER BY p.id ASC");
        query.setFirstResult(range.getFromIndex());
        query.setMaxResults(range.getToIndex() - range.getFromIndex());
        return query.getResultList();
    }

    public Paginator<Person> getPaginator() {
        return paginator;
    }

    

}
