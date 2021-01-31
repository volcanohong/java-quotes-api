package org.hong.quotesapi.controller;

import lombok.RequiredArgsConstructor;
import org.hong.quotesapi.annotation.ResponseWrapper;
import org.hong.quotesapi.entity.Quote;
import org.hong.quotesapi.exception.AuthorNotFoundException;
import org.hong.quotesapi.service.QuoteService;
import org.hong.quotesapi.util.QuoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API to get quotes
 *
 * Example:
 * http://localhost:9000/quotes/rand/str?num=4
 * http://localhost:9000/quotes/rand?num=4
 * http://localhost:9000/quotes?num=4&author=abc
 * @author hong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("quotes")
public class QuoteController {

    @Autowired
    private final QuoteService quoteService;

    /**
     * Get random quotes in string format
     *
     * @param num # of quotes
     * @return string
     */
    @GetMapping("/rand/str")
    @ResponseWrapper
    public String getRandQuotes(@RequestParam(required = false) Integer num) {
        return QuoteUtil.toString(quoteService.getRandomQuotes(num));
    }

    /**
     * Get random quotes in json format
     *
     * @param num # of quotes
     * @return list of quotes
     */
    @ResponseWrapper
    @GetMapping("/rand")
    public List<Quote> getRandQuotesInJson(@RequestParam(required = false) Integer num) {
        return quoteService.getRandomQuotes(num);
    }

    /**
     * Get quotes by author name in json format
     * Name can be partial like a filter
     *
     * @param num # of quotes
     * @param author name
     * @return list of quotes
     */
    @ResponseWrapper
    @GetMapping
    public List<Quote> getQuotesByAuthor(@RequestParam(required = false) Integer num,
                                         @RequestParam(required = false) String author) {
        List<Quote> quotes = quoteService.getByAuthor(author, num);
        if (quotes.isEmpty()) {
            throw new AuthorNotFoundException();
        }
        return quotes;
    }
}
