package com.juniorjavaready.domain.Offer;

import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import com.juniorjavaready.infrastructure.http.JobOfferFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JobOfferFacadeTest {

    private JobOfferRepositoryImplementation jobOfferRepositoryImplementation;
    private JobOfferFacade jobOfferFacade;
    private WebClient webClient;
    private JobOfferRepository jobOfferRepository;


    @BeforeEach
    void setUp() {
        jobOfferRepositoryImplementation = new JobOfferRepositoryImplementation(jobOfferRepository);
        webClient = WebClient.create();
        jobOfferFacade = new JobOfferFacade(jobOfferRepository, new JobOfferFetcher(webClient));

    }

    @Test
    public void should_save_4_job_offers_if_database_is_empty() {
        //given
        List<JobOffer> jobOffers = List.of(
                new JobOffer("NetCompany1", "Java developer1", "3000-4000 PLN", "http://netcompany1.pl"),
                new JobOffer("NetCompany2", "Java developer2", "3000-4000 PLN", "http://netcompany2.pl"),
                new JobOffer("NetCompany3", "Java developer3", "3000-4000 PLN", "http://netcompany3.pl"),
                new JobOffer("NetCompany5", "Java developer5", "3000-4000 PLN", "http://netcompany5.pl"));
        //when
        List<JobOffer> savedJobOffers = jobOfferRepositoryImplementation.save(jobOffers);

        //then
        assertEquals(4, savedJobOffers.size(), "Should save exactly 4 job offers");
    }

    @Test
    public void should_save_2_job_offers_if_database_has_4_job_offers() {

    }

    @Test
    public void should_throw_duplicate_key_exception_if_job_url_exists() {

    }

    @Test
    public void should_throw_not_found_exception_if_job_offer_not_exists() {

    }

    @Test
    public void should_find_offer_by_id_if_offer_was_saved() {

    }

    @Test
    public void should_fetch_all_jobs_if_database_is_empty() {

    }
}