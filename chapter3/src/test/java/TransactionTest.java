import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by heaven.zyc on 2016/3/31.
 */
public class TransactionTest {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init(){
        dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/heaven_ecs", "root", "111111");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void trans() {
//        DataSourceUtils.
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource);
        jdbcTemplate.query("select * from user ", new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                String name = resultSet.getString("name");
                System.out.println(name);
            }
        });
    }

}
