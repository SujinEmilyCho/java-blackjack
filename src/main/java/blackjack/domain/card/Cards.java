package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static blackjack.domain.card.CardDeck.NUMBER_OF_FIRST_CARDS;
import static blackjack.domain.result.ResultType.BUST;

public class Cards {
    private static final int ACE_VALUE_DIFFERENCE = 10;

    private List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public int computeScore() {
        int sum = cards.stream()
                .mapToInt(Card::cardValue)
                .sum();
        return handleAce(sum);
    }

    private int handleAce(int sum) {
        for (Card card : cards) {
            if (sum <= BUST) {
                break;
            }

            if (card.isAce()) {
                sum -= ACE_VALUE_DIFFERENCE;
            }
        }
        return sum;
    }

    public List<String> cardsInfo() {
        return cards.stream()
                .map(Card::cardInfo)
                .collect(Collectors.toList());
    }

    public int size() {
        return cards.size();
    }

    public boolean isBlackJack() {
        return computeScore() == BUST && cards.size() == NUMBER_OF_FIRST_CARDS;
    }

    public boolean isBust() {
        return computeScore() > BUST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
