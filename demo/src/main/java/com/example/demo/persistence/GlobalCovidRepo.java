package com.example.demo.persistence;

import com.example.demo.persistence.entities.GlobalCovidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalCovidRepo extends JpaRepository<GlobalCovidEntity,Long> {
    //filler comment
}
