package bjoj._02_intermediate01.divideandconquer;

/*
Z
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	    512 MB	    10896	4719	3217	    42.853%
문제
한수는 2차원 배열 (항상 2^N * 2^N 크기이다)을 Z모양으로 탐색하려고 한다.
예를 들어, 2*2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.



만약, 2차원 배열의 크기가 2^N * 2^N라서 왼쪽 위에 있는 칸이 하나가 아니라면,
배열을 4등분 한 후에 (크기가 같은 2^(N-1)로) 재귀적으로 순서대로 방문한다.

다음 예는 2^2 * 2^2 크기의 배열을 방문한 순서이다.


N이 주어졌을 때, (r, c)를 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
다음 그림은 N=3일 때의 예이다.


입력
첫째 줄에 N r c가 주어진다. N은 15보다 작거나 같은 자연수이고, r과 c는 0보다 크거나 같고, 2^N-1보다 작거나 같은 정수이다

출력
첫째 줄에 문제의 정답을 출력한다.

예제 입력 1
2 3 1
예제 출력 1
11

예제 입력 2
3 7 7
예제 출력 2
63
*/
import java.util.Scanner;

public class _1074_Z {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int targetRow = sc.nextInt();
        int targetCol = sc.nextInt();

        int sum = 0;
        int rowPosition = 0;
        int colPosition = 0;
        size = (int)Math.pow(2, size);

        while (size > 1) {
            size >>= 1;

            // 현재 위치를 (0,0) (0,1) (1,0) (1,1) 중 어디인지 파악
            rowPosition = (targetRow/size);
            colPosition = (targetCol/size);

            // 현재 위치에 따라 방문 위치 누적 합계 구하기
            sum += (rowPosition * size * size) * 2 + colPosition * size * size;

            // 다음 좌표 값 구하기
            targetRow %= size;
            targetCol %= size;
        }

        System.out.println(sum);
    }
}