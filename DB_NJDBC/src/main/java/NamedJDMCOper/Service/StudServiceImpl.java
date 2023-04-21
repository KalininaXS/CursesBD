package NamedJDMCOper.Service;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Transactional
@Service
public class StudServiceImpl implements StudService {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public StudServiceImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Double>  AvgMarkOnCurs(){
        return jdbc.query("select avg(MARK) from Stud GROUP BY CURSES_NAME", new StudentMapper());
    }

    private static class StudentMapper implements RowMapper<Double> {
        @Override
        public Double mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getDouble("avg(MARK)");
        }
    }
}
