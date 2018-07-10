package _01.basic.tree;

/*
트리의 부모 찾기

문제
루트 없는 트리가 주어진다. 이 때, 트리의 루트를 1이라고 정했을 때,
각 노드의 부모를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다.
둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.

출력
첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.

예제 입력 1
7
1 6
6 3
3 5
4 1
2 4
4 7

예제 출력 1
4
6
1
3
1
4

예제 입력 2
12
1 2
1 3
2 4
3 5
3 6
4 7
4 8
5 9
5 10
6 11
6 12

예제 출력 2
1
1
2
3
3
4
4
5
5
6
6
*/

import java.util.*;

public class _11725_FindParent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodeCnt = sc.nextInt();

        int [] tree = new int[nodeCnt+1];
        boolean [] hasVisited = new boolean[nodeCnt+1];

        List<Integer> [] inputList = (List<Integer>[]) new ArrayList[nodeCnt+1];

        for( int i = 0; i <= nodeCnt; ++i ) {
            tree[i] = i;
            inputList[i] = new ArrayList<Integer>();
        }

        for( int i = 1; i < nodeCnt; ++i ) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            inputList[from].add(to);
            inputList[to].add(from);
        }

        makeTree( 1, inputList, tree, hasVisited );

        for( int i = 2; i <= nodeCnt; ++i ) {
            System.out.println(tree[i]);
        }

    }

    private static void makeTree( int curNode, List<Integer> [] inputList, int[] tree, boolean[] hasVisited ) {

        if( hasVisited[curNode] ) {
            return;
        }

        hasVisited[curNode] = true;

        for( int i : inputList[curNode] ) {
            if( !hasVisited[i] ) {
                tree[i] = curNode;
                makeTree(i, inputList, tree, hasVisited);
            }
        }
    }
}