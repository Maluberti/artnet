package com.internship.project.artnet.repositories;

import com.internship.project.artnet.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
