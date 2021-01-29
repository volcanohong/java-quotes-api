package org.hong.quotesapi.service;

import org.hong.quotesapi.entity.Quote;
import org.springframework.stereotype.Component;
import org.hong.quotesapi.util.QuoteUtil;

import java.util.List;

/**
 * Base quote service
 *
 * @author hong
 */
@Component
public class BaseQuoteService {

    protected final List<Quote> quoteList;

    /**
     * Initialize the quote list
     */
    public BaseQuoteService() {
        quoteList = QuoteUtil.initQuote();
    }
}
