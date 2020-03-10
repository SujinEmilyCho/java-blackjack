package blackjack.domain;

import blackjack.domain.Card.CardDeck;
import blackjack.domain.Card.CardNumber;
import blackjack.domain.Card.Cards;
import blackjack.domain.Card.Figure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CardsTest {
    @DisplayName("카드 합 계산 확인")
    @Test
    void sumTest() {
        Cards cards = new Cards();
        cards.add(CardDeck.of(CardNumber.THREE, Figure.CLOVER));
        cards.add(CardDeck.of(CardNumber.KING, Figure.CLOVER));

        int expected = 13;
        assertThat(cards.getSum()).isEqualTo(expected);
    }

    @DisplayName("Ace가 있을 때의 카드 합 계산 확인(21이 초과하는 경우)")
    @Test
    void aceSumTest() {
        Cards cards = new Cards();
        cards.add(CardDeck.of(CardNumber.ACE, Figure.CLOVER));
        cards.add(CardDeck.of(CardNumber.KING, Figure.CLOVER));
        cards.add(CardDeck.of(CardNumber.QUEEN, Figure.HEART));

        int expected = 21;
        assertThat(cards.getSum()).isEqualTo(expected);
    }

    @DisplayName("Ace가 있을 때의 카드 합 계산 확인(21이 초과하지 않는 경우)")
    @Test
    void aceSumTest2() {
        Cards cards = new Cards();
        cards.add(CardDeck.of(CardNumber.ACE, Figure.CLOVER));
        cards.add(CardDeck.of(CardNumber.KING, Figure.CLOVER));

        int expected = 21;
        assertThat(cards.getSum()).isEqualTo(expected);
    }
}
