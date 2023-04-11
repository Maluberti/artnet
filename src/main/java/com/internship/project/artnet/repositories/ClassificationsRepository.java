package com.internship.project.artnet.repositories;

import com.internship.project.artnet.domain.Classifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationsRepository  extends JpaRepository<Classifications, Long> { }
