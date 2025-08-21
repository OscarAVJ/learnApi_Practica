package IntegracionBackFront.backfront.Config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

///Esta anotacion nos permite verificar que todas las claves esten correctas
@Configuration
public class CloudinaryConfig {

    /// Bean sirve para que le demos acceso a la manipulacion de lo que hagamos en este metodo directamente
    /// a spring
    @Bean
    public Cloudinary cloudinary(){
        /// Cargamos todas las variables del .env con el Dotenv.load();
        Dotenv dotenv = Dotenv.load();
       /// Creamos un map para la configuracion de claudinary
        Map<String, String> config = new HashMap<>();
        /// la clave de cada uno tiene que ser SI O SI EL que esta, y pues
        ///  ya la parte del .get es el que tengamos en el .env
        config.put("cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"));
        config.put("api_key", dotenv.get("CLOUDINARY_API_KEY"));
        config.put("api_secret", dotenv.get("CLOUDNINARY_API_SECRET"));

        /// Retornamos una nueva instancia de Claudinary con la configuracion cargada
        return new Cloudinary(config);
    }
}
