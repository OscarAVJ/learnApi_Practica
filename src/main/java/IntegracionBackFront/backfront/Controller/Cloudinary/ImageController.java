package IntegracionBackFront.backfront.Controller.Cloudinary;

import IntegracionBackFront.backfront.Services.Cloudinary.ClaudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private final ClaudinaryService cloudService;

    public ImageController(ClaudinaryService cloudService) {
        this.cloudService = cloudService;
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file){
        try{
            /// Llamamos al servicio y obtener la URL
            String imageUrl = cloudService.uploadImage(file);
            return ResponseEntity.ok(Map.of("message", "Imagen subida exitosamente", "url", imageUrl));

        }catch (IOException e){
            return ResponseEntity.internalServerError().body(Map.of("Error", "Error al subir la imagen"));

        }
    }

    @PostMapping("/upload-to-folder")
    public ResponseEntity<?> uploadImageToFolder(
            @RequestParam("image")MultipartFile file, @RequestParam String folder
    ){
        try{
            String imageUrl = cloudService.uploadImage(file, folder);
            return ResponseEntity.ok(Map.of("message", "Imagen subida", "url", imageUrl));

        }catch (IOException e){
            return ResponseEntity.internalServerError().body("Error al subir image");
        }
    }
}
