//package soen331_a2;
import be.ac.ua.ansymo.adbc.annotations.*;

public class List<E> {

	private Node<E> n;

	//default constructor
	public List(){
		n=null;
	}

	//Parameterized constructor
	@requires ({ "x != null" })
	@ensures ({"$this.size() ==1"})
	public List(Node<E> x){
		n=x;
		System.out.println("List created");

	}

	//add a node at the end of the list
	@requires ({ "x != null" })
	@ensures ({"$this.size() ==$old($this.size())+1","x.getNext()==null"})
	public boolean add(Node<E> x){
		try{
			if (n == null){
				n= x;
				return true;
			}
			Node<E> current = n;
			while(current.getNext() != null){
				current=current.getNext();
			}
			current.setNext(x);
			//System.out.println(x.getData());
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	//add a node at index 
	@requires ({"index > -1","y != null","index < $this.size()"})
	@ensures ({"$this.size() == $old($this.size())+1","$this.get(index).equals(y)"}) //not sure about the second part of the postcondition
	public void add(int index, Node<E> y){
		if(index == 0){
			y.setNext(n);
			this.n=y;
		}
		else{
			Node<E> pointer = this.n;
			Node<E> temp;
			for(int i =0; i == index -1; i++)
				pointer = pointer.getNext();
			temp = pointer.getNext();
			pointer.setNext(y);
			y.setNext(temp);			
		}
	}

	// clear the list
	@requires ({"true"})
	@ensures({"$this.size() == 0"})
	public void clear(){
		this.n = null;
	}

	//check if the list contains a node
	//*********method equals() to be verified*****************
	@requires({"n != null"})
	@ensures({"$this.size() == $old($this.size())"})
	public boolean contains(Node<E> n){
		Node<E> next = n;
		while(next != null){
			if(next.equals(n))
				return true;
		}
		return false;
	}

	//get node at index
	@requires ({"$index > -1"})
	@ensures({"$this.size() == $old($this.size())"})
	public Node<E> get(int index){
		int count=1;
		Node<E> next=n;
		while (next != null){
			if (count == index) return next;
			next=next.getNext();
			count ++;
			
		}
		return null;

	}

	//find index of node
	//******************method equals() to be verified***************
	@requires({"n != null"})
	@ensures({"$this.size() == $old($this.size())",
	"$result == -1 || $result >= 0"})
	public int indexOf(Node<E> n){
		int count = 0;
		Node<E> next = this.n;
		while(next != null){
			if(next.equals(n))
				return count;
			next = next.getNext();
			count ++;
		}
		return -1;

	}

	//check if it is empty 
	@requires({"true"})
	@ensures({"$this.size() == $old($this.size())"})
	public boolean isEmpty(){
		if(n == null)
			return true;
		else 
			return false;

	}

	//remove a node
	@requires({"$this.isEmpty() == false","index > -1","index < $this.size()"})
	@ensures({"($old($this.get(index+1))).getData().equals($this.get(index).getData())"})
	public void remove(int index){
		if (index==1) {
			Node<E> temp=n;
			n=n.getNext();
			temp.setNext(null);
		} else{
			Node<E> previous = n;
			int count =1;
			while(count<index-1){
				previous=previous.getNext();
				count++;
			}
			Node<E> current=previous.getNext();
			previous.setNext(current.getNext());
			current.setNext(null);
		}
	}

	//add element at index
	@requires({"$index >= 1","element != null"})
	@ensures({"$this.size() == $old($this.size())"})
	public void set(int index, E element){
		Node<E> pointer = n;
		int count=1;
		if (pointer==null){
			System.out.println("List is empty");
			return;
		}
		while (pointer != null){
			if (index==count){
				pointer.setData(element);
			}
			pointer=pointer.getNext();
			count++;
		}


	}

	//size of the list
	@requires({"true"})
	@ensures({"$result >=0"})
	public int size(){
		int result;
		Node<E> next;
		if(n!=null)
			result = 1;
		else 
			return 0;
		next = n.getNext();
		while(next != null){
			result++;
			next = next.getNext();
		}
		return result;

	}
	public void toStringAll(){
		Node<E> next = this.n;
		int count = 0;
		while(next != null){
			System.out.println("Node #" + count +": "+ next.getData());
			count++;
			next =next.getNext();
		}
	}
}
