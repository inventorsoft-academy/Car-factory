package com.academy.oop.basic.util.impl;


import java.util.*;

public class CustomArrayList<T> extends ArrayList<T> {

	private static final int DEFAULT_CAPACITY = 5;

	private static final Object[] EMPTY_ELEMENT_DATA = {};

	private int size;

	private transient Object[] customArrayListElementData;

	public CustomArrayList(int initialCapacity){
		super();
		if (initialCapacity < 0)
		throw new IllegalArgumentException("Illegal Capacity: "+
				initialCapacity);
		this.customArrayListElementData = new Object[initialCapacity];
	}

	public CustomArrayList(){
		super();
		this.customArrayListElementData = EMPTY_ELEMENT_DATA;
	}


	public boolean add(T e) {
		ensureCapacity(size + 1);
		customArrayListElementData[size++] = e;
		return true;
	}

	public void add(int index, T element) {
		ensureCapacity(size + 1);
		System.arraycopy(customArrayListElementData, index, customArrayListElementData, index + 1,size - index);
		customArrayListElementData[index] = element;
		size++;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index >= size){
			throw new ArrayIndexOutOfBoundsException("array index out of bound exception with index at"+index);
		}
		return (T)customArrayListElementData[index];
	}



}
