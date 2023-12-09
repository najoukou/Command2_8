package edu.uga.miage.m1.polygons.gui.commands;


import java.util.Deque;
import java.util.LinkedList;

public class CommandHistory {
    private Deque<Command> historystack = new LinkedList<>();

    public void push(Command command) {
        historystack.push(command);
    }

    public Command pop() {
        if (!historystack.isEmpty()) {
            return historystack.pop();
        }
        return null;
    }
    public boolean isEmpty() {
        return historystack.isEmpty();
    }
}
