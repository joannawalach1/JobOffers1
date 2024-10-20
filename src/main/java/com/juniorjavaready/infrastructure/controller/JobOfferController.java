package com.juniorjavaready.infrastructure.controller;

import com.juniorjavaready.domain.Offer.NoJobsFoundException;
import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import com.juniorjavaready.infrastructure.http.JobOffersFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/jobs")
public class JobOfferController {
    private final JobOffersFetcher jobOfferFetcher;

    public JobOfferController(JobOffersFetcher jobOfferFetcher) {
        this.jobOfferFetcher = jobOfferFetcher;
    }


    @GetMapping("/fetchOffers")
    public ResponseEntity<Mono<List<JobOfferDto>>> getAllJobOffers() throws NoJobsFoundException {
        Mono<List<JobOfferDto>> jobOffers = jobOfferFetcher.fetchAllJobs();
        return ResponseEntity.ok().body(jobOffers);
    }

//    @GetMapping("/findById/{id}")
//    public ResponseEntity<JobOfferDto> getJobOfferById(@PathVariable int id) throws NoJobsFoundException {
//        JobOfferDto foundById = jobOfferFetcher.findById(id);
//        log.info("Successfully retrieved job with id", id);
//        return ResponseEntity.ok(foundById);
//    }
}
