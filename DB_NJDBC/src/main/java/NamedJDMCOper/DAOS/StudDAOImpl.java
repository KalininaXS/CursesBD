package NamedJDMCOper.DAOS;

import NamedJDMCOper.DAOS.StudDAO;
import NamedJDMCOper.Entity.Stud;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class StudDAOImpl implements StudDAO {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public StudDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }


    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from Stud", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Stud students) {
        namedParameterJdbcOperations.update("insert into Stud (ID, FIO, ID_GROUP, CURSES_NAME, MARK) values (:ID, :FIO, :ID_GROUP, :CURSES_NAME, :MARK)",
                Map.of("ID", students.getID(), "FIO", students.getFIO(), "ID_GROUP", students.getID_GROUP(), "CURSES_NAME", students.getCURSES_NAME(), "MARK", students.getMARK()));
    }

    @Override
    public Stud getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, FIO, ID_GROUP, CURSES_NAME, MARK from Stud where id = :id", params, new StudentMapper()
        );
    }

    @Override
    public List<Stud> getAll() {
        return jdbc.query("select ID, FIO, ID_GROUP, CURSES_NAME, MARK from Stud", new StudentMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from Stud where id = :id", params
        );
    }


    private static class StudentMapper implements RowMapper<Stud> {

        @Override
        public Stud mapRow(ResultSet resultSet, int i) throws SQLException {
            long ID = resultSet.getLong("ID");
            String FIO = resultSet.getString("FIO");
            int ID_GROUP = resultSet.getInt("ID_GROUP");
            String CURSES_NAME = resultSet.getString("CURSES_NAME");
            int MARK = resultSet.getInt("MARK");
            return new Stud(ID, FIO, ID_GROUP, CURSES_NAME, MARK);
        }
    }
}
