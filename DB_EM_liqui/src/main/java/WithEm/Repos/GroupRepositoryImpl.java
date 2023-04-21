package WithEm.Repos;

import WithEm.EM.GroupCurs;
import WithEm.EM.Stud;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class GroupRepositoryImpl implements GroupsRepository {

    @PersistenceContext
    private final EntityManager em;

    public GroupRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public GroupCurs save(GroupCurs groupcurs) {
        if (groupcurs.getID() <= 0) {
            em.persist(groupcurs);
            return groupcurs;
        } else {
            return em.merge(groupcurs);
        }
    }

    @Override
    public Optional<GroupCurs> findById(long id) {
        return Optional.ofNullable(em.find(GroupCurs.class, id));
    }

    @Override
    public List<GroupCurs> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("student-group-entity-graph");
        TypedQuery<GroupCurs> query = em.createQuery("select g from GroupCurs g join fetch g.id", GroupCurs.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public List<GroupCurs> findByName(List<Stud> group) {
        TypedQuery<GroupCurs> query = em.createQuery("select s " +
                        "from GroupCurs s " +
                        "where s.GROUP_CURSES = :group",
                GroupCurs.class);
        query.setParameter("group", group);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, int group_curses) {
        Query query = em.createQuery("update GroupCurs g " +
                "set g.GROUP_CURSES = :group_curses " +
                "where g.id = :id");
        query.setParameter("group_curses", group_curses);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from GroupCurs g " +
                "where g.ID = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
