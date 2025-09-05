package IntegracionBackFront.backfront.Services.Auth;

import IntegracionBackFront.backfront.Config.Argon2.Argon2Password;
import IntegracionBackFront.backfront.Entities.Users.UserEntity;
import IntegracionBackFront.backfront.Repositories.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository repo;

    public boolean LogIn(String email, String password){
        Argon2Password objHash = new Argon2Password();
        System.out.println(email);
        Optional<UserEntity> userLog = repo.findByCorreo(email).stream().findFirst();
        if(userLog.isPresent()){
            UserEntity user = userLog.get();
            String nombreTipoUsuario = user.getTipoUsuario().getNombreTipo();
            System.out.println("Usuario con ese id encontrado"+ user.getId()+ " Email" + user.getCorreo() + " Rol" + nombreTipoUsuario);
            return objHash.VerigyPassword(user.getContrasena(), password);
        }
        return false;
    }
    public Optional<UserEntity> getUser(String email){
        Optional<UserEntity> userOpt = repo.findByCorreo(email);

        return userOpt.isPresent() ? userOpt : null;
    }
}
