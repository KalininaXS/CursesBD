package NamedJDMCOper.DAOS;

import NamedJDMCOper.Entity.GroupCurs;
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
public class GroupCursDAOImpl implements GroupCursDAO {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GroupCursDAOImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from GroupCurs", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(GroupCurs groupCurs) {
        namedParameterJdbcOperations.update("insert into GroupCurs (ID, YEAR_OF_ENTER) values (:ID, :YEAR_OF_ENTER)",
                Map.of("ID", groupCurs.getID(), "YEAR_OF_ENTER", groupCurs.getYEAR_OF_ENTER()));
    }

    @Override
    public GroupCurs getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, GROUP_CURSES, YEAR_OF_ENTER from GroupCurs where id = :id", params, new GroupCursDAOImpl.GroupCursMapper()
        );
    }

    @Override
    public List<GroupCurs> getAll() {
        return jdbc.query("select ID, GROUP_CURSES, YEAR_OF_ENTER from GroupCurs", new GroupCursDAOImpl.GroupCursMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from GroupCurs where ID = :id", params
        );
    }

    private static class GroupCursMapper implements RowMapper<GroupCurs> {

        @Override
        public GroupCurs mapRow(ResultSet resultSet, int i) throws SQLException {
            long ID = resultSet.getLong("ID");
            List<Stud> GROUP_CURSES = resultSet.getObject("GROUP_CURSES", List.class);
            int YEAR_OF_ENTER = resultSet.getInt("YEAR_OF_ENTER");
            return new GroupCurs(ID, GROUP_CURSES, YEAR_OF_ENTER);
        }
    }
}
