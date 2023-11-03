package lotto.domain.lotto;

enum LottoExceptionMessages {

    INVALID_NUMBERS_COUNT(String.format(
            "로또 번호의 개수는 %d개이어야 합니다.",
            LottoConfig.NUMBERS_COUNT.getValue()
    ));

    private final String message;

    LottoExceptionMessages(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }

}
