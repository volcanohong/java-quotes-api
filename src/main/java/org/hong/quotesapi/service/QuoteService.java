package org.hong.quotesapi.service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import org.hong.quotesapi.entity.Quote;
import org.hong.quotesapi.exception.AuthorNotFoundException;
import org.hong.tool.logger.annotation.Logit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
     * @param num # of quotes, return 1 quote when num is null
     * @return list of quotes
     */
    @Logit
    public List<Quote> getRandomQuotes(Integer num) {
        return getRandomQuotes(quoteList, num);
    }

    /**
     * Get a random quote
     *
     * @return quote option
     */
    @Logit
    public Optional<Quote> getOneRandomQuote(List<Quote> quoteList) {
        return quoteList.isEmpty() ? Optional.empty() : Optional.of(quoteList.get(rand.nextInt(quoteList.size())));
    }

    /**
     * Get quotes by author and limit
     *
     * @param author name, return random quote when author is null
     * @param num    # of quotes, return 1 quote when num is null
     * @return list of quotes
     */
    @Logit
    public List<Quote> getByAuthor(String author, Integer num) throws AuthorNotFoundException {
        if (isNull(author) || author.isEmpty()) {
            return getRandomQuotes(num);
        }

        Predicate<Quote> quotePredicate = quote -> nonNull(quote.getAuthor()) &&
                quote.getAuthor().toLowerCase(Locale.ROOT).contains(author.toLowerCase(Locale.ROOT));

        List<Quote> quoteFiltered = quoteList.stream().filter(quotePredicate).collect(Collectors.toList());

        if (quoteFiltered.isEmpty()) {
            return Collections.emptyList();
//            throw new AuthorNotFoundException();
        }

        return getRandomQuotes(quoteFiltered, num);
    }

    private List<Quote> getRandomQuotes(List<Quote> quoteList, Integer num) {
        if (quoteList.isEmpty()) {
            return Collections.emptyList();
        }
        if (null == num || num <= 1) {
            return new ArrayList<Quote>() {{
                getOneRandomQuote(quoteList).ifPresent(this::add);
            }};
        }
        List<Quote> newList = new ArrayList<>(quoteList);
        Collections.shuffle(newList);

        return newList.subList(0, num < QUOTE_NUM_LIMIT ? num : QUOTE_NUM_LIMIT);
    }
}
