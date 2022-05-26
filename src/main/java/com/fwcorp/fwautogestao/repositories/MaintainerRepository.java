package com.fwcorp.fwautogestao.repositories;

import com.fwcorp.fwautogestao.entities.Maintainer;
import com.fwcorp.fwautogestao.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintainerRepository extends JpaRepository<Maintainer, String>{
	Optional<Usuario> findByEmail(String email);
}
