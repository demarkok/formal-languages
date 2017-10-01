package ru.spbau.kaysin.fl;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.State;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.HashMap;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.mutNode;

class Utils {



    static Graph autToGraph(Automaton automaton) {

        ArrayList<State> states = new ArrayList<>(automaton.getStates());

        HashMap<State, MutableNode> stateToNode = new HashMap<>();

        states.forEach(state -> {
            MutableNode node = mutNode(state.toString());
            stateToNode.put(state, node);
            if (automaton.getInitialState() == state) {
                node.add(Style.FILLED, Color.LIGHTGRAY);
            }
        });


        states.forEach(state -> state.getTransitions().forEach(transition -> {


            MutableNode nodeFrom = stateToNode.get(state);
            MutableNode nodeTo = stateToNode.get(transition.getDest());

            nodeFrom.addLink(nodeTo);

        }));


        return graph("graph").directed().with(stateToNode.values().toArray(new LinkSource[states.size()]));
    }
}
