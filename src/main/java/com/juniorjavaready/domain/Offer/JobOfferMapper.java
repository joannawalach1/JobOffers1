package com.juniorjavaready.domain.Offer;

import com.juniorjavaready.domain.Offer.dto.JobOfferDto;

public class JobOfferMapper {
    private static int idCounter = 1;

    public static JobOffer toEntity(JobOfferDto jobOfferDto) {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(idCounter++);
        jobOffer.setCompany(jobOfferDto.getCompany());
        jobOffer.setTitle(jobOfferDto.getTitle());
        jobOffer.setSalary(jobOfferDto.getSalary());
        jobOffer.setOfferUrl(jobOfferDto.getOfferUrl());
        return jobOffer;
    }

    public static JobOfferDto toDto(JobOffer jobOffer) {
        JobOfferDto jobOfferDto = new JobOfferDto();
        jobOfferDto.setId(jobOffer.getId());
        jobOfferDto.setCompany(jobOffer.getCompany());
        jobOfferDto.setTitle(jobOffer.getTitle());
        jobOfferDto.setSalary(jobOffer.getSalary());
        jobOfferDto.setOfferUrl(jobOffer.getOfferUrl());
        return jobOfferDto;
    }
    }
