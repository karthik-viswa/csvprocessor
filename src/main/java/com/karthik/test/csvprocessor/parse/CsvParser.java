package com.karthik.test.csvprocessor.parse;

import com.karthik.test.csvprocessor.model.Record;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvParser
{
    public List<Record> parse(String filePath) throws IOException
    {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));

        CSVReader csvReader = getCsvReader(reader);

        List<Record> records = new ArrayList<>();

        try {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                int columns = line.length;
                if(columns > 1)
                {
                    Record record = new Record(line[0]);
                    for(int i=1; i < columns-1; i++)
                    {
                        record.addValue(Integer.parseInt(line[i]));
                    }
                    record.setDecision(line[columns-1]);
                    records.add(record);
                }
            }
        }
        finally {
            csvReader.close();
            reader.close();
        }

        return records;
    }

    private CSVReader getCsvReader(Reader reader)
    {
        CSVParser parser = new CSVParserBuilder()
                            .withSeparator(',')
                            .withIgnoreQuotations(true)
                            .build();

        return new CSVReaderBuilder(reader)
                            .withSkipLines(1) // skip header
                            .withCSVParser(parser)
                            .build();
    }
}
