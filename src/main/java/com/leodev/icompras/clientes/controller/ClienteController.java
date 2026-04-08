package com.leodev.icompras.clientes.controller;

import com.leodev.icompras.clientes.model.Cliente;
import com.leodev.icompras.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes")
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Conflito - Cliente já cadastrado (e-mail ou CPF/CNPJ duplicado)")
    })
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
        service.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("{codigo}")
    @Operation(summary = "Obter Detalhes", description = "Retorna os dados do cliente pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado."),
    })
    public ResponseEntity<Cliente> obterDados(@PathVariable("codigo") Long codigo){
        return service
                .obterPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{codigo}")
    @Operation(summary = "Deletar", description = "Deleta o cliente pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente inexistente."),
    })
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo){
        var cliente = service.obterPorCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente inexistente"
                ));

        service.deletar(cliente);
        return ResponseEntity.noContent().build();
    }
}
