package org.hong.quotesapi.service;

import org.hong.quotesapi.entity.Quote;
import org.springframework.stereotype.Component;
import org.hong.quotesapi.util.QuoteUtil;

import java.util.List;

@Component
public class BaseQuoteService {

    protected final List<Quote> quoteList;

    public BaseQuoteService() {
        quoteList = QuoteUtil.initQuote();
    }
}
