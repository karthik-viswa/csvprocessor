package com.karthik.test.csvprocessor.parse;

import com.karthik.test.csvprocessor.model.Record;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class NormaliserTest
{
    @Test
    public void testGetNormalisedRecords()
    {
        Record record1 = new Record("1");
        record1.addValue(10);
        record1.addValue(20);
        record1.setDecision("1");

        Record record2 = new Record("2");
        record2.addValue(6);
        record2.addValue(30);
        record2.setDecision("1");

        Record record3 = new Record("3");
        record3.addValue(10);
        record3.addValue(30);
        record3.setDecision("0");

        Record record4 = new Record("4");
        record4.addValue(3);
        record4.addValue(15);
        record4.setDecision("0");

        Record record5 = new Record("5");
        record5.addValue(15);
        record5.addValue(60);
        record5.setDecision("0");

        Record record6 = new Record("6");
        record6.addValue(7);
        record6.addValue(50);
        record6.setDecision("0");

        List<Record> records = Stream.of(record1, record2, record3, record4, record5, record6).collect(Collectors.toList());

        Normaliser normaliser = new Normaliser();
        List<Record> normalisedRecords = normaliser.getNormalisedRecords(records);

        assertEquals(4, normalisedRecords.size());
    }
}
