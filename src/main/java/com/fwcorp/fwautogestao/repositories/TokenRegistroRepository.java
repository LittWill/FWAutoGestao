package com.fwcorp.fwautogestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fwcorp.fwautogestao.entities.TokenRegistro;

@Repository
public interface TokenRegistroRepository extends JpaRepository<TokenRegistro, String>{

}
