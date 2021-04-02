package BusinessRuleEngine;


@FunctionalInterface
public interface Action {
    void execute(Facts facts);
}
