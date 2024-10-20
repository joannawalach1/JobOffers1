package com.juniorjavaready.domain.Offer;

import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import com.juniorjavaready.infrastructure.http.JobOfferFetcher;

import java.util.Collections;
import java.util.List;

public class JobOfferFacade {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferFetcher jobOfferFetcher;

    public JobOfferFacade(JobOfferRepository jobOfferRepository, JobOfferFetcher jobOfferFetcher) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobOfferFetcher = jobOfferFetcher;
    }

    public List<JobOffer> findAllOffers() {
        return jobOfferRepository.findAll();
    }

    public JobOffer findOfferById(int id) {
        return jobOfferRepository.findById(id);
    }

    public JobOffer findOfferByUrl(String url) {
        return jobOfferRepository.findByUrls(url);
    }

    public JobOffer save(JobOffer jobOffer) throws DuplicateKeyException, NoJobsFoundException {
        if (jobOffer == null) {
            throw new NoJobsFoundException("JobOffer cannot be null");
        }

        List<JobOffer> savedOffers = jobOfferRepository.save(Collections.singletonList(jobOffer));

        if (savedOffers.isEmpty() || savedOffers.get(0) == null) {
            throw new RuntimeException("Failed to save JobOffer to the repository");
        }

        return savedOffers.get(0);
    }



    public List<JobOfferDto> fetchAllOffersAndSaveAllIfNoExists() throws NoJobsFoundException {
        return jobOfferFetcher.fetchJobOffer();
    }
}
