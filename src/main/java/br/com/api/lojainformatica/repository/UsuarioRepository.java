package br.com.api.lojainformatica.repository;

import br.com.api.lojainformatica.dto.UsuarioDTO;
import br.com.api.lojainformatica.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
}
