package com.juniorjavaready.domain.Offer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class JobOfferRepositoryImplementation implements JobOfferRepository{
    private List<JobOffer> jobOffers;

    public JobOfferRepositoryImplementation() {
        this.jobOffers = new ArrayList<>();
    }

    @Override
    public List<JobOffer> findAll() {
        return new ArrayList<>(jobOffers) ;
    }

    @Override
    public JobOffer findById(int id) {
        return jobOffers.stream()
                .filter(offer -> offer.getId() == id)
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<JobOffer> save(List<JobOffer> offersToSave) {
        jobOffers.addAll(offersToSave);
        return offersToSave;
    }

    @Override
    public int count() {
        return jobOffers.size();
    }

    @Override
    public void clear() {
        jobOffers.clear();
    }

    @Override
    public JobOffer findByUrls(String offerUrl) {
        return jobOffers.stream()
                .filter(jobOffer -> jobOffer.getOfferUrl().equals(offerUrl))
                .findFirst()
                .orElse(null);
    }
}
