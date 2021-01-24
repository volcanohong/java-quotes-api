package org.hong.quotesapi.service;

import org.hong.tool.logger.annotation.Logit;
import org.hong.quotesapi.entity.Quote;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService extends BaseQuoteService {
    private static final Integer QUOTE_NUM_LIMIT = 10;
    private final Random rand = new Random();

    public QuoteService() {
        super();
    }

    @Logit
    public List<Quote> getRandomQuotes(Integer num) {
        if (quoteList.isEmpty()) {
            return Collections.emptyList();
        }
        if (null == num || num <= 1) {
            return new ArrayList<Quote>() {{
                getARandomQuote().ifPresent(this::add);
            }};
        }
        List<Quote> newList = new ArrayList<>(quoteList);
        Collections.shuffle(newList);

        return newList.subList(0, num < QUOTE_NUM_LIMIT ? num : QUOTE_NUM_LIMIT);
    }

    public Optional<Quote> getARandomQuote() {
        return quoteList.isEmpty() ? Optional.empty() : Optional.of(quoteList.get(rand.nextInt(quoteList.size())));
    }
}
