package WithEm.Repos;

import WithEm.EM.Stud;

import java.util.Optional;
import java.util.List;

public interface StudentsRepository {
    Stud save(Stud stud);

    Optional<Stud> findById(long id);

    List<Stud> findAll();

    List<Stud> findByName(String FIO);

    void updateFIOById(long id, String FIO);

    void deleteById(long id);

}
