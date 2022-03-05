package com.fwcorp.fwautogestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fwcorp.fwautogestao.entities.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, String>{

}
