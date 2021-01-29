package org.hong.quotesapi.service;

import org.hong.tool.logger.annotation.Logit;
import org.hong.quotesapi.entity.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Quote service
 *
 * @author Larva
 */
@Service
public class QuoteService extends BaseQuoteService {
    private static final Integer QUOTE_NUM_LIMIT = 10;
    private final Random rand = new Random();

    public QuoteService() {
        super();
    }

    /**
     * Get random number of quotes
     *
     * @param num # of quotes
     * @return list of quotes
     */
    @Logit
    public List<Quote> getRandomQuotes(Integer num) {
        if (quoteList.isEmpty()) {
            return Collections.emptyList();
        }
        if (null == num || num <= 1) {
            return new ArrayList<Quote>() {{
                getOneRandomQuote().ifPresent(this::add);
            }};
        }
        List<Quote> newList = new ArrayList<>(quoteList);
        Collections.shuffle(newList);

        return newList.subList(0, num < QUOTE_NUM_LIMIT ? num : QUOTE_NUM_LIMIT);
    }

    /**
     * Get a random quote
     *
     * @return quote option
     */
    @Logit
    public Optional<Quote> getOneRandomQuote() {
        return quoteList.isEmpty() ? Optional.empty() : Optional.of(quoteList.get(rand.nextInt(quoteList.size())));
    }
}
