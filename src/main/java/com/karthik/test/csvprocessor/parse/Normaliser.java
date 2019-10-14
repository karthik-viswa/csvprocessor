package com.karthik.test.csvprocessor.parse;

import com.karthik.test.csvprocessor.model.Record;

import java.util.ArrayList;
import java.util.List;

public class Normaliser
{
    public List<Record> getNormalisedRecords(List<Record> records)
    {
        if(records == null || records.isEmpty())
        {
            return null;
        }

        List<Record> normalisedRecords = new ArrayList<>();

        int columnCount = records.get(0).getValues().size();

        int[] maxValues = new int[columnCount];
        int[] minValues = new int[columnCount];

        for(int i=0; i < columnCount; i++)
        {
            maxValues[i] = getMaxValueForColumn(records, i);
            minValues[i] = getMinValueForColumn(records, i);
        }

        records.stream().filter(record -> includeRecord(record, maxValues, minValues))
                        .forEach(normalisedRecords::add);

        return normalisedRecords;
    }

    private int getMaxValueForColumn(List<Record> records, int column)
    {
        return records.stream()
                      .filter(record -> record.getDecision().equals("1"))
                      .mapToInt(record -> record.getValues().get(column)).max()
                      .orElse(0);
    }

    private int getMinValueForColumn(List<Record> records, int column)
    {
        return records.stream()
                        .filter(record -> record.getDecision().equals("1"))
                        .mapToInt(record -> record.getValues().get(column)).min()
                        .orElse(0);
    }

    private boolean includeRecord(Record record, int[] maxValues, int[] minValues)
    {
        if(!record.getDecision().equals("0"))
        {
            return true;
        }

        List<Integer> values = record.getValues();
        for(int i=0; i < values.size(); i++)
        {
            if(values.get(i) <= maxValues[i] && values.get(i) >= minValues[i])
            {
                return true;
            }
        }

        return false;
    }
}
