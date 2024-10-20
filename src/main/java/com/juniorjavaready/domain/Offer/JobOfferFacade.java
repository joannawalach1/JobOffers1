package com.juniorjavaready.domain.Offer;

import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import com.juniorjavaready.infrastructure.http.JobOfferFetcher;

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

    public JobOfferDto save(JobOffer jobOffer) {
        if (jobOffer == null) {
            throw new IllegalArgumentException("JobOffer cannot be null");
        }
        JobOfferDto jobOfferDto = new JobOfferDto();
        jobOfferDto.setId(jobOffer.getId());
        jobOfferDto.setCompanyName(jobOffer.getCompanyName());
        jobOfferDto.setPosition(jobOffer.getPosition());
        jobOfferDto.setSalary(jobOffer.getSalary());
        jobOfferDto.setOfferUrl(jobOffer.getOfferUrl());
        return jobOfferDto;
    }

    public List<JobOfferDto> fetchAllOffersAndSaveAllIfNoExists() throws NoJobsFoundException {
        return jobOfferFetcher.fetchJobOffer();
    }
}
