package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Skakaonica;

@Repository
public interface SkakaonicaRepository extends JpaRepository<Skakaonica, Long> {

}
