package com.fwcorp.fwautogestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fwcorp.fwautogestao.entities.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{

}
