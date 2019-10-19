import java.util.HashMap;

/**
 * 其他算法
 * 
 * @author zzy
 *
 */
public class Algorithm_Other {

	public static void main(String[] args) {

	}

	/******************** 最近最少使用LRU算法 ********************/

	class LRU<K, V> {

		private final int SIZE;
		private HashMap<K, Node<K, V>> map;

		private Node<K, V> first;
		private Node<K, V> last;

		public LRU(int size) {
			SIZE = size;
			map = new HashMap<>();
		}

		public void put(K key, V val) {
			Node<K, V> node = getNode(key);
			if (node == null) {
				if (map.size() >= SIZE) {
					map.remove(last.key);
					removeLast();
				}
				node = new Node<K, V>();
				node.key = key;
			}
			node.val = val;
			moveToFirst(node);
			map.put(key, node);
		}

		public V get(K key) {
			Node<K, V> node = getNode(key);
			if (node == null) {
				return null;
			}
			moveToFirst(node);
			return node.val;
		}

		private Node<K, V> getNode(K key) {
			return map.get(key);
		}

		private void moveToFirst(Node<K, V> node) {
			if (node == first) {
				return;
			}
			if (node.prev != null) {
				node.prev.next = node.next;
			}
			if (node.next != null) {
				node.next.prev = node.prev;
			}
			if (node == last) {
				last = last.prev;
			}
			if (first == null || last == null) {
				first = last = node;
				return;
			}
			node.next = first;
			first.prev = node;
			first = node;
			node.prev = null;
		}

		private void removeLast() {
			if (last != null) {
				last = last.prev;
				if (last == null) {
					first = null;
				} else {
					last.next = null;
				}
			}
		}

	}

	class Node<K, V> {
		public Node<K, V> prev;
		public Node<K, V> next;
		public K key;
		public V val;
	}

}
