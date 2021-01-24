package org.hong.quotesapi.controller;

import lombok.RequiredArgsConstructor;
import org.hong.quotesapi.entity.Quote;
import org.hong.quotesapi.service.QuoteService;
import org.hong.quotesapi.util.QuoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("quotes")
public class QuoteController {

    @Autowired
    private final QuoteService quoteService;

    @GetMapping
    public ResponseEntity<String> getQuotes(@RequestParam Integer num) {
        return ResponseEntity.ok().body(QuoteUtil.toString(quoteService.getRandomQuotes(num)));
    }

    //http://localhost:9000/quotes/json?num=4
    @GetMapping("/json")
    public ResponseEntity<List<Quote>> getQuotesInJson(@RequestParam Integer num) {
        return ResponseEntity.ok().body(quoteService.getRandomQuotes(num));
    }
}
