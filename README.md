## 전공 수업 모바일 프로그래밍 텀프로젝트

재미있는 주제를 선정하고 싶었고 대학생들에게 빼놓을 수 없는 술과 관련하여
제공해줄 수 있는 서비스에 초점을 두었다.

술자리를 옮길 때 어디 술집을 갈지, 어떤 안주를 먹을지
사소한 고민거리를 해결할 수 있는 `술안주 월드컵` 앱을 구현하고자 한다.
거기에 우리학교 학생들을 위해 결승한 술안주를 판매하는
학교 주변 술집 정보가 적힌 리스트도 추가적으로 제공할 예정이다.

술안주 월드컵은 16강, 8강, 4강 이렇게 세 가지 라운드 중에 하나를 선택할 수 있다.

<br>

## 🎨 UI 디자인
![](https://velog.velcdn.com/images/ssuzyn/post/58d71656-c685-43e2-9649-3af86d6100b4/image.png)
![](https://velog.velcdn.com/images/ssuzyn/post/a7751057-6200-4966-8f90-84843412a08b/image.png)

<br>

## 🎯 월드컵 게임 로직 설계

### 초기 설정
- 술안주 목록을 데이터베이스에서 가져온다.
- 사용자가 선택한 라운드 수에 맞게 랜덤한 술안주 메뉴를 불러온다.
- 사용자가 선택한 라운드에 따라 토너먼트 구조를 만든다.

### 게임 플로우
- 사용자는 두 개의 술안주 메뉴 중 하나를 선택한다.
- 선택한 술안주는 다음 라운드로 이동한다.
- 위 과정을 반복하여 최종 우승 술안주를 결정한다.

### 최종 선택된 술안주 출력
- 사용자에게 최종적으로 선택된 술안주를 보여준다.
- 해당 술안주를 판매하는 술집 정보를 제공한다.