package com.interest.message;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        List<Integer> list2 = list.stream().filter(integer -> integer<5).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
    }

}
