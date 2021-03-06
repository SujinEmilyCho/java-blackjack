package blackjack.controller;

import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayersFactory;
import blackjack.domain.participant.attribute.Money;
import blackjack.domain.participant.attribute.Name;
import blackjack.domain.result.model.ProfitDto;
import blackjack.domain.result.outcome.BettingResultResolver;
import blackjack.domain.result.outcome.PlayerResultBundle;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BettingMode implements ModeStrategy<BettingPlayer> {

    @Override
    public Players<BettingPlayer> createPlayers(List<Name> names) {
        List<Money> bettingMoneys = createMoneys(names);
        return PlayersFactory.createBettingPlayers(names, bettingMoneys);
    }

    private List<Money> createMoneys(List<Name> names) {
        return InputView.enterBettingMoney(names).stream()
                .map(Money::new)
                .collect(Collectors.toList());
    }

    @Override
    public void showResult(Players<BettingPlayer> players, Dealer dealer) {
        PlayerResultBundle<BettingPlayer, Double, Double> playerResultBundle
                = new PlayerResultBundle<>(players, dealer, new BettingResultResolver());

        List<ProfitDto> playerDtos = playerResultBundle.stream()
                .map(result -> new ProfitDto(result.getName(), result.showPlayerResult()))
                .collect(Collectors.toList());

        OutputView.printFinalProfit(dealer.name(), playerResultBundle.computeDealerResult(), playerDtos);
    }
}
