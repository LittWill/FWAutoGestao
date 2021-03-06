package com.fwcorp.fwautogestao.services;

import com.fwcorp.fwautogestao.entities.Cargo;
import com.fwcorp.fwautogestao.repositories.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public Cargo obterCargo(String nome){
        return this.cargoRepository.findById(nome).orElseThrow(() -> new RuntimeException("Cargo não encontrado!"));
    }

    public List<Cargo> listarCargos(){
        return this.cargoRepository.findAll();
    }
}
