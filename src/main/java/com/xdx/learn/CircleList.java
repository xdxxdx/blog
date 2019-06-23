package com.xdx.learn;

import com.xdx.learn.LinkList.Node;

public class CircleList<E> {
	private Node<E> head;
	private int size;
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	class Node<E> {
		E item;
		Node<E> next;

		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}

		public E getItem() {
			return item;
		}

		public void setItem(E item) {
			this.item = item;
		}
		
	}

	public CircleList() {
		Node<E> headTmp = new Node<E>(null, null);// 构造的时候，初始化
		head = headTmp;
	}

	public void add(int index, E e) throws Exception {
		Node<E> node = new Node<E>(e, null);
		if (index < size-1 && index > 0) {
			// 插入任意节点,除了尾巴节点
			Node<E> current = head;
			int j = 0;
			while (j < index) {
				current = current.next;
				j++;
			}
			node.next = current.next;
			current.next = node;
			size++;
		} else {
			throw new Exception("边界错误");
		}

	}

	/**
	 * 添加元素到头部
	 * 
	 * @param e
	 */
	public void add2Head(E e) {
		Node<E> node = new Node<E>(e, null);
		// 还没元素
		if (head.next == null) {
			head.next = node;
			node.next=node;
			size++;
		} else {
			// 已经有元素了
			node.next = head.next;
			head.next = node;
			size++;
			Node<E> current = head;
			for (int i = 0; i < size; i++) {
				current = current.next;
			}
			current.next = head.next;// 首尾相接
		}
	}

	public void add2Last(E e) {
		Node<E> node = new Node<E>(e, null);
		if (head.next == null) {
			head.next = node;
			size++;
			node.next=node;
		} else {
			Node<E> current = head;
			for (int i = 0; i < size; i++) {
				current = current.next;
			}
			current.next = node;
			node.next = head.next;
			size++;
		}
	}

	public Node<E> get(int index) throws Exception {
		if (index < size) {
			Node<E> current = head;
			for (int i = 0; i <=index; i++) {
				current = current.next;
			}
			return current;
		} else {
			throw new Exception("边界错误");
		}

	}

	public static void main(String args[]) throws Exception {
		CircleList<Integer> circleList = new CircleList<Integer>();
		circleList.add2Head(1);//从头部加入
		circleList.add2Head(2);
		circleList.add2Head(3);
		circleList.add2Head(4);
		circleList.add2Last(5);//在尾部加入
		circleList.add(2, 8);//从中间插入
		for(int i=0;i<circleList.getSize();i++){
			System.out.println(circleList.get(i).getItem());
		}
	}
}
