package com.juniorjavaready.domain.Offer;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class JobOffer {
    private int id;
    private String companyName;
    private String position;
    private String salary;
    private String offerUrl;

    public JobOffer(int id, String companyName, String position, String salary, String offerUrl) {
    }
}
