package com.juniorjavaready.domain.Offer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class JobOfferDto {
    private int id;
    private String company;
    private String title;
    private String salary;
    private String offerUrl;

    public JobOfferDto(int id, String company, String title, String salary, String offerUrl) {
    }
}
