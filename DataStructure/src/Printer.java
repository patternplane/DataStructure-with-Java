
/**
 * 
 * @author patternplane
 * '���α׷��ӽ�' ����Ʈ�� ���� '������' Ǯ��
 * programmers.co.kr/learn/courses/30/lessons/42587
 *
 */

class Solution {
    public int solution(int[] priorities, int location) {
        
        // �־��� �迭�� �ٷ� ���� ť�� ����մϴ�.
        deformedCircleQueue queue_P = new deformedCircleQueue(priorities);
        
        int order = 0;
        int currentP;
        while (true) {
            // 1. ����Ͽ��� ������
            currentP = queue_P.getFirst();
            if (!queue_P.delete())
                // error
                ;
            location--; // �ش� �׸��� ��ġ�� �̵���
            
            // 2. �߿䵵�� ���� ���ٸ� �μ��ϱ�
            if (queue_P.is_max(currentP)) {
                // ���� �׸� �μ��
                order++;
                
                // �μ�� �׸��� ���ϴ� �׸��̾��ٸ� ó�� �Ϸ�
                if (location == -1)
                    break;
            }
            
            // 3. �߿䵵�� ���� ���� �ʴٸ� �ڷΰ���
            else {
                // ���� �׸� �ڷ� ��
                queue_P.add(currentP);
                
                // ���� ���� �׸��� ������ �׸��̾��ٸ� ��ġ�� �ݿ�
                if (location == -1)
                    location += queue_P.getCurrentSize();
            }
        }
        
        return order;
    }
}

class deformedCircleQueue {
    // �־��� �迭�� �ٷ� ���� ť�� ����ϱ� ���Ͽ� rear, count ���� ����մϴ�.
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