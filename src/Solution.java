import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** TEST CASE **/
/*
10
1 42
2
1 14
3
1 28
3
1 60
1 78
2
2
*/
public class Solution {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();

            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}

class MyQueue<K> {
    List<Integer> list = new ArrayList<>();

    public void enqueue(int num) {
        list.add(num);
    }

    public void dequeue() {
        if (!list.isEmpty()) {
            list.remove(0);
        }
    }

    public int peek() {
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return -1;
    }
}

