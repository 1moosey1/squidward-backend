package com.squidward.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter @Setter
public class BurnDownData {

    private int sum;
    private Map<Date, Integer> dateDifficultyMap;
}
