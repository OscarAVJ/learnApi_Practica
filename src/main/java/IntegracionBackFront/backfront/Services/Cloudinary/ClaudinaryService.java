package IntegracionBackFront.backfront.Services.Cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ClaudinaryService {
    /// Definir las imagenes en MB = 5
    private static final long maxFileSize = 5*1024*1024;
    /// Definir las extensiones definidas
    private static final String[] ALLOWED_EXTENTIONS = {".jpg", "jpeg", ".png"};
    /// Atributo de cloudinary
    private final Cloudinary cloudinary;

    public ClaudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    public String uploadImage(MultipartFile file) throws IOException {
        validateImage(file);
    }

    private void validateImage(MultipartFile file) {
        if(file.isEmpty()){
            throw  new IllegalArgumentException("El archivo esta vacio");
        }
        if (file.getSize() > maxFileSize){
            throw new IllegalArgumentException("El archivo excede el tamaño permitido")
        }

        String originalFileName = file.getOriginalFilename();
        if(originalFileName == null){
            throw new IllegalArgumentException("Nombre no valido");
        }

        String extention = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();

        if(!Arrays.asList(ALLOWED_EXTENTIONS).contains(extention)){
            throw new IllegalArgumentException("Solo se permiten archivos jpg, jpeg y png");
        }

        if(!file.getContentType().startsWith("image/")){
            throw new IllegalArgumentException("El archivo debe de tener una direccion valida");
        }

    }


}
