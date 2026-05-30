package br.com.api.lojainformatica.service;

import br.com.api.lojainformatica.dto.UsuarioDTO;
import br.com.api.lojainformatica.model.UsuarioEntity;
import br.com.api.lojainformatica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuarioDTO.getNome());
        usuarioEntity.setCpf(usuarioDTO.getCpf());

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

        UsuarioDTO usuarioDTOSalvo = new UsuarioDTO();

        usuarioDTOSalvo.setNome(usuarioSalvo.getNome());
        usuarioDTOSalvo.setCpf(usuarioSalvo.getCpf());

        return usuarioDTOSalvo;
    }
}
