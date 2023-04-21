package WithEm.Repos;

import WithEm.EM.GroupCurs;
import WithEm.EM.Stud;
import java.util.List;
import java.util.Optional;

public interface GroupsRepository {

    GroupCurs save(GroupCurs groupCursOfCurses);

    //для поиска студента по его id
    Optional<GroupCurs> findById(long id);

    //для поиска всех студентов
    List<GroupCurs> findAll();

    //для поиска студента по его имени
    List<GroupCurs> findByName(List<Stud> groupcurs);

    //обновление имени студента по его id (как пример, может быть ситуация со сменой фамилии студентом по каким-либо причинам
    void updateNameById(long id, int group_curses);

    //удаление по id и возможно не только студента
    void deleteById(long id);

}
