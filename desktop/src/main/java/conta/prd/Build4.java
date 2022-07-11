package conta.prd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({
        // adaptadores front-end javaFx
        "conta.prd",
        "conta.tela",
        // objetos de sistema
        "conta.sistema",
        // adapter real
        "conta.servicos.repositorio"
})
public class Build4 {
    // Buil 3: Adapter JavaFx -> Sistema <- Adapter DB Produção

    @Bean
    public DataSource dataSource() {
        var db = new SimpleDriverDataSource();
        db.setDriverClass(org.hsqldb.jdbcDriver.class);
        db.setUrl("jdbc:hsqldb:file:E:/Database/");
        db.setUsername("sa");
        db.setPassword("1234");
        return db;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
