package com.juniorjavaready.infrastructure.http;

import com.juniorjavaready.domain.Offer.NoJobsFoundException;
import com.juniorjavaready.domain.Offer.JobOffer;
import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import com.juniorjavaready.domain.Offer.JobOfferMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class JobOfferFetcher {

    private static final String BASE_URL = "http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers";
    private final WebClient webClient;
    private JobOfferMapper JobOfferMapper;

    public JobOfferFetcher(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<JobOfferDto> fetchJobOffer() throws NoJobsFoundException {
        try {
            List<JobOffer> jobOffers = Optional.ofNullable(
                    webClient.get()
                            .uri(BASE_URL)
                            .retrieve()
                            .bodyToFlux(JobOffer.class)
                            .collectList()
                            .block()
            ).orElseThrow(() -> new NoJobsFoundException("No job offers found from the API."));

            if (jobOffers.isEmpty()) {
                throw new NoJobsFoundException("No job offers found from the API.");
            }

            log.info(String.valueOf(jobOffers.get(0).getId()));

            AtomicInteger idCounter = new AtomicInteger(1);
            return jobOffers.stream()
                    .map(jobOffer -> {
                        jobOffer.setId(idCounter.getAndIncrement());
                        System.out.println(jobOffer);
                        return JobOfferMapper.toDto(jobOffer);
                    })
                    .toList();

        } catch (Exception e) {
            log.info("Wystąpił błąd podczas pobierania ofert pracy: " + e.getMessage());
            throw new NoJobsFoundException("Błąd podczas pobierania ofert pracy z API: " + e.getMessage());
        }
    }

    public JobOfferDto findById(int id) {
        return null;
    }
}
