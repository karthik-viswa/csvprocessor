package com.karthik.test.csvprocessor.parse;

import com.karthik.test.csvprocessor.model.Record;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class CsvParserTest
{
    @Test
    public void testParseSingleVariable() throws IOException
    {
        CsvParser parser = new CsvParser();
        String file = "src/test/resources/exampleA_input.csv";

        List<Record> records = parser.parse(file);

        //assertThat(records.size(), is(5));
        assertEquals(5, records.size());
        records.stream().forEach(record -> assertEquals(1, record.getValues().size()));
        assertEquals(3, records.stream().filter(record -> record.getDecision().equals("0")).count());
        assertEquals(2, records.stream().filter(record -> record.getDecision().equals("1")).count());
    }

    @Test
    public void testParseMultipleVariables() throws IOException
    {
        CsvParser parser = new CsvParser();
        String file = "src/test/resources/exampleC_input.csv";

        List<Record> records = parser.parse(file);

        assertEquals(7, records.size());
        records.stream().forEach(record -> assertEquals(2, record.getValues().size()));
        assertEquals(5, records.stream().filter(record -> record.getDecision().equals("0")).count());
        assertEquals(2, records.stream().filter(record -> record.getDecision().equals("1")).count());
    }
}
