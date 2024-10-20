package com.juniorjavaready.infrastructure.controller;

import com.juniorjavaready.domain.Offer.NoJobsFoundException;
import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import com.juniorjavaready.infrastructure.http.JobOfferFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/jobs")
public class JobOfferController {
    private final JobOfferFetcher jobOfferFetcher;

    public JobOfferController(JobOfferFetcher jobOfferFetcher) {
        this.jobOfferFetcher = jobOfferFetcher;
    }


    @GetMapping("/fetchOffers")
    public ResponseEntity<List<JobOfferDto>> getAllJobOffers() throws NoJobsFoundException {
        try {
            List<JobOfferDto> jobOffers = jobOfferFetcher.fetchJobOffer();
            log.info("Successfully fetched {} job offers", jobOffers.size());
            return ResponseEntity.ok(jobOffers);
        } catch (NoJobsFoundException e) {
            log.error("No job offers found: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<JobOfferDto> getJobOfferById(@PathVariable int id) throws NoJobsFoundException {
        JobOfferDto foundById = jobOfferFetcher.findById(id);
        log.info("Successfully retrieved job with id", id);
        return ResponseEntity.ok(foundById);
    }
}
