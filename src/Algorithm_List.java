/**
 * 链表算法
 * 
 * @author zzy
 *
 */
public class Algorithm_List {

	public static void main(String[] args) {

	}

	/**
	 * 两个链表的第一个公共结点
	 */
	static Node_List FindFirstCommonNode(Node_List pHead1, Node_List pHead2) {
		Node_List p1 = pHead1;
		Node_List p2 = pHead2;
		while (p1 != p2) {
			p1 = p1 == null ? pHead1 : p1.next;
			p2 = p2 == null ? pHead2 : p2.next;
		}
		return p1;
	}

	/**
	 * 链表中倒数第k个结点
	 */
	static Node_List FindKthToTail(Node_List head, int k) {
		if (head == null || k == 0) return null;
		Node_List temp = head;
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

	/**
	 * 合并两个排序的链表
	 */
	static Node_List Merge(Node_List list1, Node_List list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		Node_List head = null;
		Node_List temp = null;
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

	/**
	 * 反转链表
	 */
	static Node_List ReverseList(Node_List head) {
		if (head == null) return null;
		Node_List prev = null;
		Node_List next = null;
		while (head != null) {
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	/**
	 * 链表中检测是否有环
	 */
	static Boolean IsLoop(Node_List pHead) {
		if (pHead == null || pHead.next == null) return false;
		Node_List p1 = pHead;
		Node_List p2 = pHead;
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
	static Node_List EntryNodeOfLoop(Node_List pHead) {
		if (pHead == null || pHead.next == null) return null;
		Node_List p1 = pHead;
		Node_List p2 = pHead;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				p1 = pHead;
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
class Node_List {
	int value;
	Node_List next;

	Node_List(int value) {
		this.value = value;
	}
}