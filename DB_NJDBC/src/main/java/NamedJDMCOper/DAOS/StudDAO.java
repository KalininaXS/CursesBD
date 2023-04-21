package NamedJDMCOper.DAOS;

import NamedJDMCOper.Entity.Stud;

import java.util.List;

public interface StudDAO {
    int count();

    void insert(Stud students);

    Stud getById(long id);

    List<Stud> getAll();

    void deleteById(long id);
}