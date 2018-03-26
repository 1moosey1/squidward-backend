package com.squidward.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class BurnDownData {

    private int sum;
    List<Date> dates;
    List<Integer> points;
}
