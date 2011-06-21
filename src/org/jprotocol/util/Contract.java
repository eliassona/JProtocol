package org.jprotocol.util;


import java.util.Collection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;





public class Contract
{    
    
    public interface IContractCondition
    {
        public boolean condition(); 
        public String description();
    }

    @SuppressWarnings("serial")
	public static final class ContractError extends AssertionError
    {
        public ContractError(final String msg)
        {
            super(msg);
        }
    }

        
    // --------------------------------------------
    // Contract Programming primitive definitions.
    // --------------------------------------------
    
    public static void require(boolean requireCondition, final Object... description)
    {
        require(equalsTrue(requireCondition), description);
    }

    public static void require(final IContractCondition contract, final Object... description)
    {
        if (SyntacticSugar.isNull(contract) || !contract.condition())
        {
            throwContractViolationException(REQUIRE_LABEL, contract, description);
        }
    }

    public static void require(final Object object, final Matcher<?> matcher, final Object... additionalValues)
    {
        if (SyntacticSugar.isNull(matcher) || !matcher.matches(object))
        {
            throwContractViolationException(REQUIRE_LABEL, object, matcher, additionalValues);
        }
    }
    
    public static void ensure(boolean ensureCondition, final Object... description)
    {
        ensure(equalsTrue(ensureCondition), description);
    }
    
    public static void ensure(final IContractCondition contract, final Object... description)
    {
        if (SyntacticSugar.isNull(contract) || !contract.condition())
        {
            throwContractViolationException(ENSURE_LABEL, contract, description);
        }
    }

    public static void ensure(final Object object, final Matcher<?> matcher, final Object... additionalValues)
    {
        if (SyntacticSugar.isNull(matcher) || !matcher.matches(object))
        {
            throwContractViolationException(ENSURE_LABEL, object, matcher, additionalValues);
        }
    }
    
    public static void invariant(boolean invariantCondition, final Object... description)
    {
        invariant(equalsTrue(invariantCondition), description);
    }

    public static void invariant(final IContractCondition contract, final Object... description)
    {
        if (SyntacticSugar.isNull(contract) || !contract.condition())
        {
            throwContractViolationException(INVARIANT_LABEL, contract, description);
        }
    }
    
    public static void invariant(final Object object, final Matcher<?> matcher, final Object... additionalValues)
    {
        if (SyntacticSugar.isNull(matcher) || !matcher.matches(object))
        {
            throwContractViolationException(INVARIANT_LABEL, object, matcher, additionalValues);
        }
    }
    
    public static void check(boolean checkCondition, final Object... description)
    {
        check(equalsTrue(checkCondition), description);
    }

    public static void check(final IContractCondition contract, final Object... description)
    {
        if (SyntacticSugar.isNull(contract) || !contract.condition())
        {
            throwContractViolationException(CHECK_LABEL, contract, description);
        }
    }

    public static void check(final Object object, final Matcher<?> matcher, final Object... additionalValues)
    {
        if (SyntacticSugar.isNull(matcher) || !matcher.matches(object))
        {
            throwContractViolationException(CHECK_LABEL, object, matcher, additionalValues);
        }
    }
    
    public static void neverGetHere(final Object... description) 
    {
        final String contractDescription = "This code-path was never expected to be executed. ";
        throwContractViolationException(NEVERGETHERE_LABEL, contractDescription, description);
    }

    public static void underConstruction(final Object... description) 
    {
        final String contractDescription = "You have reached code that is under construction, and not yet ready to be tested. ";
        throwContractViolationException(UNDERCONSTRUCTION_LABEL, contractDescription, description);
    }
    
    
    
    
    public static boolean implies(
    		final boolean impliesCondition, 
    		final boolean condition)
    {
        final boolean doNotEvaluateCondition = true;
    	return impliesCondition ? condition : doNotEvaluateCondition;
    }
    
    public static <T> boolean isNull(final T ref)
    {
    	return (null == ref);
    }

    public static <T> IContractCondition equalsNull(final T ref)
    {
        final IContractCondition condition;
        if (SyntacticSugar.isNull(ref)) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String anonymous = "<anonymous>";
            final String className = ref.getClass().getSimpleName();
            final boolean noName = className.isEmpty();
            final String description = "The reference points to an [" + (noName ? anonymous : className) + "] instance, but was expected to be a null reference.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }
    
    public static <T> boolean isNotNull(final T ref)
    {
    	return (null != ref);
    }
    
    public static <T> IContractCondition notNull(final T ref)
    {
        final IContractCondition condition;
        if (SyntacticSugar.isNotNull(ref)) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The reference is null, but was expected to refere to an instance.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }


    public static IContractCondition nonNegative(final int value)
    {
        final IContractCondition condition;
        if (0 <= value)
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The value is " + value + ", but was expected to be non-negative.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }


    public static IContractCondition nonNegative(final double value)
    {
        final IContractCondition condition;
        if (0 <= value)
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The value is < 0.0, but was expected to be non-negative.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }

    
    public static boolean isInRange(final int value, final int rangeMin, final int rangeMax)
    {
        final boolean notBellow = (value >= rangeMin);
        final boolean notAbove  = (value <= rangeMax);
        return notBellow && notAbove;
    }

    public static IContractCondition inRange(final int value, final int rangeMin, final int rangeMax)
    {
        final boolean notBellow = (value >= rangeMin);
        final boolean notAbove  = (value <= rangeMax);

        final IContractCondition condition;
        if (notBellow && notAbove)
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The value " + value + " is not within the range [" + rangeMin + ", " + rangeMax + "].";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }

    
    public static boolean isInRange(final double value, final double rangeMin, final double rangeMax)
    {
        final boolean notBellow = (value >= rangeMin);
        final boolean notAbove  = (value <= rangeMax);
        return notBellow && notAbove;
    }
            
    public static IContractCondition inRange(final double value, final double rangeMin, final double rangeMax)
    {
        final boolean notBellow = (value >= rangeMin);
        final boolean notAbove  = (value <= rangeMax);

        final IContractCondition condition;
        if (notBellow && notAbove) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The value " + value + " is not within the range [" + rangeMin + ", " + rangeMax + "].";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }
    
    public static boolean isNotEmpty(final Collection<?> collection)
    {
        boolean isNotEmpty = false;
        
        if (SyntacticSugar.isNotNull(collection) && !collection.isEmpty())
        {
            isNotEmpty = true;
        }
        
        return isNotEmpty;
    }

    public static IContractCondition notEmpty(final Collection<?> collection)
    {
        final IContractCondition condition;
        if (SyntacticSugar.isNotNull(collection) && !collection.isEmpty()) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The collection is " + ((null == collection) ? "null" : "empty") + ", but was expected to be non-empty.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }

    

    public static boolean isNotEmpty(final String s)
    {
        boolean isNotEmpty = false;
        
        if (SyntacticSugar.isNotNull(s) && !s.isEmpty())
        {
            isNotEmpty = true;
        }
        
        return isNotEmpty;
    }

    public static IContractCondition notEmpty(final String s)
    {
        final IContractCondition condition;
        if (SyntacticSugar.isNotNull(s) && !s.isEmpty()) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The string is " + ((null == s) ? "null" : "empty") + ", but was expected to be non-empty.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }
    
    public static <T> IContractCondition notEmpty(final T[] objArr)
    {
        final IContractCondition condition;
        if (SyntacticSugar.isNotNull(objArr) && (objArr.length > 0)) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final boolean isNull = (null == objArr);
            final String description = "The array is " + (isNull ? "null" : "empty") + ", but was expected to be non-empty.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }

    public static IContractCondition notEmpty(final double[] doubleArr)
    {
        final IContractCondition condition;
        if (isNotNull(doubleArr) && (doubleArr.length > 0)) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final boolean isNull = (null == doubleArr);
            final String description = "The array of doubles is " + (isNull ? "null" : "empty") + ", but was expected to be non-empty.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }


    public static IContractCondition equalsTrue(final boolean value)
    {
        final IContractCondition condition;
        if (value) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The value is false, but was expected to be true.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }


    public static IContractCondition equalsFalse(final boolean value)
    {
        final IContractCondition condition;
        if (!value) 
        {
            condition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The value is true, but was expected to be false.";
            condition = createContractCondition(FAILURE, description);
        }
        
        return condition;
    }

    
    public static IContractCondition forAll(final Collection<?> collection, final Comparable<?> forAllCondition)
    {
        final IContractCondition contractCondition;
        if (SyntacticSugar.isForAll(collection, forAllCondition)) 
        {
            contractCondition = getSuccessContractCondition();
        }
        else 
        {
            final String description = "The supplied condition was not true for all elements in the collection: ";
            final String collectionStr;
            if (SyntacticSugar.isNotNull(collection))
            {
                collectionStr = collection.toString();
            }
            else
            {
                collectionStr = "<null>";
            }
            
            contractCondition = createContractCondition(FAILURE, description + collectionStr);
        }
        
        return contractCondition;
    }

    
    
    public static IContractCondition getSuccessContractCondition()
    {
        return SUCCESS_CONDITION;
    }
   
    public static IContractCondition createContractCondition(
            final boolean condition, 
            final String description)
    {
        final class ContractCondition implements IContractCondition
        {
            final boolean myCondition;
            final String myDescription;
            
            ContractCondition(final boolean cond, final String descr)
            {
                myCondition = cond;
                myDescription = descr;
            }
            
            public boolean condition()
            {
                return myCondition;
            }

            public String description()
            {
                return myDescription + " ";
            }
        }
        
        return new ContractCondition(condition, description);    
    }

    
    private static void throwContractViolationException(
            final String label, 
            final IContractCondition contract, 
            final Object... description) throws Error
    {
        String contractDescription = "Contract condition is null.";
        if (SyntacticSugar.isNotNull(contract))
        {
            contractDescription = contract.description();
        }
        
        throwContractViolationException(label, contractDescription, description);
    }

    private static void throwContractViolationException(
            final String label, 
            final Object violatingObject,
            final Matcher<?> matcher, 
            final Object... additionalValues) throws Error
    {
        final Description description = new StringDescription(); 
        description.appendText("\nExpected: ");
        
        if (SyntacticSugar.isNull(matcher)) {
            description.appendText("<null>");
        } else {  
            matcher.describeTo(description);
        }
        description.appendText("\nGot: ");
        description.appendText(String.valueOf(violatingObject));
        throwContractViolationException(label, description.toString(), additionalValues);
    }

    private static void throwContractViolationException(
            final String contractLabel, 
            final String contract, 
            final Object... description) throws Error
    {
        final Error error = new ContractError(createInfo(contractLabel, contract, description));
        
        if (hasUsefulStackTrace(error))
        {
            error.setStackTrace( getTrimmedCallstack(error) );
        }
        
        throw error;
    }
    
    private static boolean hasUsefulStackTrace(final Error error)
    {
        final boolean hasStackTraceElements = SyntacticSugar.isNotNull(error) && (error.getStackTrace().length > 0);
        
        boolean hasNoNullElements = false;
        if (hasStackTraceElements)
        {
            hasNoNullElements = hasNoNullElements(error);
        }       
        
        return  hasStackTraceElements && hasNoNullElements;
    }

    private static boolean hasNoNullElements(final Error error)
    {
        boolean noNullElements = false;
        for (final StackTraceElement element : error.getStackTrace())
        {
            noNullElements = SyntacticSugar.isNotNull(element);
            if (! noNullElements)
            {
                break;
            }
        }

        return noNullElements;
    }

    private static StackTraceElement[] getTrimmedCallstack(final Error error)
    {
        final StackTraceElement[] originalStackTrace = error.getStackTrace();
        final int firstNonContractStackTracePosition = getFirstNonContractStackTracePosition(originalStackTrace);

        final int newLength = (originalStackTrace.length - firstNonContractStackTracePosition);
        final StackTraceElement[] filteredStackTrace = new StackTraceElement[newLength];

        // Transfer all non-contract related stack trace elements.
        for (int i = 0; i < newLength; ++i)
        {
            filteredStackTrace[i] = originalStackTrace[i + firstNonContractStackTracePosition];
        }
        
        return filteredStackTrace;
    }
       
    private static int getFirstNonContractStackTracePosition(
            final StackTraceElement[] stackTrace)
    {
        int elementNo = stackTrace.length - 1;
        while (elementNo >= 0)
        {
            if (foundContractCallStackLocation(stackTrace, elementNo))
            {
                // Ok, the previous is the stack element we want.
                ++elementNo;
                break;
            }
            
            // Ok, no luck, try the next stack element.
            --elementNo;
        }
                
        return elementNo;
    }

    private static boolean foundContractCallStackLocation(
            final StackTraceElement[] stackTrace, 
            final int elementNo)
    {
        final String contractClassName = Contract.class.getCanonicalName();
        final String stackElementClassName = stackTrace[elementNo].getClassName();
        return stackElementClassName.contentEquals(contractClassName);
    }
    
    private static String getThreadInfo()
    {
        return " Thread id: " + Thread.currentThread().getId() + "." +
               " Thread name: " + Thread.currentThread().getName() + ".";
    }
    
    private static String getSystemInfo()
    {
        final String osNameKey = "os.name";
        final String systemLabel = " System: "; 
        String systemInfo;

        try
        {
            systemInfo = systemLabel + System.getProperties().getProperty(osNameKey) + ".";
        }
        catch (final SecurityException se)
        {
            systemInfo = systemLabel + "<Security manager does not allow access to property keys!>";    
        }
        
        return systemInfo;
    }
    
    private static String descriptionInfo(
            final String contract, 
            final Object... description)
    {
        final String descriptionHeader = "\nDescription: ";   
        return  descriptionHeader + contract + SyntacticSugar.concatenate(description);
    }
    
    private static String violationInfo(final String contractLabel)
    {
        final String violationHeader = "\n\nViolation:   ";
        return violationHeader + contractLabel;
    }

    private static String callStackInfo()
    {
        final String callStackHeader = "\nCall stack: ";        
        return callStackHeader + "\n"; 
    }
    
    private static String generalInfo()
    {
        final String infoHeader = "\nInformation:";
        return infoHeader + getThreadInfo() + getSystemInfo();
    }  

    private static String createInfo(
            final String contractLabel, 
            final String contract, 
            final Object... description)
    {
        return violationInfo(contractLabel) + 
               descriptionInfo(contract, description) + 
               generalInfo() +
               callStackInfo();
    }
    

    
    // Texts to be logged.
    private static final String CONTRACT_VIOLATION      = " contract violation. ";
    private static final String REQUIRE_LABEL           = "REQUIRE" + CONTRACT_VIOLATION;
    private static final String ENSURE_LABEL            = "ENSURE" + CONTRACT_VIOLATION;
    private static final String INVARIANT_LABEL         = "INVARIANT" + CONTRACT_VIOLATION;
    private static final String NEVERGETHERE_LABEL      = "NEVER GET HERE" + CONTRACT_VIOLATION;
    private static final String CHECK_LABEL             = "CHECK failed. ";
    private static final String UNDERCONSTRUCTION_LABEL = "UNDER CONSTRUCTION" + CONTRACT_VIOLATION;
    
    private static final boolean SUCCESS = true; 
    private static final boolean FAILURE = false;
    private static final String NO_DESCRIPTION = "";
    private static final IContractCondition SUCCESS_CONDITION = createContractCondition(SUCCESS, NO_DESCRIPTION);   
}
