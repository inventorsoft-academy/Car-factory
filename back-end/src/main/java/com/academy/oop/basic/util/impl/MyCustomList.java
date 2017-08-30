package com.academy.oop.basic.util.impl;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyCustomList<T> implements List {

	private T[] array = (T[]) new Object[1];
	private T[] arrayTemp;


	@Override
	public int size() {
		int size = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				size = i + 1;
			}

		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public T[] toArray() {
		return array;
	}

	@Override
	public boolean add(Object o) {
		boolean flag = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				array[i] = (T) o;
				flag = true;
				break;
			}
		}
		if (!flag) {
			arrayTemp = (T[]) new Object[array.length * 2];
			int j = 0;
			for (T obj : array) {
				arrayTemp[j] = obj;
				j++;
			}
			array = arrayTemp;
			arrayTemp = null;
			int k = 0;
			while (true) {
				if (array[k] == null) {
					array[k] = (T) o;
					break;
				}
				k++;
			}
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == (T) o) {
				array[i] = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addAll(Collection collection) {
		return false;
	}

	@Override
	public boolean addAll(int i, Collection collection) {
		return false;
	}

	@Override
	public void clear() {
		array = (T[]) new Object[1];

	}

	@Override
	public T get(int i) {
		return array[i];
	}

	@Override
	public T set(int i, Object o) {
		array[i] = (T) o;
		return (T) o;
	}

	@Override
	public void add(int i, Object o) {
		if (array.length > i) {
			array[i] = (T) o;
		} else {
			arrayTemp = (T[]) new Object[i];
			for (int j = 0; j < array.length; j++) {
				arrayTemp[j] = array[j];
			}
			array = arrayTemp;
			arrayTemp = null;
			array[i] = (T) o;
		}
	}

	@Override
	public Object remove(int i) {
		if (i >= 0 && i <= array.length) {
			array[i] = null;
			return true;
		}
		return false;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == (T) o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] == (T) o) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator listIterator() {
		return null;
	}

	@Override
	public ListIterator listIterator(int i) {
		return null;
	}

	@Override
	public List<T> subList(int i, int i1) {
		int count = i1 - i;
		T[] subArray = (T[]) new Object[count];
		int k = 0;
		for (int j = i; j < i1; j++) {
			subArray[k] = array[j];
			k++;
		}
		MyCustomList<T> myCustomList = new MyCustomList<T>();
		for (int q = 0; q < subArray.length; q++) {
			myCustomList.add(subArray[q]);
		}
		return myCustomList;
	}

	@Override
	public boolean retainAll(Collection collection) {
		return false;
	}

	@Override
	public boolean removeAll(Collection collection) {
		return false;
	}

	@Override
	public boolean containsAll(Collection collection) {
		return false;
	}

	@Override
	public T[] toArray(Object[] objects) {
		int indexLastElement = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] != null) {
				indexLastElement = i;
			}
		}
		arrayTemp = (T[]) new Object[indexLastElement];
		for (int i = 0; i < indexLastElement; i++) {
			arrayTemp[i] = array[i];
		}
		return arrayTemp;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator iterator() {
		return null;
	}
}
