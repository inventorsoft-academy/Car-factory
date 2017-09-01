package com.academy.oop.basic.util.impl;


import java.util.*;

public class CustomArrayList<T> extends ArrayList<T> {

	private static final int DEFAULT_INITIAL_CAPACITY = 5;

	private static final Object[] EMPTY_ELEMENT_DATA = {};

	private int size;

	private transient Object[] customArrayListElementData;

	public CustomArrayList(int initialCapacity){
		if (initialCapacity < 0)
		throw new IllegalArgumentException("Illegal Capacity: "+
				initialCapacity);
		this.customArrayListElementData = new Object[initialCapacity];
	}

	public CustomArrayList(){
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

    public void ensureCapacity(int minCapacity) {
        if (customArrayListElementData == EMPTY_ELEMENT_DATA) {
            minCapacity = Math.max(DEFAULT_INITIAL_CAPACITY, minCapacity);
        }
        if (minCapacity - customArrayListElementData.length > 0)
            growCustomArrayList(minCapacity);
    }

    private void growCustomArrayList(int minCapacity) {
        int oldCapacity = customArrayListElementData.length;
        int newCapacity = oldCapacity + (oldCapacity /2);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        customArrayListElementData = Arrays.copyOf(customArrayListElementData, newCapacity);
    }



}
