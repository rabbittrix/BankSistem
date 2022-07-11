package conta.hml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({
        // adaptadores front-end javaFx
        "conta.hml",
        "conta.tela",
        // objetos de sistema
        "conta.sistema",
        // adapter real
        "conta.servicos.repositorio"
})
public class Build3 {
    // Buil 3: Adapter JavaFx -> Sistema <- Adapter DB Homologação

    @Bean
    public DataSource dataSource() {
        var builder = new EmbeddedDatabaseBuilder();
        var db = builder.setType(EmbeddedDatabaseType.HSQL.HSQL)
                .addScript("create-db.sql")
                .addScript("insert-hml.sql")
                .build();
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
