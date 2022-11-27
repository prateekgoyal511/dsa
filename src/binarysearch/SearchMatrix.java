package binarysearch;

class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        return binarySearch(matrix, 0, m*n-1, target);
    }

    private boolean binarySearch(int[][] matrix, int low, int high, int key) {
        int n = matrix.length;
        int m = matrix[0].length;
        int rowNo, colNo;
        int mid;

        if(low>high) return false;

        //rowNo => at least how many rows of m elements are before this index
        //colNo => how much far from first column is this index.
        while(low <= high) {
            mid = low+(high-low)/2;
            rowNo = mid/m;
            colNo = mid%m;
            if(matrix[rowNo][colNo] == key) return true;
            if(matrix[rowNo][colNo] > key) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return false;
    }
}
