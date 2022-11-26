package graphs;

import java.util.ArrayList;
import java.util.List;

class NumberOfIslands {
    public int numIslands(char[][] grid) {
        //This tracks number of islands
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        int i, j;

        for(i=0; i<m; i++) {
            for(j=0; j<n; j++) {
                //The value 1 represents that a)it has not been visited and b)its value is not 0.
                if(grid[i][j] == '1') {
                    numIslandsUtil(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    //This method does dfs from i,j
    private void numIslandsUtil(char[][] grid, int i, int j) {
        //Mark it visited
        grid[i][j] = '2';
        List<Pair> neighbours = getUnvisitedNeighbours(grid, i, j);
        for(Pair neighbour: neighbours) {
            numIslandsUtil(grid, neighbour.rowNo, neighbour.colNo);
        }
    }

    private List<Pair> getUnvisitedNeighbours(char[][] grid, int i, int j) {
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        List<Pair> pairs = new ArrayList<>();

        int k;
        int candidatei, candidatej;
        for(k=0; k<4; k++) {
            candidatei = i + dx[k];
            candidatej = j + dy[k];
            if(validPair(candidatei, candidatej, grid)) {
                if(grid[candidatei][candidatej] == '1') {
                    Pair pair = new Pair(candidatei, candidatej);
                    pairs.add(pair);
                }
            }
        }
        return pairs;
    }

    private boolean validPair(int candidatei, int candidatej, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if(candidatei < 0 || candidatei >= m || candidatej < 0 || candidatej >= n) return false;
        return true;
    }
}

class Pair {
    public int rowNo;
    public int colNo;

    public Pair(int i, int j) {
        this.rowNo = i;
        this.colNo = j;
    }
}
