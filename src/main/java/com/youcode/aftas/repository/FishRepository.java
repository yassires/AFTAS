package com.youcode.aftas.repository;

import com.youcode.aftas.DTO.FishDto;
import com.youcode.aftas.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {


    Fish findByName(String name);

}
