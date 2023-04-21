package NamedJDMCOper.DAOS;

import NamedJDMCOper.Entity.GroupCurs;

import java.util.List;

public interface GroupCursDAO {
    int count();

    void insert(GroupCurs groupOfCurses);

    GroupCurs getById(long id);

    List<GroupCurs> getAll();

    void deleteById(long id);
}
