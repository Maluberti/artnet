package com.internship.project.artnet.bootstrap;

import com.internship.project.artnet.domain.*;
import com.internship.project.artnet.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Bootstrap implements ApplicationRunner {

    private final ArtistRepository artistRepository;
    private final AdmirerRepository admirerRepository;
    private final ClassificationsRepository classificationsRepository;
    private final ExpositionRepository expositionRepository;
    private final StyleRepository styleRepository;
    private final WorkOfArtRepository workOfArtRepository;
    //private final WorkOfArt_ImagesRepository workOfArtImagesRepository;

    public Bootstrap(ArtistRepository artistRepository, AdmirerRepository admirerRepository, ClassificationsRepository classificationsRepository, ExpositionRepository expositionRepository, StyleRepository styleRepository, WorkOfArtRepository workOfArtRepository) {
        this.artistRepository = artistRepository;
        this.admirerRepository = admirerRepository;
        this.classificationsRepository = classificationsRepository;
        this.expositionRepository = expositionRepository;
        this.styleRepository = styleRepository;
        this.workOfArtRepository = workOfArtRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //set Admirer
        List<Admirer> admirerList = new ArrayList<>();
        Admirer admirer1 = new Admirer("admirer1", "emailAdmirer1", "password1", false, true, true);
        admirerList.add(admirer1);
        List<Admirer> admirerList2 = new ArrayList<>();
        Admirer admirer2 = new Admirer("admirer2", "emailAdmirer2", "password2", false, true, false);
        admirerList2.add(admirer2);

        //set Artist
        List<Artist> artistList = new ArrayList<>();
        Artist artist1 = new Artist("artist1", "emailArtist1", "password1", true, false, 1111111, "biographic1");
        artistList.add(artist1);
        List<Artist> artistList2 = new ArrayList<>();
        Artist artist2 = new Artist("artist2", "emailArtist2", "password2", true, false, 2222222, "biographic2");
        artistList2.add(artist2);

        //set Classifications

        Classifications classification1 = new Classifications("classification1");
        Classifications classification2 = new Classifications("classification2");

        List<Exposition> expositionList = new ArrayList<>();
        Exposition exposition = new Exposition("exposition1", "concept 1", "inspiration1", LocalDate.of(2023, 02, 05), LocalDate.of(2023, 02, 25));
        expositionList.add(exposition);

        List<Exposition> expositionList2 = new ArrayList<>();
        Exposition exposition2 = new Exposition("exposition2", "concept 2", "inspiration2", LocalDate.of(2023, 02, 05), LocalDate.of(2023, 02, 25));
        expositionList2.add(exposition2);


        List<Style> styleList = new ArrayList<>();
        Style style1 = new Style("style 1");
        styleList.add(style1);
        Style style2 = new Style("style 2");
        styleList.add(style2);

        List<Style> styleList2 = new ArrayList<>();
        Style style3 = new Style("style 3");
        styleList2.add(style3);
        Style style4 = new Style("style 4");
        styleList2.add(style4);

        List<WorkOfArt> workOfArtList = new ArrayList<>();
        WorkOfArt work1 = new WorkOfArt("work 1", "concept work1", 150.00);
        workOfArtList.add(work1);
        WorkOfArt work2 = new WorkOfArt("work 2", "concept work2", 120.00);
        workOfArtList.add(work2);

        List<WorkOfArt> workOfArtList2 = new ArrayList<>();
        WorkOfArt work3 = new WorkOfArt("work 3", "concept work3", 130.00);
        workOfArtList2.add(work3);
        WorkOfArt work4 = new WorkOfArt("work 4", "concept work4", 140.00);
        workOfArtList2.add(work4);

        artistRepository.saveAll(artistList);
        artistRepository.saveAll(artistList2);
        admirerRepository.saveAll(admirerList);
        admirerRepository.saveAll(admirerList2);
        classificationsRepository.save(classification1);
        classificationsRepository.save(classification2);
        expositionRepository.saveAll(expositionList);
        expositionRepository.saveAll(expositionList2);
        styleRepository.saveAll(styleList);
        styleRepository.saveAll(styleList2);
        workOfArtRepository.saveAll(workOfArtList);
        workOfArtRepository.saveAll(workOfArtList2);

        admirer1.setWork(workOfArtList);
        admirer1.setArtists(artistList);
        admirer1.setExpositions(expositionList);

        admirer2.setWork(workOfArtList2);
        admirer2.setArtists(artistList2);
        admirer2.setExpositions(expositionList2);

        artist1.setExpositions(expositionList);
        artist1.setAdmirers(admirerList);
        artist1.setStyles(styleList);

        artist2.setExpositions(expositionList2);
        artist2.setAdmirers(admirerList2);
        artist2.setStyles(styleList2);

        classification1.setWork(workOfArtList);
        classification2.setWork(workOfArtList2);



        exposition.setWork(workOfArtList);
        exposition.setArtist(artist1);
        exposition.setAdmirers(admirerList);

        exposition2.setWork(workOfArtList2);
        exposition2.setArtist(artist2);
        exposition.setAdmirers(admirerList2);


        style1.setArtists(artistList);
        style2.setArtists(artistList);
        style3.setArtists(artistList2);
        style4.setArtists(artistList2);

        work1.setClassification(classification1);
        work1.setAdmirer(admirer1);
        work1.setExposition(exposition);

        work2.setClassification(classification1);
        work2.setAdmirer(admirer1);
        work2.setExposition(exposition);

        work3.setClassification(classification2);
        work3.setAdmirer(admirer2);
        work3.setExposition(exposition2);

        work1.setClassification(classification2);
        work1.setAdmirer(admirer2);
        work1.setExposition(exposition2);

        artistRepository.saveAll(artistList);
        artistRepository.saveAll(artistList2);
        admirerRepository.saveAll(admirerList);
        admirerRepository.saveAll(admirerList2);
        classificationsRepository.save(classification1);
        classificationsRepository.save(classification2);
        expositionRepository.saveAll(expositionList);
        expositionRepository.saveAll(expositionList2);
        styleRepository.saveAll(styleList);
        styleRepository.saveAll(styleList2);
        workOfArtRepository.saveAll(workOfArtList);
        workOfArtRepository.saveAll(workOfArtList2);



    }


}
