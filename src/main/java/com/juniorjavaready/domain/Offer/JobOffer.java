package com.juniorjavaready.domain.Offer;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class JobOffer {
    private int id;
    private String company;
    private String title;
    private String salary;
    private String offerUrl;

    public JobOffer(int id, String company, String title, String salary, String offerUrl) {
    }
}
