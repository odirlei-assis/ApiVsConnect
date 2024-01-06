package com.senai.apivsconnect.controllers;

import com.senai.apivsconnect.dtos.CotacaoDto;
import com.senai.apivsconnect.models.CotacaoModel;
import com.senai.apivsconnect.models.ServicoModel;
import com.senai.apivsconnect.models.UsuarioModel;
import com.senai.apivsconnect.repositories.CotacaoRepository;
import com.senai.apivsconnect.repositories.ServicoRepository;
import com.senai.apivsconnect.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cotacao", produces = {"application/json"})
public class CotacaoController {

    @Autowired
    CotacaoRepository cotacaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<List<CotacaoModel>> listarCotacao() {
        return ResponseEntity.status(HttpStatus.OK).body(cotacaoRepository.findAll());
    }

    @GetMapping("/{idCotacao}")
    public ResponseEntity<Object> exibirCotacaoId(@PathVariable(value = "idCotacao") UUID id) {
        Optional<CotacaoModel> cotacaoBuscada = cotacaoRepository.findById(id);

        if (cotacaoBuscada.isEmpty()) {
            // Retornar servico não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cotação não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cotacaoBuscada.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarCotacao(@RequestBody @Valid CotacaoDto cotacaoDto) {
        CotacaoModel cotacaoModel = new CotacaoModel();

        BeanUtils.copyProperties(cotacaoDto, cotacaoModel);

        Optional<UsuarioModel> dev = usuarioRepository.findById(cotacaoDto.id_dev());

        if (dev.isPresent()) {
            Optional<ServicoModel> servico = servicoRepository.findById(cotacaoDto.id_servico());

            if (servico.isPresent()) {

                cotacaoModel.setDesenvolvedor(dev.get());
                cotacaoModel.setServico(servico.get());

            } else {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id do serviço não encontrado");
            }
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id do cliente não encontrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(cotacaoRepository.save(cotacaoModel));
    }
}
