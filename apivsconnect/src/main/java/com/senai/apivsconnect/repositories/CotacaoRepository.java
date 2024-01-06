package com.senai.apivsconnect.repositories;

import com.senai.apivsconnect.models.CotacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CotacaoRepository extends JpaRepository<CotacaoModel, UUID> {
}