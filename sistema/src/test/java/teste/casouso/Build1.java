package teste.casouso;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        // objetos de sistema
        "conta.sistema",
        // adapter fake
        "conta.adaptador"
})
public class Build1 {
    // Buil 1: Adapter test -> Sistema <- Adapter Mock
}
