package com.juniorjavaready.domain.Offer;

import com.juniorjavaready.infrastructure.http.JobOffersFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobOfferFacadeTest {

    private JobOfferRepositoryImplementation jobOfferRepositoryImplementation;
    private JobOfferFacade jobOfferFacade;
    private JobOffersFetcher jobOfferFetcher;
    private WebClient webClient;
    private JobOfferRepository jobOfferRepository;


    @BeforeEach
    void setUp() {
        webClient = WebClient.create();
        jobOfferRepository = new JobOfferRepositoryImplementation();
        jobOfferFacade = new JobOfferFacade(jobOfferRepository, new JobOffersFetcher(
                jobOfferRepository,
                WebClient.builder().build()));
        jobOfferRepository.clear();
    }

    @Test
    public void should_save_4_job_offers_if_database_is_empty() throws NoJobsFoundException, DuplicateKeyException {
        //given
        List<JobOffer> jobOffers = List.of(
                new JobOffer(1, "NetCompany1", "Java developer1", "3000-4000 PLN", "http://netcompany1.pl"),
                new JobOffer(2, "NetCompany2", "Java developer2", "3000-4000 PLN", "http://netcompany2.pl"),
                new JobOffer(3, "NetCompany3", "Java developer3", "3000-4000 PLN", "http://netcompany3.pl"),
                new JobOffer(4, "NetCompany5", "Java developer5", "3000-4000 PLN", "http://netcompany5.pl"));
        //when
        for (JobOffer jobOffer : jobOffers) {
            jobOfferFacade.save(jobOffer);
        }
        //then
        assertEquals(4, jobOfferRepository.count(), "Repository should contain 4 job offers");
    }

    @Test
    public void should_save_2_job_offers_if_database_has_4_job_offers() throws NoJobsFoundException, DuplicateKeyException {
        //given
        List<JobOffer> jobOffers = List.of(
                new JobOffer(1, "NetCompany1", "Java developer1", "3000-4000 PLN", "http://netcompany1.pl"),
                new JobOffer(2, "NetCompany5", "Java developer5", "3000-4000 PLN", "http://netcompany5.pl"));
        //when
        for (JobOffer jobOffer : jobOffers) {
            jobOfferFacade.save(jobOffer);
        }
        //then
        assertEquals(2, jobOfferRepository.count(), "Repository should contain 2 job offers");
    }

    @Test
    public void should_throw_duplicate_key_exception_if_job_url_exists() throws NoJobsFoundException, DuplicateKeyException {
        //given
        JobOffer existingOffer = new JobOffer(1, "NetCompany1", "Java developer1", "3000-4000 PLN", "http://netcompany1.pl");
        jobOfferFacade.save(existingOffer);
        JobOffer duplicatedOffer = new JobOffer(2, "NetCompany2", "Java developer2", "3000-4000 PLN", "http://netcompany1.pl");
        //when
        Exception exception = assertThrows(DuplicateKeyException.class, () -> {
            jobOfferFacade.save(duplicatedOffer);
        });
        //then
        assertEquals("Job offer with this URL already exists.", exception.getMessage());
    }

    @Test
    public void should_throw_not_found_exception_if_job_offer_not_exists() {
        //given
        JobOffer nonExistingOffer = new JobOffer(1, "NetCompany1", "Java developer1", "3000-4000 PLN", "http://netcompany1.pl");
        //when
        Exception exception = assertThrows(NoJobsFoundException.class, () -> {
            jobOfferFacade.findOfferById(nonExistingOffer.getId());
        });
        //then
        assertEquals("Job offer not found with id: " + nonExistingOffer.getId(), exception.getMessage());

    }

    @Test
    public void should_find_offer_by_id_if_offer_was_saved() throws NoJobsFoundException, DuplicateKeyException {
        //given
        JobOffer newOffer = new JobOffer(1, "NetCompany1", "Java developer1", "3000-4000 PLN", "http://netcompany1.pl");
        jobOfferFacade.save(newOffer);
        //when
        JobOffer offerById = jobOfferFacade.findOfferById(newOffer.getId());
        //then
        assertEquals(newOffer.getId(), offerById.getId());
    }

    @Test
    public void should_fetch_all_jobs_if_database_is_empty() {

    }
}