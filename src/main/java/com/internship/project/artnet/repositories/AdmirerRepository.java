package com.internship.project.artnet.repositories;

import com.internship.project.artnet.domain.Admirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmirerRepository extends JpaRepository<Admirer, Long> {}
