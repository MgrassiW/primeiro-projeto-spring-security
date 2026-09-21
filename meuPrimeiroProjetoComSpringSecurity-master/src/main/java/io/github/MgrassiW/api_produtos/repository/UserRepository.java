package io.github.MgrassiW.api_produtos.repository;

import io.github.MgrassiW.api_produtos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Usuario , Long > {

    Optional<Usuario> findByLogin(String login);

    Boolean existsByLogin(String login);
}
