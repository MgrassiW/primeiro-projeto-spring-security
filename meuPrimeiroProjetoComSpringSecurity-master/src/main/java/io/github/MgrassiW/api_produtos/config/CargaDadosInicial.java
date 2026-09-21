package io.github.MgrassiW.api_produtos.config;

import io.github.MgrassiW.api_produtos.entity.Produto;
import io.github.MgrassiW.api_produtos.entity.ROLE;
import io.github.MgrassiW.api_produtos.entity.Usuario;
import io.github.MgrassiW.api_produtos.repository.ProdutoRepository;
import io.github.MgrassiW.api_produtos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;


@RequiredArgsConstructor
@Configuration
public class CargaDadosInicial implements CommandLineRunner {

    private final ProdutoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        //popula apenas se o banco de dados estiver vazio
        if(repository.count() == 0){
            Usuario admin = Usuario.builder()
                    .login("admin")
                    .senha(passwordEncoder.encode("123"))
                    .role(ROLE.admin)
                    .build();

            Usuario user = Usuario.builder()
                    .login("user")
                    .senha(passwordEncoder.encode("123"))
                    .role(ROLE.user)
                    .build();
            List<Produto> produtosIniciais = List.of(
                    Produto.builder()
                            .nome("Notebook Dell Inspiron")
                            .preco(new BigDecimal("4500.00"))
                            .ativo(true)
                            .build(),
                    Produto.builder()
                            .nome("Mouse Gamer")
                            .preco(new BigDecimal("150.00"))
                            .ativo(true)
                            .build(),
                    Produto.builder()
                            .nome("Teclado Mecânico")
                            .preco(new BigDecimal("350.00"))
                            .ativo(true)
                            .build(),
                    Produto.builder()
                            .nome("Monitor 29")
                            .preco(new BigDecimal("1250.00"))
                            .ativo(true)
                            .build(),
                    Produto.builder()
                            .nome("Fone de ouvido bluetooth (descontinuado)")
                            .preco(new BigDecimal("200.00"))
                            .ativo(false)
                            .build()
            );
            repository.saveAll(produtosIniciais);
            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
