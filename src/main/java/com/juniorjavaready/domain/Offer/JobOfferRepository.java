package com.juniorjavaready.domain.Offer;

import java.util.List;

public interface JobOfferRepository {
    List<JobOffer> findAll();
    JobOffer findById(int id);
    List<JobOffer> save(List<JobOffer> offersToSave);
    int count();
    void clear();
    JobOffer findByUrls(String offerUrl);
}
