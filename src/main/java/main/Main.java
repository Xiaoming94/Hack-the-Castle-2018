package main;

import backend.graph.PageNode;

import java.util.List;

public class Main {
    public static void main(String [] args){
        PageNode pn = new PageNode("Linux");
        List<String> neighbours = pn.getNeighbours();
        System.out.print(neighbours);
    }
}
