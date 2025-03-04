# 🍀 로또

### [🚀 기능 요구 사항](https://github.com/SJ70/woowacourse-java-lotto-6#-%EA%B8%B0%EB%8A%A5-%EC%9A%94%EA%B5%AC-%EC%82%AC%ED%95%AD)

### [🎯 프로그래밍 요구 사항](https://github.com/SJ70/woowacourse-java-lotto-6#-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EC%9A%94%EA%B5%AC-%EC%82%AC%ED%95%AD)

---
# ⚙️ 기능 목록

### ✅ 로또 구입 금액 입력

- [x] 숫자인가?
- [x] 양수인가?
- [x] 1000으로 나누어 떨어지는가?
- [x] 최대 구입 금액 제한
- [x] 예외 발생 시 재입력

### ✅ 로또 번호 발행

- [x] `1~45` 내의 무작위 숫자를 `6`개 선택

### ✅ 당첨 번호 입력

- [x] `,`를 기준으로 `split`
- [x] `6`개인가?
- [x] 숫자인가?
- [x] `1~45` 범위 내의 숫자인가?
- [x] 중복 검증
- [x] 예외 발생 시 재입력

### ✅ 보너스 번호 입력

- [x] 숫자인가?
- [x] `1~45` 범위 내의 숫자인가?
- [x] 당첨 번호와의 중복 검증
- [x] 예외 발생 시 재입력

### ✅ 발행된 로또 번호 출력
- [x] `n개를 구매했습니다.` 출력
- [x] 번호들을 형식에 맞게 출력
  - [x] 정렬하여 출력

### ✅ 당첨 번호와 로또 번호를 비교
- [x] 당첨 번호와 로또 번호의 일치하는 숫자의 수를 판별
- [x] 보너스 번호가 로또 번호에 존재하는지 판별

### ✅ 당첨 여부 조회
- [x] 일치 정보로부터 당첨 여부 판단

### ✅ 당첨 통계 출력
- [x] 등수 별 출력
  - [x] 돈 단위 포맷

### ✅ 수익률 출력
- [x] 총 수익률 출력
  - [x] `당첨 금액` / `로또 구입 금액` * `100`
  - [x] 소수점 둘째 자리에서 반올림

### ✅ 예외 처리
- [x] `[ERROR]` 로 시작하는 에러 메시지를 출력

---

# 도메인

## 🍀 로또

### 📚 WinningLottoNumbers

- 📄 `Lotto` : 당첨 번호들
- 📄 `LottoNumber` : 보너스 번호
- ✔️ 보너스 번호 중복 검증
- 🛠️ 로또에 대한 일치 정보를 `LottoNumberMatchDTO`로 반환

### 📘 Lotto

- 📄 `List<LottoNumber>` : 로또 번호들
- ✔️ 개수 검증
- ✔️ 중복 검증

### 📄 LottoNumber

- 📄 `int` : 로또 번호
- ✔️ 범위 검증

### 🏭 LottoNumbersGenerator

- 🛠️ 전략을 통해 로또 번호들을 생성

### 📦 LottoNumberMatchDTO

- 📄 `int` : 일치하는 번호의 수
- 📄 `boolean` 보너스 번호 포함 여부

## 💵 로또 구매 금액

### 💵 PurchasingMoney

- ✔️ 단위 검증
- ✔️ 양수 검증
- ✔️ 구매액 한도 검증
- 🛠️ 금액에 대한 로또 수량 반환

## 🏆 당첨

### 🏆 WinState

- 📄 `enum` : 당첨 종류
- 🛠️ 일치 정보에 따른 당첨 종류 반환

### 🏆 WinningStatistics

- 📄 `Map<WinState, Integer>` : 당첨 종류와 횟수를 저장
- 🛠️ 당첨 정보들을 취합해서 반환

## 💰 당첨금

### 💰 Prize

- 📄 `long` : 당첨금 총액
- 🛠️ 당첨 여부에 알맞는 당첨금 총액 계산
- 🛠️ 수익률 반환

# 뷰

## 📝 InputView

- 🛠️ 숫자 입력
- 🛠️ 숫자들 입력

## 🖥️ OutputView

- 🛠️ 메시지 출력
- 🛠️ 에러 메시지 출력