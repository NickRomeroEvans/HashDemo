

public class HashDemo<K,V> {
	private static final int[] PRIMES = {11, 47, 197, 797, 3203, 13001, 53003, 213019};
	private int numItems;
	private int loadFactor;
	private Node<K,V>[] table;
	
	// Surpress unchecked construction to reifiable type.
	@SuppressWarnings("unchecked")
	public HashDemo(){
		table = new Node[PRIMES[0]];
		loadFactor = PRIMES[0]/2;
		numItems = 0;
	}
	
	// Add a element pair of key, value into HashMap
	// Or updates the value if the key already exists
	public void add(K key, V value){
		if(numItems >= loadFactor){
			resize();
		}
		
		int index = key.hashCode() % table.length;
		
		Node<K,V> n = table[index];
		if(n == null) {
			table[index] = new Node<K,V>(key, value);
			numItems++;
		} else {
			while(n.next != null){
				if(n.getKey().equals(key)){
					n.setValue(value);
					return;
				} else {
					n = n.next;
				}
			}
			if(n.getKey().equals(key)){
				n.setValue(value);
				return;
			} else {
				n.next = new Node<K,V>(key, value);
				numItems++;
			}
			
		}
	}
	
	// Find a Node element in table with the Node.key = key 
	// and change the Node.value to value/
	// If no element exists do nothing.
	public void update(K key, V value){
		int index = key.hashCode() % table.length;
		
		Node<K,V> n = table[index];
		
		if(n == null) {
			return;
		} else {
			while(n.next != null){
				if(n.getKey().equals(key)){
					n.setValue(value);
					return;
				} else {
					n = n.next;
				}
			}
			if(n.getKey().equals(key)){
				n.setValue(value);
				return;
			} else {
				return;
			}
		}
	}
	
	// Delete Node with Node.key=key if it exists
	// other wise do nothing.
	public void delete(K key){
		int index = key.hashCode()% table.length;
		
		Node<K,V> n = table[index];
		if(n != null){
			if(n.getKey().equals(key)){
				table[index] = n.next;
				numItems--;
			} else {
				while(n.next != null){
					if(n.next.getKey().equals(key)){
						n.next = n.next.next;
						numItems--;
						return;
					}
					n = n.next;
				}
			}
		}
	}
	
	// Resize the table to next size up
	@SuppressWarnings("unchecked")
	private void resize(){
		int primeSize = getPrime();
		numItems = 0;
		
		Node<K,V>[] temp = table; //(Node<K,V>[]) new Node<K,V>[primeSize];
		table =  new Node[primeSize];
		
		for(Node<K,V> n : temp){
			while(n != null){
				add(n.getKey(), n.getValue());
				n = n.next;
			}
		}
		loadFactor = table.length/2;
	}
	
	// Get Prime for table size
	private int getPrime(){
		if(table.length > PRIMES[PRIMES.length -1]){
			// get next prime
			int temp = table.length;
			temp += 2;
			while(temp > table.length){
				if(isPrime(temp)){
					return temp;
				}
				temp+=2;
			}
			// temp is largest prime
			return temp;
		} else {
			for(int i = 0; i < PRIMES.length; i++){
				if(PRIMES[i] > table.length){
					return PRIMES[i];
				}
			}
			
			return PRIMES[PRIMES.length-1];
		}
	}
	
	// Helper method verify prime in get prime
	private boolean isPrime(int check){
		if(check % 2 == 0){
			return false;
		}
		
		for(int i = 3; i < Math.sqrt(check); i+=2){
			if(check % 3 == 0){
				return false;
			}
		}
		
		return true;
	}
	
	// Return a element of V if Node exists with Node.key
	// other wise returns null
	public V getValue(K key){
		HashDemo<K, V>.Node<K, V> temp = table[key.hashCode()%table.length];
		
		if(temp == null){
			return null;
		}
		
		while(temp.next != null){
			if(temp.getKey().equals(key)){
				return temp.getValue();
			} else {
				temp = temp.next;
			}
		}			
		if(temp.getKey().equals(key)){
			return temp.getValue();
		} 
		
		return null;
	}
	
	// Gets Number of items
	public int getSize(){
		return numItems;
	}
	
	// For testing purposes gets length of table
	public int getLength(){
		return table.length;
	}
	
	// Private class used to create linked list
	private class Node<K, V> {
		private K key;
		private V value;
		
		public Node<K,V> next;
		
		public Node(K k, V v){
			this.key = k;
			this.value = v;
			this.next = null;
		}
		
		public K getKey(){
			return key;
		}
		
		public void setKey(K k){
			this.key = k;
		}
		
		public V getValue(){
			return value;
		}
		
		public void setValue(V v){
			this.value = v;
		}
	}
}