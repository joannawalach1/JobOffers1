package com.juniorjavaready.domain.Offer;

import java.util.ArrayList;
import java.util.List;

public class JobOfferRepositoryImplementation implements JobOfferRepository{
    private List<JobOffer> jobOffers = new ArrayList<>();
    private final JobOfferRepository jobOfferRepository;

    public JobOfferRepositoryImplementation(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @Override
    public List<JobOffer> findAll() {
        return jobOfferRepository.findAll();
    }

    @Override
    public JobOffer findById(int id) {
        return jobOfferRepository.findById(id);
    }


    @Override
    public List<JobOffer> save(List<JobOffer> offersToSave) {
        jobOffers.addAll(offersToSave);
        return offersToSave;
    }

    @Override
    public int count() {
        return jobOfferRepository.count();
    }

    @Override
    public void clear() {
        jobOfferRepository.clear();
    }
}
