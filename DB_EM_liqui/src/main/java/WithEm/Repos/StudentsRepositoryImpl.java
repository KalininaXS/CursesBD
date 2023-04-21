package WithEm.Repos;


import WithEm.EM.Stud;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class StudentsRepositoryImpl implements StudentsRepository {
    @PersistenceContext
    private final EntityManager em;

    public StudentsRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Transactional
    @Override
    public Stud save(Stud students){
        if (students.getID() <= 0) {
            em.persist(students);
            return students;
        } else {
            return em.merge(students);
        }
    }

    @Override
    public Optional<Stud> findById(long id) {
        return Optional.ofNullable(em.find(Stud.class, id));
    }

    @Override
    public List<Stud> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("student-courses-entity-graph");
        TypedQuery<Stud> query = em.createQuery("select s from Stud s join fetch s.ID_GROUP", Stud.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<Stud> findByName(String name) {
        TypedQuery<Stud> query = em.createQuery("select s " +
                        "from Stud s " +
                        "where s.FIO = :name",
                Stud.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateFIOById(long id, String name) {
        Query query = em.createQuery("update Stud s " +
                "set s.FIO = :name " +
                "where s.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Stud s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
