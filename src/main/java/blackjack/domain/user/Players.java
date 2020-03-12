package blackjack.domain.user;

import blackjack.domain.result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String MAX_PLAYER_ERR_MSG = "플레이어는 최대 %d명입니다.";
    private static final int MAX_PLAYER = 5;

    private final List<Player> players;

    public Players(List<String> names) {
        Objects.requireNonNull(names, NULL_ERR_MSG);

        if (names.size() > MAX_PLAYER) {
            throw new IllegalArgumentException(String.format(MAX_PLAYER_ERR_MSG, MAX_PLAYER));
        }

        players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
    }


    public void computeResult(Dealer dealer) {
        for (Player player : players) {
            player.createResult(dealer);
        }
    }

//    public List<Result> computeResult2(Dealer dealer) {
//        for (Player player : players) {
//            player.createResult2(dealer);
//        }
//    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Result> getResult() {
        return players.stream()
                .map(Player::getResult)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}