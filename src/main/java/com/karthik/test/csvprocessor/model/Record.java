package com.karthik.test.csvprocessor.model;

import java.util.ArrayList;
import java.util.List;

public class Record
{
    private String id;

    private List<Integer> values;

    private String decision;

    public Record(String id)
    {
        this.id = id;
        this.values = new ArrayList<>();
    }

    public void addValue(Integer value)
    {
        values.add(value);
    }

    public String getId()
    {
        return id;
    }

    public String getDecision()
    {
        return decision;
    }

    public void setDecision(final String decision)
    {
        this.decision = decision;
    }

    public List<Integer> getValues()
    {
        return values;
    }
}
