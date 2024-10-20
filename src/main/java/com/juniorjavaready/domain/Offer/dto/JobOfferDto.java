package com.juniorjavaready.domain.Offer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class JobOfferDto {
    private int id;
    private String companyName;
    private String position;
    private String salary;
    private String offerUrl;
}
