package br.com.itau.catapi.repositories;

import br.com.itau.catapi.beans.Raca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacasRepository extends JpaRepository<Raca, String> {
}
