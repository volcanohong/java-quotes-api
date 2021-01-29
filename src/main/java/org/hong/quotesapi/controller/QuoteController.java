package org.hong.quotesapi.controller;

import lombok.RequiredArgsConstructor;
import org.hong.quotesapi.annotation.ResponseWrapper;
import org.hong.quotesapi.entity.Quote;
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
 * http://localhost:9000/quotes?num=4
 * http://localhost:9000/quotes/json?num=4
 *
 * @author hong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("quotes")
public class QuoteController {

    @Autowired
    private final QuoteService quoteService;

    /**
     * Get quotes in string
     *
     * @param num # of quotes
     * @return string
     */
    @GetMapping
    @ResponseWrapper
    public String getQuotes(@RequestParam Integer num) {
        return QuoteUtil.toString(quoteService.getRandomQuotes(num));
    }

    /**
     * Get quotes in json format
     *
     * @param num # of quotes
     * @return list of quotes
     */
    @ResponseWrapper
    @GetMapping("/json")
    public List<Quote> getQuotesInJson(@RequestParam Integer num) {
        return quoteService.getRandomQuotes(num);
    }
}
