package IntegracionBackFront.backfront.Config.Argon2;

import com.sun.jna.Memory;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class Argon2Password {
    /// Cantidad de iteraciones a ejecutar
    private static final int ITERATIONS= 10;
    private static final int PARALELLISM = 2;
    private static final int MEMORY = 32768;

    /// Definimos la versiona usar de argon
    private Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public String EncryptPassword(String password) {
        return argon2.hash(ITERATIONS, MEMORY, PARALELLISM, password);
    }


    public boolean VerigyPassword(String hashDB, String password) {
        return argon2.verify(hashDB, password);
    }
}
