package com.leodev.icompras.clientes.service;

import com.leodev.icompras.clientes.model.Cliente;
import com.leodev.icompras.clientes.repository.ClienteRepository;
import com.leodev.icompras.clientes.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente salvar(Cliente cliente){
        if (repository.existsByEmail(cliente.getEmail())){
            throw new ValidationException("Já existe um cliente cadastrado com este email");
        }

        if (repository.existsByCpf(cliente.getCpf())){
            throw new ValidationException("Já existe um cliente cadastrado com este CPF");
        }

        return repository.save(cliente);
    }

    public Optional<Cliente> obterPorCodigo(Long codigo){
        return repository.findById(codigo);
    }

    public void deletar(Cliente cliente) {
        cliente.setAtivo(false);
        repository.save(cliente);
    }
}
