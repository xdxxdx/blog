package com.xdx.learn;

public class LinkList<E> {
	int size;//链表长度
	Node<E> head;//头节点,假设头节点不存储数据 
	//节点类
	class Node<E>{
		E item;
        Node<E> next;
        public Node(E item,Node<E> next){
        	this.item=item;
        	this.next=next;
        }
	}
	Node<E> get(int index){
		Node<E>current=head;//指向头节点
		if(index>=0&&index<size){
			for(int i=0;i<index;i++){
				current=current.next;
			}
			return current;
		}else{
			return null;
		}
	}
	boolean add(int index,E e) throws Exception{
		Node<E>current=head;//指向头节点
		if(index>=0&&index<size){
			//定位到插入的地方
			for(int i=0;i<index;i++){
				current=current.next;
			}
			Node<E>node =new Node<E>(e, null);
			//执行插入操作
			node.next=current.next;
			current.next=node;
			size++;
			return true;
		}else{
			throw new Exception("error");
		}
	}
	boolean remove(int index) throws Exception{
		Node<E>current=head;//指向头节点
		if(index>=0&&index<size){
			//定位到删除的地方，要删除的是current后面的那个元素
			for(int i=0;i<index;i++){
				current=current.next;
			}
			Node<E>temp =current.next;//要删除的是current后面的那个元素
			//执行删除
			current.next=temp.next;
			temp=null;//去除引用，便于垃圾回收
			size--;
			return true;
		}else{
			throw new Exception("error");
		}
	}
}
