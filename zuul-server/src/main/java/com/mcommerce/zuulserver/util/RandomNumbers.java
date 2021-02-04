package com.mcommerce.zuulserver.util;

import java.util.ArrayList;
import java.util.Random;

public class RandomNumbers {

	/**
	 * Generate n pseudorandom integer numbers from 0 to bound
	 * @param bound The upper bound (exclusive). Must be positive
	 * @param n Number of integers to generate
	 * @return Array of n pseudorandom integers
	 */
    static ArrayList<Integer> intFormat(int bound, int n){
        Random random = new Random();
        ArrayList<Integer> intArray = new ArrayList<>();
		// generate 0 <= random number < n
        for ( int i=0; i<n; i++ ) { 
			intArray.add(random.nextInt(bound));
		}
		return intArray;    	
    }	
    
    /**
     * Generates n pseudorandom double numbers from 0.0 and less than 1.0
     * @param n Number of doubles to generate
     * @return Array of n pseudorandom doubles
     */
    static ArrayList<Double> doubleFormat(int n){
        Random random = new Random();
        ArrayList<Double> doubleArray = new ArrayList<>();
        for ( int i=0; i<n; i++ ) { 
			doubleArray.add(random.nextDouble());
		}
		return doubleArray;  	
    }	
}
