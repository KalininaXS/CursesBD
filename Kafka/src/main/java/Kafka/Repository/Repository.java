package Kafka.Repository;

import Kafka.util.Measurement;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Repository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public Measurement save(Measurement measurement){
        String sql = "insert into measurement.test (timestamp,source,value) " +
                "values (:timestamp,:source, :amount);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("timestamp",measurement.timestamp());
        parameterSource.addValue("source",measurement.source());
        parameterSource.addValue("value",measurement.value());
        namedParameterJdbcTemplate.update(sql, parameterSource);
        return measurement;
    }


    public List<Measurement> findAll(){
        List<Measurement> query =
                namedParameterJdbcTemplate.query("select * from measurement.test", new Repository.OrderMapper());
        return query;

    }


    public static class OrderMapper implements RowMapper<Measurement> {
        @Override
        public Measurement mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Measurement(
                    rs.getTimestamp("timestamp").toInstant(),
                    rs.getNString("source"),
                    rs.getDouble("value")
            );
        }
    }
}
