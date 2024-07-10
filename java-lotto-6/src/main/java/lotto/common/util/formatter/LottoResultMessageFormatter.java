package lotto.common.util.formatter;

import lotto.dto.response.LottoRankResponse;

public class LottoResultMessageFormatter {
    public static String lottoResultMessage(final LottoRankResponse lottoRankResponse) {
        String matchMessage = getMatchMessage(lottoRankResponse);
        String prizeMessage = formatPrize(lottoRankResponse.getPrize());

        return String.format("%s (%s원) - ", matchMessage, prizeMessage);
    }

    private static String getMatchMessage(LottoRankResponse lottoRankResponse) {
        StringBuilder message = new StringBuilder();
        message.append(lottoRankResponse.getMatchCount()).append("개 일치");

        if (lottoRankResponse.isBonusMatch()) {
            message.append(", 보너스 볼 일치");
        }

        return message.toString();
    }

    private static String formatPrize(int prize) {
        return String.format("%,d", prize);
    }
}

