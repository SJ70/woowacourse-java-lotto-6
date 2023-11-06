package lotto.controller;

import java.util.List;
import lotto.domain.cash.Cash;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumbersGenerator;
import lotto.domain.lotto.WinningLottoNumbers;
import lotto.domain.lotto.strategy.PickNumbersStrategy;
import lotto.domain.lotto.strategy.PickRandomNumbersStrategy;
import lotto.domain.prize.Prize;
import lotto.domain.shop.LottoShop;
import lotto.domain.win.WinStatesCounter;
import lotto.dto.LottoNumbersDTO;
import lotto.dto.WinStateInformationDTO;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    private final PickNumbersStrategy pickNumbersStrategy = new PickRandomNumbersStrategy();
    private final LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator(pickNumbersStrategy);

    private final LottoShop lottoShop = new LottoShop();

    private Cash purchaseCash;
    private int lotteriesCount;
    private WinningLottoNumbers winningLottoNumbers;
    private List<Lotto> lotteries;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputPurchaseCash();
        generateLotteries();
        inputWinningLottoNumbers();
        printResult();
    }

    private void inputPurchaseCash() {
        // 구입금액을 입력해 주세요.
        try {
            purchaseCash = new Cash(inputView.inputNumber());
            lotteriesCount = lottoShop.countPurchasableAmount(purchaseCash.amount());
        } catch (IllegalArgumentException e) {
            outputView.print(e.getMessage());
            inputPurchaseCash();
        }
    }

    private void generateLotteries() {
        // n개를 구매했습니다.
        List<LottoNumbersDTO> lottoNumbersDTOs = lottoNumbersGenerator.generateByCount(lotteriesCount);
        lotteries = lottoNumbersDTOs.stream()
                .map(LottoNumbersDTO::numbers)
                .map(Lotto::new)
                .toList();
        // 발행번호들 출력
    }

    private void inputWinningLottoNumbers() {
        winningLottoNumbers = new WinningLottoNumbers(inputWinningNumbers(), inputBonusNumber());
    }

    private List<Integer> inputWinningNumbers() {
        // 당첨 번호를 입력해 주세요.
        try {
            return inputView.inputNumbers();
        } catch (IllegalArgumentException e) {
            outputView.print(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private int inputBonusNumber() {
        // 보너스 번호를 입력해 주세요.
        try {
            return inputView.inputNumber();
        } catch (IllegalArgumentException e) {
            outputView.print(e.getMessage());
            return inputBonusNumber();
        }
    }

    private void printResult() {
        WinStatesCounter winStatesCounter = new WinStatesCounter(winningLottoNumbers, lotteries);
        List<WinStateInformationDTO> winStateInformationDTOs = winStatesCounter.getWinStateInformationDTOs();
        // 당첨 통계\n---
        // 통계 출력
        Prize prize = Prize.from(winStateInformationDTOs);
        double yield = prize.getYield(purchaseCash);
        // 수익률 출력
    }

}
