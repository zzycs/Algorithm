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
	 * 快速排序
	 */
	static ListNode quickSort(ListNode head) {
		if (head == null || head.next == null) return head;
		// 定义三个新的链表头
		ListNode ltHead = new ListNode(0);
		ListNode eqHead = new ListNode(0);
		ListNode gtHead = new ListNode(0);
		// 定义指针
		ListNode temp = head, lt = ltHead, eq = eqHead, gt = gtHead;
		// 放入链表
		while (temp != null) {
			if (temp.value < head.value) {
				lt.next = temp;
				lt = lt.next;
			} else if (temp.value > head.value) {
				gt.next = temp;
				gt = gt.next;
			} else {
				eq.next = temp;
				eq = eq.next;
			}
			temp = temp.next;
		}
		// 截断
		lt.next = eq.next = gt.next = null;
		// 递归
		ListNode p1 = quickSort(ltHead.next);
		ListNode p2 = quickSort(eqHead.next);
		ListNode p3 = quickSort(gtHead.next);
		return merge1(merge1(p1, p2), p3);
	}

	/**
	 * 归并排序
	 */
	static ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) return head;
		// 定义指针
		ListNode prev = null, slow = head, fast = head;
		// 分割
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			head = head.next.next;
		}
		// 截断
		prev.next = null;
		// 递归
		ListNode p1 = mergeSort(head);
		ListNode p2 = mergeSort(slow);
		return merge1(p1, p2);
	}

	/******************** 合并 ********************/

	/**
	 * 合并两个排序的链表
	 */
	static ListNode merge1(ListNode list1, ListNode list2) {
		// 定义新的链表头
		ListNode head = new ListNode(0);
		ListNode temp = head;
		// 放入链表
		while (list1 != null && list2 != null) {
			if (list1.value < list2.value) {
				temp.next = list1;
				list1 = list1.next;
			} else {
				temp.next = list2;
				list2 = list2.next;
			}
			temp = temp.next;
		}
		// 补全
		if (list1 != null) temp.next = list1;
		if (list2 != null) temp.next = list2;
		return head.next;
	}

	/**
	 * 递归合并两个排序的链表
	 */
	static ListNode merge2(ListNode list1, ListNode list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		if (list1.value < list2.value) {
			list1.next = merge2(list1.next, list2);
			return list1;
		} else {
			list2.next = merge2(list1, list2.next);
			return list2;
		}
	}

	/**
	 * 合并K个排序的链表
	 */
	static ListNode mergeLists(ListNode[] lists) {
		return mergeListsHelper(lists, 0, lists.length - 1);
	}

	/**
	 * 切分合并
	 */
	static ListNode mergeListsHelper(ListNode[] lists, int low, int high) {
		if (low == high) return lists[low];
		if (low < high) {
			int mid = (low + high) / 2;
			ListNode p1 = mergeListsHelper(lists, low, mid);
			ListNode p2 = mergeListsHelper(lists, mid + 1, high);
			return merge2(p1, p2);

		}
		return null;
	}

	/******************** 查找 ********************/

	/**
	 * 两个链表的第一个公共结点
	 */
	static ListNode findFirstCommonNode(ListNode list1, ListNode list2) {
		ListNode p1 = list1;
		ListNode p2 = list2;
		while (p1 != p2) {
			p1 = p1 == null ? list1 : p1.next;
			p2 = p2 == null ? list2 : p2.next;
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