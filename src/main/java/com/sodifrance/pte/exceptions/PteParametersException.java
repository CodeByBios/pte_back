package com.sodifrance.pte.exceptions;

/**
 * The Class GnfaParametersException.<br>
 * Exception sur les paramètres incorrectes.
 */
public class PteParametersException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2396345774132678005L;

    /**
     * Constructeur.
     *
     * @param message Message
     */
    public PteParametersException(String message) {
        super(message);
    }
}
