package leecode;

//该题考察的是链表数据结构的读与写，即遍历，取值，赋值
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //我的
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3=null;
        int ex = 0;//
        while ((l1 != null) || (l2 != null)) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int x = a + b;
            x += ex;
            if (x >= 10) {
                x%=10;
                ex = 1;
            } else {
                ex = 0;
            }
            l3 = addInTail(l3, new ListNode(x));
            l1 = l1==null?null:l1.next;
            l2 = l2==null?null:l2.next;
        }
        if(ex == 1){
            l3 = addInTail(l3, new ListNode(ex));
        }
        return l3;
    }
    private static ListNode addInTail(ListNode now, ListNode newNode){
        if(now == null){
            now = newNode;
        }else{
            ListNode next = now;
            while(next.next!=null){
                next = next.next;
            }
            next.next = newNode;
        }
        return now;
    }

    //最佳
    public static ListNode addTwoNumbers_best(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1),p=dummy; //定义一个虚假节点解决链表初始化的问题，方便遍历(可以在循环体里写p.next=***,p=p.next，否则不好遍历)
        int n = 0;//进位标志
        while(l1!=null || l2!=null || n!=0){
            int sum = n;
            if(l1!=null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                sum += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(sum%10);
            n = sum/10;
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        ListNode l3 = addTwoNumbers(l1, l2);
        System.out.println(l3);
    }
}
