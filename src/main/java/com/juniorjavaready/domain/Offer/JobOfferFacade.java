package com.juniorjavaready.domain.Offer;

import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import com.juniorjavaready.infrastructure.http.JobOffersFetcher;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

public class JobOfferFacade {

    private final JobOfferRepository jobOfferRepository;
    private final JobOffersFetcher jobOfferFetcher;

    public JobOfferFacade(JobOfferRepository jobOfferRepository, JobOffersFetcher jobOfferFetcher) {
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

//        if (jobOfferRepository.findByUrls(jobOffer.getOfferUrl())) {
//            throw new DuplicateKeyException("JobOffer already exists");
//        }

        jobOfferRepository.save(Collections.singletonList(jobOffer));

        return jobOffer;
    }



    public Mono<List<JobOfferDto>> fetchAllOffersAndSaveAllIfNoExists() throws NoJobsFoundException {
        return jobOfferFetcher.fetchAllJobs();
    }
}
