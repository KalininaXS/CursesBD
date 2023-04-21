package WithEm.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Service
public class StudentsServiceImpl implements StudentsService{
    @PersistenceContext
    private final EntityManager em;

    public StudentsServiceImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Double> AvgMark(){
        TypedQuery<Double> query = em.createQuery("select avg(s.MARK) from Stud s GROUP BY s.CURSES_NAME", Double.class);
        return query.getResultList();
    }
}
