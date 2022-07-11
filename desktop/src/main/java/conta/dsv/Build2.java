package conta.dsv;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        // adaptadores front-end javaFx
        "conta.dsv",
        "conta.tela",
        // objetos de sistema
        "conta.sistema",
        // adapter fake
        "conta.adaptador"
})
public class Build2 {
    // Buil 2: Adapter JavaFx -> Sistema <- Adapter Mock
}
