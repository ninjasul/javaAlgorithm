package _01.basic.graph;

/*
이분 그래프

문제
그래프의 정점의 집합을 둘로 분할하여,
각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때,
그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.

그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K(2≤K≤5)가 주어진다. 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V(1≤V≤20,000)와 간선의 개수 E(1≤E≤200,000)가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호가 빈 칸을 사이에 두고 주어진다.

출력
K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

예제 입력 1
2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2

예제 출력 1
YES
NO
*/

import java.util.*;

public class _01707_BipartiteGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCnt = sc.nextInt();

        for( int i = 0; i < testCnt; ++i ) {
            int vCnt = sc.nextInt();
            int eCnt = sc.nextInt();

            List<List<Integer>> edgeLists = new ArrayList<List<Integer>>(vCnt+1);

            for( int j = 0; j <= vCnt; ++j ) {
                List<Integer> curEdgeList = new ArrayList<Integer>();
                edgeLists.add(curEdgeList);
            }

            int start = 0;
            for( int j = 0; j < eCnt; ++j ) {

                int from = sc.nextInt();
                int to = sc.nextInt();

                if( j == 0 ) {
                    start = from;
                }

                List<Integer> curEdgeList = edgeLists.get(from);
                curEdgeList.add(to);

                curEdgeList = edgeLists.get(to);
                curEdgeList.add(from);
            }

            int [] visitColors = new int[vCnt+1];

            for( int j = 1; j <= vCnt; ++j ) {
                goDfs(j, edgeLists, 1, visitColors);
            }

            System.out.println( isBipartiteGraph(vCnt, edgeLists, visitColors) ? "YES" : "NO" );
        }
    }

    private static void goDfs( int v, List<List<Integer>> edgeLists, int visitColor, int [] visitColors ) {

        if( visitColors[v] != 0 ) {
            return;
        }

        visitColors[v] = visitColor;

        for( int to : edgeLists.get(v) ) {
            if( visitColors[to] == 0 ) {
                goDfs( to, edgeLists, -visitColor, visitColors );
            }
        }
    }

    private static boolean isBipartiteGraph( int vCnt, List<List<Integer>> edgeLists, int [] visitColors ) {

        for( int i = 1; i <= vCnt; ++i ) {
            List<Integer> curEdgeList = edgeLists.get(i);

            for( int to : curEdgeList ) {
                if( visitColors[i] == visitColors[to] )
                    return false;
            }
        }

        return true;
    }
}