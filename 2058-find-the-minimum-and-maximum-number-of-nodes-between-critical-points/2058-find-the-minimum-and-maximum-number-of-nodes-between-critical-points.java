/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = {-1, -1};
        if (head == null || head.next == null || head.next.next == null) {
            return result;
        }
        ListNode previous = head;
        ListNode current = head.next;
        int position = 1;
        int firstCritical = -1;
        int lastCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        while (current.next != null) {
            ListNode next = current.next;
            boolean isCritical =
                    (current.val > previous.val && current.val > next.val) ||
                    (current.val < previous.val && current.val < next.val);
            if (isCritical) {
                if (firstCritical == -1) {
                    firstCritical = position;
                }
                if (lastCritical != -1) {
                    minDistance = Math.min(
                            minDistance,
                            position - lastCritical
                    );
                }
                lastCritical = position;
            }
            previous = current;
            current = next;
            position++;
        }
        if (firstCritical == -1 || firstCritical == lastCritical) {
            return result;
        }
        int maxDistance = lastCritical - firstCritical;
        result[0] = minDistance;
        result[1] = maxDistance;
        return result;
    }
}