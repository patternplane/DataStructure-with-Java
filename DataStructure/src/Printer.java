
/**
 * 
 * @author patternplane
 * '프로그래머스' 사이트의 문제 '프린터' 풀이
 * programmers.co.kr/learn/courses/30/lessons/42587
 *
 */

class Solution {
    public int solution(int[] priorities, int location) {
        
        // 주어진 배열을 바로 원형 큐로 사용합니다.
        deformedCircleQueue queue_P = new deformedCircleQueue(priorities);
        
        int order = 0;
        int currentP;
        while (true) {
            // 1. 대기목록에서 꺼내기
            currentP = queue_P.getFirst();
            if (!queue_P.delete())
                // error
                ;
            location--; // 해당 항목의 위치도 이동함
            
            // 2. 중요도가 가장 높다면 인쇄하기
            if (queue_P.is_max(currentP)) {
                // 현재 항목 인쇄됨
                order++;
                
                // 인쇄된 항목이 원하는 항목이었다면 처리 완료
                if (location == -1)
                    break;
            }
            
            // 3. 중요도가 가장 높지 않다면 뒤로가기
            else {
                // 현재 항목 뒤로 감
                queue_P.add(currentP);
                
                // 만약 현재 항목이 문제의 항목이었다면 위치도 반영
                if (location == -1)
                    location += queue_P.getCurrentSize();
            }
        }
        
        return order;
    }
}

class deformedCircleQueue {
    // 주어진 배열을 바로 원형 큐로 사용하기 위하여 rear, count 값을 사용합니다.
    int[] array;
    int size;
    int rear;
    int count;
    
    deformedCircleQueue(int[] array) {
        this.array = array;
        size = array.length;
        count = size;
        rear = -1;
    }
    
    int getCurrentSize() {
        return count;
    }
    
    void add(int item) {
        count++;
        array[(rear + count)%size] = item;
    }
    
    int getFirst() {
        return array[(rear+1)%size];
    }
    
    boolean delete() {
        if (count == 0) 
            return false;
        
        rear++; 
        count--;
        return true;
    }
    
    boolean is_max(int item) {
        for (int i = 1; i <= count; i++) 
            if (item < array[(rear + i)%size])
                return false;
        return true;
    }
}