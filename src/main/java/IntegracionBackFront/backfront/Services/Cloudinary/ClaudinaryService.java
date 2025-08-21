package IntegracionBackFront.backfront.Services.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Service
public class ClaudinaryService {
    /// Definir las imagenes en MB = 5
    private static final long maxFileSize = 5*1024*1024;
    /// Definir las extensiones permitidas
    private static final String[] ALLOWED_EXTENTIONS = {".jpg", "jpeg", ".png"};
    /// Cliente de laudinary como dependencia
    private final Cloudinary cloudinary;

    public ClaudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        validateImage(file);
        Map<?,?> uploadResoult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto", "quality", "auto:good"));
        return (String) uploadResoult.get("secure_url");
    }

    private void validateImage(MultipartFile file) {
        if(file.isEmpty()){
            throw  new IllegalArgumentException("El archivo esta vacio");
        }
        if (file.getSize() > maxFileSize) throw new IllegalArgumentException("El archivo excede el tamaño permitido");

        String originalFileName = file.getOriginalFilename();
        if(originalFileName == null) throw new IllegalArgumentException("Nombre no valido");

        String extention = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();

        if(!Arrays.asList(ALLOWED_EXTENTIONS).contains(extention)) throw new IllegalArgumentException("Solo se permiten archivos jpg, jpeg y png");

        if(!file.getContentType().startsWith("image/")) throw new IllegalArgumentException("El archivo debe de tener una direccion valida");

    }


}
