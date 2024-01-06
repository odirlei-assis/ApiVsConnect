package com.senai.apivsconnect.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_cotacao")
public class CotacaoModel implements Serializable {

    @Serial
    private static final long serialVersinUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cotacao", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_dev")
    UsuarioModel desenvolvedor;

    @ManyToOne
    @JoinColumn(name = "id_servico")
    ServicoModel servico;

    private BigDecimal valor;
}