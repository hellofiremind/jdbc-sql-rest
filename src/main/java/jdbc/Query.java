package jdbc;

import config.ReadProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;

/**
 * Created by jamescross91 on 05/11/2015.
 */

public class Query {
    private static JdbcTemplate jdbcTemplate;

    public static <T> T execute(String sql, ResultSetExtractor<T> extractor) {
        if(jdbcTemplate == null) {
            jdbcTemplate = init();
        }

        return jdbcTemplate.query(sql, extractor);
    }

    private static JdbcTemplate init() {
        return new JdbcTemplate(getDs());
    }

    private static DataSource getDs(){
        String url = ReadProperties.getProperty("jdbcurl");
        String user = ReadProperties.getProperty("jdbcuser");
        String pass = ReadProperties.getProperty("jdbcpass");
        String driver = ReadProperties.getProperty("jdbcdriver");

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(pass);
        ds.setDriverClassName(driver);

        return ds;
    }
}
