package blackjack.domain.card;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CardFactory {
    private static final String NULL_ERROR_MSG = "카드 Type과 Figure에 Null이 들어올 수 없습니다.";
    private static final Set<Card> cards = new HashSet<>();

    static {
        for (Type type : Type.values()) {
            for (Figure figure : Figure.values()) {
                cards.add(new Card(type, figure));
            }
        }
    }

    private CardFactory() {
    }

    public static Set<Card> getInstance() {
        return new HashSet<>(cards);
    }

    public static Card of(Type type, Figure figure) {
        Objects.requireNonNull(type, NULL_ERROR_MSG);
        Objects.requireNonNull(figure, NULL_ERROR_MSG);

        return new Card(type, figure);
    }
}