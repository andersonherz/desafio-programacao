package br.com.herz.desafio_luizalabs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.herz.desafio_luizalabs.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {

}
