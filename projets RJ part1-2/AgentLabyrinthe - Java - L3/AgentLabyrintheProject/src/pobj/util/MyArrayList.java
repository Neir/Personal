package pobj.util;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class MyArrayList<T> extends AbstractList<T> implements Iterable<T>{

	private final int taille;
	private LinkedList<Vector<T>> ll;
	

	public MyArrayList() {
		super();
		taille = 20;
		ll= new LinkedList<Vector<T>>();
	}
	
	public MyArrayList(int taille) {
		super();
		this.taille = taille;
		ll = new LinkedList<Vector<T>>();
	}

	public MyArrayList(LinkedList<Vector<T>> ll) {
		super();
		taille = 20;
		this.ll = ll;
	}
	
	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		if(ll.isEmpty()){
			ll.add(new Vector<T>(taille));
		}
		if(ll.getLast().size()==taille){
			ll.add(new Vector<T>(taille));
		}
		return ll.getLast().add(e);
	}

	public T get(int location){
		return ll.get(location/taille).get(location%taille);
		
	}
	
	public T set(int location, T e){
		return ll.get(location/taille).set(location%taille,e);
	}

	public MyIterator<T> iterator(){
		return new MyIterator<T>(ll);
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return (ll.size()-1)*taille+ll.getLast().size();
	}
}

class MyIterator<T> implements Iterator<T>{
	protected Iterator<T> vectorIT;
	protected Iterator<Vector<T>> listIT;

	public MyIterator(List<Vector<T>> list){
		listIT = list.iterator();
		vectorIT = Collections.EMPTY_LIST.iterator();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return listIT.hasNext()||vectorIT.hasNext();
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		if(vectorIT.hasNext())
			return vectorIT.next();
		else if(listIT.hasNext()){
			Vector<T> vect = listIT.next();
			vectorIT = vect.iterator();
			
			return next();
		}
		return null;
	}

	@Override
	public void remove(){
		throw new UnsupportedOperationException();	
	
	}
}