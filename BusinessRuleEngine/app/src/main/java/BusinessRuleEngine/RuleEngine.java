package BusinessRuleEngine;

import java.util.List;
import java.util.ArrayList;


public class RuleEngine {

    private final List<Action> actions;
    private final Facts facts;

    public RuleEngine(final Facts facts) {
        this.actions = new ArrayList<>();
        this.facts = facts;
    }

    public void addAction(final Action action) {
        this.actions.add(action);
    }

    public int count() {
        return this.actions.size();
    }

    public void run() {
        this.actions.forEach(action -> action.execute(facts));
    }
}
