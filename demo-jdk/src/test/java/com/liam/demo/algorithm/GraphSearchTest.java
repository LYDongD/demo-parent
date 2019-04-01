package com.liam.demo.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphSearchTest {

    private GraphSearch graphSearch;

    @Before
    public void init(){
        graphSearch = new GraphSearch();
        graphSearch.initGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }

    @Test
    public void distance() {
        System.out.println(graphSearch.distance("A-B-C"));
        System.out.println(graphSearch.distance("A-D"));
        System.out.println(graphSearch.distance("A-D-C"));
        System.out.println(graphSearch.distance("A-E-B-C-D"));
        System.out.println(graphSearch.distance("A-E-D"));
    }
}