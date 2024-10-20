package com.juniorjavaready.domain.Offer;

import com.juniorjavaready.domain.Offer.dto.JobOfferDto;

public class JobOfferMapper {
    public JobOfferDto toDto(JobOffer jobOffer) {
        JobOfferDto dto = new JobOfferDto();
        dto.setId(jobOffer.getId());
        dto.setCompanyName(jobOffer.getCompanyName());
        dto.setPosition(jobOffer.getPosition());
        dto.setSalary(jobOffer.getSalary());
        dto.setOfferUrl(jobOffer.getOfferUrl());
        return new JobOfferDto();
    }
}
