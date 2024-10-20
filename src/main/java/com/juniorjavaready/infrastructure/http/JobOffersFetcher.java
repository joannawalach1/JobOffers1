package com.juniorjavaready.infrastructure.http;

import com.juniorjavaready.domain.Offer.JobOffer;
import com.juniorjavaready.domain.Offer.JobOfferMapper;
import com.juniorjavaready.domain.Offer.JobOfferRepository;
import com.juniorjavaready.domain.Offer.NoJobsFoundException;
import com.juniorjavaready.domain.Offer.dto.JobOfferDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Service
public class JobOffersFetcher {

    private static final Logger logger = Logger.getLogger(JobOffersFetcher.class.getName());
    private static final String BASE_URL = "http://ec2-3-120-147-150.eu-central-1.compute.amazonaws.com:5057/offers" ;
    private final JobOfferRepository jobOfferRepo;
    private final WebClient webClient;

    public JobOffersFetcher(JobOfferRepository jobOfferRepo, WebClient webClient) {
        this.jobOfferRepo = jobOfferRepo;
        this.webClient = webClient;
    }


    public Mono<List<JobOfferDto>> fetchAllJobs() {
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToFlux(JobOffer.class)
                .collectList()
                .map(jobOffers -> {
                    if (jobOffers.isEmpty()) {
                        try {
                            throw new NoJobsFoundException("No job offers found from the API.");
                        } catch (NoJobsFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    logger.info("Successfully retrieved " + jobOffers.size() + " job offers.");
                    logger.info(jobOffers.toString());
                    AtomicInteger idCounter = new AtomicInteger(1);
                    return jobOffers.stream()
                            .map(jobOffer -> {
                                jobOffer.setId(idCounter.getAndIncrement());
                                return JobOfferMapper.toDto(jobOffer);
                            })
                            .toList();
                });
    }

}

