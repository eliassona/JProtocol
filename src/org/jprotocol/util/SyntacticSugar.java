/*
 * SyntacticSugar
 *
 * St. Jude Medical CRMD Proprietary
 * Copyright, Pacesetter, Inc., 2009.
 * Unpublished work. All rights reserved.
 */
package org.jprotocol.util;

import java.util.*;

public class SyntacticSugar {

    
    public static boolean not(final boolean condition) {
        return (! condition);
    }


    public static <T> boolean isNull(final T ref) {
        return (null == ref);
    }
    
    public static <T> boolean isNotNull(final T ref) {
        return (null != ref);
    }
    
    public static <T> boolean has(final T ref) {
        return (null != ref);
    }
    
    public static <T> boolean hasNot(final T ref) {
        return (null == ref);
    }
    
    // *******************
    // *** ARITHMETICS ***
    // *******************
    
    public static boolean isNonNegative(final double value) {
        return (0.0 <= value);
    }
    
    public static boolean isNonNegative(final int value) {
        return (0 <= value);
    }
    
    
    public static boolean isInRange(final int value, final int rangeMin, final int rangeMax) {
        final boolean notBellow = (value >= rangeMin);
        final boolean notAbove  = (value <= rangeMax);
        return notBellow && notAbove;
    }
  
    public static boolean isInRange(final double value, final double rangeMin, final double rangeMax) {
        final boolean notBellow = (value >= rangeMin);
        final boolean notAbove  = (value <= rangeMax);
        return notBellow && notAbove;
    }
    
    
    // *************
    // *** EMPTY ***
    // *************
    
    public static <T> boolean isNotEmpty(final T[] objArr) {
        boolean isNotEmpty = false;
        
        if (isNotNull(objArr) && (objArr.length > 0)) {
            isNotEmpty = true;
        }
        
        return isNotEmpty;
    }
    
    public static boolean isNotEmpty(final double[] doubleArr) {
        boolean isNotEmpty = false;
        
        if (isNotNull(doubleArr) && (doubleArr.length > 0)) {
            isNotEmpty = true;
        }
        
        return isNotEmpty;
    }
    
    public static boolean isNotEmpty(final Collection<?> collection) {
        boolean isNotEmpty = false;
        
        if (isNotNull(collection) && !collection.isEmpty()) {
            isNotEmpty = true;
        }
        
        return isNotEmpty;
    }
    
    
    public static boolean isNotEmpty(final String s) {
        boolean isNotEmpty = false;
        
        if (isNotNull(s) && !s.isEmpty()) {
            isNotEmpty = true;
        }
        
        return isNotEmpty;
    }
    
    public static Comparable<String> isNonEmptyString()
    {
        final Comparable<String> condition = new Comparable<String>()
        {
            // Define the condition.
            public int compareTo(final String str)
            {
                boolean conditionResult = false;
                if (isNotNull(str))
                {
                    conditionResult = ! str.isEmpty();
                }
                
                return analyzeConditionResult(conditionResult);
            }
        };
        
        return condition;        
    }
    
    public static Comparable<String> isEmptyString() {
        final String conditionEmptyString = "";
        return conditionEmptyString;        
    }

    
    
    public static boolean implies(
            final boolean impliesCondition, 
            final boolean condition) {
        final boolean doNotEvaluateCondition = true;
        return impliesCondition ? condition : doNotEvaluateCondition;
    }
    
    
    public static boolean isForAll(final Collection<?> collection, final Comparable<?> condition)
    {
        final boolean resultForAllElements;

        // A null condition and/or collection is considered as a false condition.
        if (gotNullParameters(collection, condition))
        {
            resultForAllElements = false;
        } 
        else
        {
            // An empty collection is considered to be satisfied with any condition.
            resultForAllElements = verifyConditionOnAllCollectionElements(collection, condition);
        }
        
        return resultForAllElements;
    }
    
    public static int analyzeConditionResult(final boolean conditionResult) {
        return conditionResult ? CONDITION_IS_OK : CONDITION_IS_NOT_OK;
    }
    
    public static String concatenate(final Object... descriptions) {
        // No descriptions (or if somebody explicitly sends in "null"...) 
        if (isNull(descriptions) || descriptions.length < 1) {
            return NO_DESCRIPTION;
        }
        // Only one description, no need to concatenate.
        else if ((descriptions.length == 1) && (isNotNull(descriptions[0]))) {
            return descriptions[0].toString();
        }
        // Need to concatenate multiple descriptions...
        else {
            final StringBuffer sb = new StringBuffer();
            final String space = " ";
            for (final Object o : descriptions) {
                sb.append(o);
                sb.append(space);
            }
            
            return sb.toString();
        }
    }
    
	@SuppressWarnings("rawtypes")
	public static Comparable isNotNull() {
        final Comparable condition = new Comparable() {
            public int compareTo(final Object element) {
                // Define condition to be true for all elements.            
                return analyzeConditionResult( isNotNull(element) );
            }
        };
        
        return condition;
    }    
    

	@SuppressWarnings("rawtypes")
	public static Comparable containsSubString(final String substr) {
        final Comparable<String> condition = new Comparable<String>() {
            // Define condition to be true for all elements.            
            public int compareTo(final String str) {
                return analyzeConditionResult(str.contains(substr));
            }
        };
        
        return condition;
    }
        
    public static void givenNothing() {
        nothing();
    }

    public static void expectNothing() {
        doNothing();
    }
    
    public static void nothing() {
    }
    
    public static void doNothing() {
    }
    
    public static void doNothing(final String reason) {
        doNothing();
    }
    
    public static boolean isEqual(final int compareToValue) {
        return 0 == compareToValue;
    }

    public static boolean isNotEqual(final int compareToValue) {
        return 0 != compareToValue;
    }

    public static boolean isEqualOrLess(final int compareToValue) {
        return 0 >= compareToValue;
    }

    public static boolean isEqualOrMore(final int compareToValue) {
        return 0 <= compareToValue;
    }

    public static boolean isMore(final int compareToValue) {
        return 0 < compareToValue;
    }

    public static boolean isLess(final int compareToValue) {
        return 0 > compareToValue;
    }

	@SuppressWarnings("unchecked")
	public static boolean verifyCondition(
            @SuppressWarnings("rawtypes") final Comparable condition,
            final Object element)
    {
        return isNotEqual(condition.compareTo(element));
    }
    
    public static boolean isEmpty(final String string)
    {
        return string == null || string.trim().isEmpty();
    }
    
    
    
    private static boolean gotNullParameters(
            final Collection<?> collection, 
            final Comparable<?> condition) {
        return isNull(condition) || isNull(collection);
    }
    
	private static boolean verifyConditionOnAllCollectionElements(
            final Collection<?> collection, 
            @SuppressWarnings("rawtypes") final Comparable condition) {
        boolean result = true;
        for (final Object element : collection) {
            if (verifyCondition(condition, element)) {
                result = false;
                break;
            }
        }
        
        return result;
    }
    
    
    
    private static final String NO_DESCRIPTION = "";
    private static final int CONDITION_IS_OK = 0;
    private static final int CONDITION_IS_NOT_OK = -1;     
}
