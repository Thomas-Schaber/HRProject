package edu.waketech.csc251.collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.waketech.csc251.tools.Screener;

public class DataSetGeneric<E extends Measurable> {
	
	private ArrayList<E> list;
	
	public DataSetGeneric() {
		list = new ArrayList<E>();
	}
	
	public void add(E objectToAdd) {
		list.add(objectToAdd);
	}
	
	public ArrayList<E> getList() {
		return list;
	}
	
	public ArrayList<E> getList(Screener<E> elementScreener) {
		ArrayList<E> list2 = new ArrayList<E>();
		for(E e : list) {
			if(elementScreener.test(e)) {
				list2.add(e);
			}
		}
		return list2;
	}
	
	public E getMin() {
		E min = list.get(0);
		for(int x = 1; x < list.size(); x++) {
			if(min.getMeasure() > list.get(x).getMeasure()) {
				min = list.get(x);
			}
		}
		return min;
	}
	
	public E getMax() {
		E max = list.get(0);
		for(int x = 1; x < list.size(); x++) {
			if(max.getMeasure() < list.get(x).getMeasure()) {
				max = list.get(x);
			}
		}
		return max;
	}
	
	public List<E> sortBy(Comparator<? super E> comparator) {
		List<E> list1 = (List<E>)list.clone();
		list1.sort(comparator);
		
		return list1; 
	}
	
	public String toString() {
		
		return "DataSetGeneric [\n\tsize()= " + list.size() + "\n\tgetMin()= " + getMin() + "\n\tgetMax()= " + getMax() + "\nObjects= " + list.toString() + "]";
	}
	
	

}
