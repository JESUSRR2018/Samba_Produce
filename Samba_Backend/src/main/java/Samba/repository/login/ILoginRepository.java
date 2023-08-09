package Samba.repository.login;

import Samba.commons.domains.entity.login.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILoginRepository extends JpaRepository<LoginEntity, Integer> {
    @Override
    Optional<LoginEntity> findById(Integer integer);
    List<LoginEntity> findByUsuarioEmail(String usuarioEmail);
}