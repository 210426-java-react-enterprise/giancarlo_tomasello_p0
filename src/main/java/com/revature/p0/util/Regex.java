package com.revature.p0.util;


/**
 * An enum that holds the values of regex patterns that are used
 * to varrify user input to desired rules. The rules for each pattern
 * are specified in the .java file
 */
public enum Regex {

    /*Rules:
    - Must start with a letter
    -Must be at least 2 characters
    -No Special characters except _
    -No whitespaces

    Following this recomendation for pattern:
    https://javadeveloperzone.com/java-8/java-regular-expression-for-username/
    */

    USERNAME_PATTERN("^([a-zA-z])+([\\w@]{2,})+$"),

    /* This is the pattern for password where it must have a Capital letter, a Number, and be at least
    4 characters long but no longer than 50 characters, and no whiteSpaces, and limited special characters.
    *
    * */

    PASSWORD_PATTERN("^(?=[a-zA-Z0-9#@$?_-]{4,50}$)(?=.*?[A-Z])(?=.*?[0-9]).*"),

    /* This it the pattern for firstname that can't start with a space, only allows letters, dashes, or underscores, and
    must be between 2 and 26 characters.
     */
    FIRSTNAME_PATTERN("^[^-\\s][a-zA-Z_\\s-]{2,25}+$"),

    /* This it the pattern for lastname that can't start with a space, only allows letters, dashes, or underscores, and
    must be between 2 and 26 characters.
    */
    LASTNAME_PATTERN("^[^-\\s][a-zA-Z_\\s-]{2,25}+$"),

    /* This is the rules for email pattern:
    * -must have an @ serperating the email into two parts name & domain
    * -Can only contain num, letters, underscore, period, and - (no _ in domain)
    * */
    EMAIL_PATTERN("^[A-Za-z0-9+_.-]+@[a-zA_Z0-9.-]+$"),

    NONE ("");

    private String pattern;

    Regex(String pattern){
        this.pattern = pattern;
    };

    public String getPattern() {
        return this.pattern;
    }
}
