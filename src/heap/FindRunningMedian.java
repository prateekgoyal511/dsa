package heap;

import java.util.Collections;
import java.util.PriorityQueue;

class FindRunningMedian {
    private PriorityQueue<Integer> leftPQ;
    private PriorityQueue<Integer> rightPQ;

    public FindRunningMedian() {
        //This will hold left half of the elements in a maxheap
        leftPQ = new PriorityQueue<>(Collections.reverseOrder());
        //This will hold right half of the elements in a minheap
        rightPQ = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(leftPQ.size() == 0) {
            leftPQ.add(num);
            return;
        }

        int peekLeft = leftPQ.peek();
        if(num < peekLeft) {
            leftPQ.add(num);
            rebalance();
        } else {
            rightPQ.add(num);
            rebalance();
        }
    }

    private void rebalance() {
        int leftSize = leftPQ.size();
        int rightSize = rightPQ.size();
        int diff = leftSize - rightSize;
        int elementToShift;
        //If difference is just 1
        if(Math.abs(diff) <= 1) return;
        //If difference is greater than 1 and left one is bigger
        if(leftSize > rightSize) {
            elementToShift = leftPQ.poll();
            rightPQ.add(elementToShift);
        } else {
            elementToShift = rightPQ.poll();
            leftPQ.add(elementToShift);
        }
    }

    public double findMedian() {
        int leftSize = leftPQ.size();
        int rightSize = rightPQ.size();
        int medianPart1, medianPart2;
        if(leftSize == rightSize) {
            medianPart1 = leftPQ.peek();
            medianPart2 = rightPQ.peek();
            return (double)(medianPart1+medianPart2)/2;
        } else if(leftSize > rightSize) {
            return leftPQ.peek();
        } else {
            return rightPQ.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
