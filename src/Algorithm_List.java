/**
 * 链表算法
 * 
 * @author zzy
 *
 */
public class Algorithm_List {

	public static void main(String[] args) {}

	/******************** 排序 ********************/

	/**
	 * 归并排序
	 */
	static ListNode mergeSort(ListNode head) {
		ListNode prev = null, slow = head, fast = head;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			head = head.next.next;
		}
		prev.next = null;
		ListNode p1 = mergeSort(head);
		ListNode p2 = mergeSort(slow);
		return merge(p1, p2);
	}

	/******************** 合并 ********************/

	/**
	 * 合并两个排序的链表
	 */
	static ListNode merge(ListNode list1, ListNode list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		ListNode head = null;
		ListNode temp = null;
		while (list1 != null && list2 != null) {
			if (list1.value < list2.value) {
				if (head == null) {
					head = temp = list1;
				} else {
					temp.next = list1;
					temp = temp.next;
				}
				list1 = list1.next;
			} else {
				if (head == null) {
					head = temp = list2;
				} else {
					temp.next = list2;
					temp = temp.next;
				}
				list2 = list2.next;
			}
		}
		if (list1 != null) temp.next = list1;
		if (list2 != null) temp.next = list2;
		return head;
	}

	/******************** 查找 ********************/

	/**
	 * 两个链表的第一个公共结点
	 */
	static ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
		ListNode p1 = head1;
		ListNode p2 = head2;
		while (p1 != p2) {
			p1 = p1 == null ? head1 : p1.next;
			p2 = p2 == null ? head2 : p2.next;
		}
		return p1;
	}

	/**
	 * 链表中倒数第k个结点
	 */
	static ListNode findKthToTail(ListNode head, int k) {
		if (head == null || k == 0) return null;
		ListNode temp = head;
		while (--k > 0) {
			if (temp.next == null) return null;
			temp = temp.next;
		}
		while (temp.next != null) {
			temp = temp.next;
			head = head.next;
		}
		return head;
	}

	/******************** 反转 ********************/

	/**
	 * 反转链表
	 */
	static ListNode reverseList(ListNode head) {
		if (head == null) return null;
		ListNode prev = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	/******************** 环 ********************/

	/**
	 * 链表中检测是否有环
	 */
	static Boolean isLoop(ListNode head) {
		if (head == null || head.next == null) return false;
		ListNode p1 = head;
		ListNode p2 = head;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 链表中环的入口节点
	 */
	static ListNode findEntryNodeOfLoop(ListNode head) {
		if (head == null || head.next == null) return null;
		ListNode p1 = head;
		ListNode p2 = head;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				p1 = head;
				while (p1 != p2) {
					p1 = p1.next;
					p2 = p2.next;
				}
				return p1;
			}
		}
		return null;
	}
}

/******************** 辅助类 ********************/

/**
 * 链表节点
 */
class ListNode {
	int value;
	ListNode next;

	ListNode(int value) {
		this.value = value;
	}
}