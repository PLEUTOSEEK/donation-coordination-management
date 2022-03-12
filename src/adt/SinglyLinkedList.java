/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

/**
 *
 * @author Wong Phey Zhen
 */
public class SinglyLinkedList<T extends Comparable<T>> implements SinglyLinkedListInterface<T>{

    private Node firstNode;
    private Node tail;
    private int dataCount;

    public SinglyLinkedList() {
        this.firstNode = null;
        this.tail = null;
        this.dataCount = 0;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getTail() {
        return tail;
    }

    public int getDataCount() {
        return dataCount;
    }
    
   
    @Override
    public boolean add(T element) {
        Node newNode = new Node(element);	

    if (empty()) {
      firstNode = newNode;
    } else {                        
      Node currNode = firstNode;	
      while (currNode.next != null) { 
        currNode = currNode.next;
      }
      currNode.next = newNode; 
    }

    dataCount++;
    return true;
    }

    @Override
    public boolean add(int newPosition, T newElement) {
       boolean addNode = true;

    if ((newPosition >= 1) && (newPosition <= dataCount + 1)) {
      Node newNode = new Node(newElement);

      if (empty() || (newPosition == 1)) { 
        newNode.next = firstNode;
        firstNode = newNode;
      } else {								
        Node previousNode = firstNode;
        for (int i = 1; i < newPosition - 1; ++i) {
          previousNode = previousNode.next;		
        }

        newNode.next = previousNode.next;	
        previousNode.next = newNode;		
      }

      dataCount++;
    } else {
      addNode = false;
    }

    return addNode;
    }

    @Override
    public boolean remove(int inputPosition) {
        Node previous = firstNode;
        dataCount =1;
        
            if ((inputPosition >= 1) && (inputPosition <= dataCount)) {

                if (inputPosition == 1) {  
                firstNode = firstNode.next;
              } else {                         
                Node prev = firstNode;
                for (int i = 1; i < inputPosition - 1; ++i) {
                  prev = prev.next;		
                }
                Node curr = prev.next;
                prev.next=curr.next;
                return true;
              } 	

                dataCount--;
            }
        
        return false;
    }

    public boolean contains(T element){
        boolean found = false;
        Node curr = firstNode;
        int counter = 1;

        while (!found && (curr != null)) {
          if (element.equals(curr.getData())) {
            found = true;
          } else {
            curr = curr.next;
          }
          counter += 1;
        }
        return found;
    }

    /*@Override
    public boolean update(T element) {
       
    }*/

    @Override
    public final void clear(T element) {
       firstNode = null;
        dataCount = 0;
    }


    @Override
    public boolean empty() {
         boolean result;
        result = dataCount == 0;
        return result;
    }
   
     @Override
    public void print(T element) {
       Node curr = firstNode;
       
        while (curr != null){ 
            System.out.print("[" + curr.getData() + "], "); 
            curr = curr.getNext();
        }
         System.out.println("");
    }
  
    public T[] toArray(){
        Node curr = this.firstNode;
        
        if (curr != null){
            T[] array =(T[]) new Object[this.dataCount];
            int num =0;
            
            while (curr !=null){
                array[num] =((T) curr.data);
                curr = curr.getNext();
                num++;
            }
            return array;
        }else{
            return null;
        }
    }
    
    
    private class Node<T extends Comparable<T>> {

        private T data;
        private Node next;

        private Node(T data) {
          this.data = data;
          this.next = null;
        }

        private Node(T data, Node next) {
          this.data = data;
          this.next = next;
        }

        public T getData(){
            return data;
        }

        public void setData(T data){
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
            
      }
    
}
