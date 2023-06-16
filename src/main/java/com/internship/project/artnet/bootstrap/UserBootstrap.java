package com.internship.project.artnet.bootstrap;
/*
import com.internship.project.artnet.domain.*;
import com.internship.project.artnet.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;


@Slf4j
@Component
public class UserBootstrap implements ApplicationRunner {private final ArtistRepository artistRepository;
    private final AdmirerRepository admirerRepository;
    private final ClassificationsRepository classificationsRepository;
    private final ExpositionRepository expositionRepository;
    private final StyleRepository styleRepository;
    private final WorkOfArtRepository workOfArtRepository;
    private final WorkOfArt_ImagesRepository workOfArtImagesRepository;

    public UserBootstrap( ArtistRepository artistRepository, AdmirerRepository admirerRepository, ClassificationsRepository classificationsRepository, ExpositionRepository expositionRepository, StyleRepository styleRepository, WorkOfArtRepository workOfArtRepository, WorkOfArt_ImagesRepository workOfArtImagesRepository) {
        this.artistRepository = artistRepository;
        this.admirerRepository = admirerRepository;
        this.classificationsRepository = classificationsRepository;
        this.expositionRepository = expositionRepository;
        this.styleRepository = styleRepository;
        this.workOfArtRepository = workOfArtRepository;
        this.workOfArtImagesRepository = workOfArtImagesRepository;
    }


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        getAdmirers();
        getArtists();
        getStyles();
        getClassifications();
        getExpositions();
        getWorkOfArtImages();
        getWorkOfArts();

        log.debug("Loading Bootstrap Data");

    }



    private void getAdmirers() throws IOException {
        List<Admirer> admirers = new ArrayList<>(2);
        Admirer admirer1 = new Admirer("user1", "user1.com","user1password", false, true, true);

        admirers.add(admirer1);

        Admirer admirer2 = new Admirer("user2", "user2.com","user2password", false, true, false);
        admirers.add(admirer2);

        admirerRepository.saveAll(admirers);
    }

    private void getArtists() throws IOException {
        List<Artist> artists = new ArrayList<>(2);
        Artist artist3 = new Artist("user3", "user3.com","user3password", true, false,33333, "user3Bio");

        artists.add(artist3);
        Artist artist4 = new Artist("user4", "user4.com","user4password", true, false, 44444, "user4Bio");

        artists.add(artist4);
        artistRepository.saveAll(artists);
    }

    private void getStyles() throws IOException {
        List<Style> styles = new ArrayList<>(2);
        Set<Artist> artist1 = new HashSet<>();
        artist1.add(artistRepository.findAll().get(0));
        Style style1 = new Style(null, "neoclassico", artist1);
        styles.add(style1);
        Set<Artist> artist2 = new HashSet<>();
        artist2.add(artistRepository.findAll().get(1));
        Style style2 = new Style(null, "classico", artist2);
        styles.add(style2);
        styleRepository.saveAll(styles);

    }


    private void getClassifications() throws IOException {
        List<Classifications> classifications = new ArrayList<>(3);
        Classifications classifications1 = new Classifications("Photography");
        classifications.add(classifications1);
        Classifications classifications2 = new Classifications("Digital drawing");
        classifications.add(classifications2);

        Classifications classifications3 = new Classifications("painting");

        classifications.add(classifications3);
        classificationsRepository.saveAll(classifications);
    }

    private void getExpositions() throws IOException {
        List<Exposition> expositions = new ArrayList<>(2);
        Exposition exposition1 = new Exposition("exp1","exp1Concept","exp1Insp", LocalDate.now().plusDays(2), LocalDate.now().plusDays(7), artistRepository.findAll().get(0));

        expositions.add(exposition1);

        Exposition exposition2 = new Exposition("exp2","exp2Concept","exp2Insp", LocalDate.now().plusDays(2), LocalDate.now().plusDays(7), artistRepository.findAll().get(1));

        expositions.add(exposition2);
        expositionRepository.saveAll(expositions);
    }



    private void getWorkOfArtImages() throws IOException {
        List<WorkOfArt_Images> workOfArtImages = new ArrayList<>(4);
        WorkOfArt_Images workOfArtImages1 = new WorkOfArt_Images( Files.readAllBytes(Paths.get("C:\\Users\\maria.barbosa\\Desktop\\Projetofinal\\artnet\\src\\main\\java\\com\\internship\\project\\artnet\\bootstrap\\images/menina.jpg")));

        workOfArtImages.add(workOfArtImages1);

        WorkOfArt_Images workOfArtImages2 = new WorkOfArt_Images( Files.readAllBytes(Paths.get("C:\\Users\\maria.barbosa\\Desktop\\Projetofinal\\artnet\\src\\main\\java\\com\\internship\\project\\artnet\\bootstrap\\images/monalisa.jpg")));

        workOfArtImages.add(workOfArtImages2);

        WorkOfArt_Images workOfArtImages3 = new WorkOfArt_Images( Files.readAllBytes(Paths.get("C:\\Users\\maria.barbosa\\Desktop\\Projetofinal\\artnet\\src\\main\\java\\com\\internship\\project\\artnet\\bootstrap\\images/noiteEstrelada.jpg")));

        workOfArtImages.add(workOfArtImages3);

        WorkOfArt_Images workOfArtImages4 = new WorkOfArt_Images( Files.readAllBytes(Paths.get("C:\\Users\\maria.barbosa\\Desktop\\Projetofinal\\artnet\\src\\main\\java\\com\\internship\\project\\artnet\\bootstrap\\images/ogrito.jpg")));

        workOfArtImages.add(workOfArtImages4);
        workOfArtImagesRepository.saveAll(workOfArtImages);

    }

    private void getWorkOfArts() throws IOException {
        List<WorkOfArt> workOfArts = new ArrayList<>(4);
        List<WorkOfArt_Images> image1 = new ArrayList<>();
        image1.add(workOfArtImagesRepository.findAll().get(0));
        WorkOfArt work1 = new WorkOfArt(1L, "work1","work1Concep",(Double) 1000.0, classificationsRepository.findAll().get(2), expositionRepository.findAll().get(0));
        workOfArts.add(work1);
        List<WorkOfArt_Images> image2 = new ArrayList<>();
        image2.add(workOfArtImagesRepository.findAll().get(1));
        WorkOfArt work2 = new WorkOfArt(2L, "work2","work2Concep",(Double) 1000.0, classificationsRepository.findAll().get(2), expositionRepository.findAll().get(0));
        workOfArts.add(work2);
        List<WorkOfArt_Images> image3 = new ArrayList<>();
        image3.add(workOfArtImagesRepository.findAll().get(2));
        WorkOfArt work3 = new WorkOfArt(3L, "work3","work3Concep",(Double) 1000.0, classificationsRepository.findAll().get(2), expositionRepository.findAll().get(1));
        workOfArts.add(work3);
        List<WorkOfArt_Images> image4 = new ArrayList<>();
        image4.add(workOfArtImagesRepository.findAll().get(3));
        WorkOfArt work4 = new WorkOfArt(4L, "work4","work4Concep",(Double) 1000.0, classificationsRepository.findAll().get(2), expositionRepository.findAll().get(1));
        workOfArts.add(work4);
        workOfArtRepository.saveAll(workOfArts);
    }

    private void setConnections(){
        Set<WorkOfArt> workOfArtsAux = new HashSet<>();
        workOfArtsAux.add(workOfArtRepository.findAll().get(0));
        expositionRepository.findAll().get(0).setWork(workOfArtsAux);

    }




}*/
