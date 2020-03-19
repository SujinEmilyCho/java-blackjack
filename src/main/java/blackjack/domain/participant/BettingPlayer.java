package blackjack.domain.participant;

import java.util.Objects;

public class BettingPlayer extends Player {
    private final Money money;

    public BettingPlayer(Name name, Money money) {
        super(name);
        Objects.requireNonNull(money);
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BettingPlayer that = (BettingPlayer) o;
        return Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), money);
    }
}
