# Java Chess Game 

## 개발환경

- Java 17.0.3.1   
- JUnit5(Test Framework)
- guava-31.1-jre.jar  
- Intellij(IDE)

## Version Upgrade

[7/14] 추상 클래스 Tile 생성 및 추상 메소드 구현  
[7/14] Immutability : Tile 클래스를 immutable 하게 구현  
[7/15] 유효한 빈 타일을 새로 생성하고 싶을 때 마다 새로 생성하는 대신 하나를 검색.
캐시에서 조회하는 방법을 사용     
[7/20] abstract class인 Piece class에 기물의 이동을 나타내는 abstract method 정의.
Board와 Move class 생성  
[7/26] concrete class Knight 구현. 타일이 점유되어 있지 않다면 새로운 이동이 추가. 타일이 점유되어 있고  
현재 Knight의 색과 이동하려는 기물의 색이 다르다면 적이므로 새로운 이동 (공격) 을 추가.  
[8/1] Knight는 체스 판에서 첫 번째, 두 번째, 일곱 번째, 여덟 번째 줄에 위치할 때 이동 가능한  
Offset 만큼 이동한 위치가 예상과는 다른 (범위를 벗어난) 위치일 수 있다. 이것을 처리한다.  
[8/2] BoardUtils 클래스의 initColumn 함수 구현. 해당 함수로 첫 번째, 두 번째, 일곱 번째, 여덟 번째 줄에 위치할 때  
각각 true를 반환하는 Boolean[] 배열 초기화. Knight class의 Exclusion 함수에서 사용.  
기물의 정상적인 이동과 공격 시 이동을 Move class를 이용해 구현  
[8/6] Piece 를 상속받는 Bishop class 정의. Offset을 이용해 이동 가능한 위치를 계산하고,  
첫 번째와 여덟 번째 줄에 있을 때 예외처리  
[8/14] Piece 를 상속받는 Rook class 정의. Offset을 이용해 이동 가능한 위치를 계산하고,  
첫 번째와 여덟 번째 줄에 있을 때 예외처리  
[8/16] Piece 를 상속받는 Queen class 정의. Queen은 Bishop과 Rook의 Union으로 구현  
[8/17] Piece 를 상속받는 Pawn class 정의. 검은색 Pawn 과 흰색 Pawn의 이동을 표시하기 위해 방향 설정  
[8/18] Pawn이 조건에 따라 두 칸을 이동할 수 있음을 구현.  
[8/23] Pawn의 Attack Move 구현.  
[8/24] Piece 를 상속받는 King class 정의 및 구현.


## References







 