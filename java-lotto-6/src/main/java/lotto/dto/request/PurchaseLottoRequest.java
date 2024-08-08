package lotto.dto.request;

/**
 * 로또 구매 요청 DTO
 */
public class PurchaseLottoRequest {
    private final int money;

    public PurchaseLottoRequest(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "PurchaseLottoRequest{" +
                "money=" + money +
                '}';
    }
}
