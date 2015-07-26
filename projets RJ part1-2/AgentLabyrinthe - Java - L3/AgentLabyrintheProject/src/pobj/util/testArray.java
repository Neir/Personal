package pobj.util;

public class testArray {
	public static void main(String[] args){
		MyArrayList<Integer> list = new MyArrayList<Integer>();
		
		for(int i = 0; i<50; i++){
			list.add(i);
		}
		
			System.out.println(list.size());
	}
}
