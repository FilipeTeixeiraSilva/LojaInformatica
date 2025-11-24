package br.com.api.lojainformatica.service;

import br.com.api.lojainformatica.dto.ClienteDTO;
import br.com.api.lojainformatica.model.ClienteEntity;
import br.com.api.lojainformatica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteDTO.getNome());
        clienteEntity.setEmail(clienteDTO.getEmail());

        ClienteEntity clienteSalvo = clienteRepository.save(clienteEntity);

        ClienteDTO clienteDTOSalvo = new ClienteDTO();
        clienteDTOSalvo.setNome(clienteSalvo.getNome());
        clienteDTOSalvo.setEmail(clienteSalvo.getEmail());

        return clienteDTOSalvo;

    }

    public List<ClienteDTO> listarClientes() {
        List<ClienteEntity> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<>();
        for (ClienteEntity cliente : clientes) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setEmail(cliente.getEmail());
            clientesDTO.add(clienteDTO);
        }
        return clientesDTO;
    }

    public ClienteDTO buscarClientePorId(Long id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElse(null);
        if (clienteEntity == null) {
            return null;
        }
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(clienteEntity.getNome());
        clienteDTO.setEmail(clienteEntity.getEmail());
        return clienteDTO;
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElse(null);
        if (clienteEntity == null) {
            return null;
        }
        clienteEntity.setNome(clienteDTO.getNome());
        clienteEntity.setEmail(clienteDTO.getEmail());
        ClienteEntity clienteSalvo = clienteRepository.save(clienteEntity);
        ClienteDTO clienteDTOSalvo = new ClienteDTO();
        clienteDTOSalvo.setNome(clienteSalvo.getNome());
        clienteDTOSalvo.setEmail(clienteSalvo.getEmail());
        return clienteDTOSalvo;
    }
}
