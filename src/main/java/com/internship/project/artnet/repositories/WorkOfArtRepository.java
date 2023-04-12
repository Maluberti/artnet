package com.internship.project.artnet.repositories;

import com.internship.project.artnet.domain.WorkOfArt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOfArtRepository extends JpaRepository<WorkOfArt, Long> {
}
