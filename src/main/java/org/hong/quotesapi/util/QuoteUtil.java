package org.hong.quotesapi.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.hong.quotesapi.entity.Quote;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author hong
 */
@Slf4j
public abstract class QuoteUtil {
    private static final Gson GSON = new Gson();
    private static final String FILE_NAME = "quotes.json";
    private static final String FILE_PATH = "src/main/resources/";

    /**
     * Initialize quotes
     *
     * @return list of quotes
     */
    public static List<Quote> initQuote() {
        try {
            //TODO can we remove the file path
            JsonReader reader = new JsonReader(new FileReader(FILE_PATH + FILE_NAME));
            Quote[] quotes = GSON.fromJson(reader, Quote[].class);
            return Collections.unmodifiableList(Arrays.asList(quotes));
        } catch (FileNotFoundException ex) {
            log.error("File not found: " + FILE_NAME, ex);
        } catch (JsonIOException | JsonSyntaxException ex) {
            log.error("Json parse error: " + FILE_NAME, ex);
        }
        return Collections.emptyList();
    }

    public static String toString(List<Quote> quotes) {
        StringBuilder sb = new StringBuilder();
        quotes.forEach(q -> {
            sb.append(toString(Optional.of(q)));
            sb.append(System.getProperty("line.separator"));
        });
        return sb.toString();
    }

    private static String toString(Optional<Quote> quote) {
        return quote.map((q) -> q.getQuote() + " -- " + q.getAuthor()).orElse("");
    }
}
