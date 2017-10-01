package ru.spbau.kaysin.fl;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;

public class HW03 {

    // regex
    private static String positiveDigit = "[1-9]";
    private static String num = String.format("(-|())(0|(%s(0|%s)*))",positiveDigit, positiveDigit);
    private static String ws = " *";
    private static String list = String.format("\\[%s(%s|())(%s;%s%s)*%s\\]", ws, num, ws, ws, num, ws); // nice!
    private static String symb = "([a-z]|[A-Z]|_)";
    private static String ident = String.format("%s(%s|0|%s)*", symb, positiveDigit, symb);
    private static String x = String.format("(%s|%s|%s)", ident, list, num);
    private static String tuple = String.format("\\(%s(%s|())(%s;%s%s)*%s\\)", ws, x, ws, ws, x, ws);

    public static void main(String[] args) throws IOException {
        Automaton aut1a = new RegExp("(a|b)*ab(a|b)*|(a|b)*a|b*").toAutomaton(true);
        Automaton aut1b = new RegExp("(a|b)*(ab|ba)(a|b)*|a*|b*").toAutomaton(true);
        Automaton aut2a = new RegExp(list).toAutomaton(true);
        Automaton aut2b = new RegExp(tuple).toAutomaton(true);

        Graph g1a = Utils.autToGraph(aut1a);
        Graph g1b = Utils.autToGraph(aut1b);
        Graph g2a = Utils.autToGraph(aut2a);
        Graph g2b = Utils.autToGraph(aut2b);


        Graphviz.fromGraph(g1a).render(Format.PNG).toFile(new File("result/1/a.png"));
        Graphviz.fromGraph(g1b).render(Format.PNG).toFile(new File("result/1/b.png"));
        Graphviz.fromGraph(g2a).render(Format.PNG).toFile(new File("result/2/a.png"));
        Graphviz.fromGraph(g2b).render(Format.PNG).toFile(new File("result/2/b.png"));
    }

}
