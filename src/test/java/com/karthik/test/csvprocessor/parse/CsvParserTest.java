package com.karthik.test.csvprocessor.parse;

import com.karthik.test.csvprocessor.model.Record;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvParserTest
{
    @Test
    public void testParseSingleVariable() throws IOException
    {
        CsvParser parser = new CsvParser();

        List<Record> records = parser.parse(Paths.get("src/test/resources/exampleA_input.csv"));

        assertEquals(5, records.size());
        records.stream().forEach(record -> assertEquals(1, record.getValues().size()));
        assertEquals(3, records.stream().filter(record -> record.getDecision().equals("0")).count());
        assertEquals(2, records.stream().filter(record -> record.getDecision().equals("1")).count());
    }

    @Test
    public void testParseMultipleVariables() throws IOException
    {
        CsvParser parser = new CsvParser();

        List<Record> records = parser.parse(Paths.get("src/test/resources/exampleC_input.csv"));

        assertEquals(7, records.size());
        records.stream().forEach(record -> assertEquals(2, record.getValues().size()));
        assertEquals(5, records.stream().filter(record -> record.getDecision().equals("0")).count());
        assertEquals(2, records.stream().filter(record -> record.getDecision().equals("1")).count());
    }
}
