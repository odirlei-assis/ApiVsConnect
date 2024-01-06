package com.senai.apivsconnect.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CotacaoDto(
        @NotNull UUID id_dev,

        @NotNull UUID id_servico,

        @NotNull BigDecimal valor
) {}
