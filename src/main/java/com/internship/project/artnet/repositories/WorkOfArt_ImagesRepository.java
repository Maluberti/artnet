package com.internship.project.artnet.repositories;

import com.internship.project.artnet.domain.WorkOfArt_Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WorkOfArt_ImagesRepository extends CrudRepository<WorkOfArt_Images, Long> {
        WorkOfArt_Images findByTitle(String title);
}
