package BusinessRuleEngine;

import java.lang.UnsupportedOperationException;
import java.util.List;
import java.util.ArrayList;


public class RuleEngine {

    private final List<Action> actions;

    public RuleEngine() {
        this.actions = new ArrayList<>();
    }

    public void addAction(final Action action) {
        this.actions.add(action);
    }

    public int count() {
        return this.actions.size();
    }

    public void run() {
        throw new UnsupportedOperationException();
    }
}
