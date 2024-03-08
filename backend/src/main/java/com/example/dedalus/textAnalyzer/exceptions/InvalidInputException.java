package com.example.dedalus.textAnalyzer.exceptions;


/**
 * The InvalidInputException class represents a custom exception that extends RuntimeException.
 * It is used to indicate that invalid input has been provided to the text analysis process.
 * This exception is thrown when the input data does not meet the expected format or criteria.
 */
@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {
    
	 /**
     * Constructs a new InvalidInputException with the specified detail message.
     * The message provides more information about the nature of the invalid input.
     *
     * @param message A String detailing the reason for the exception.
     */
	public InvalidInputException(String message) {
        super(message);
    }
}
