package com.revature.p0.util;

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

    /* This it the pattern for firstname that can't start with a space, onlt allows letters, dashes, or underscores, and
    must be between 2 and 26 characters.
     */
    FIRSTNAME_PATTERN("^[^-\\s][a-zA-Z_\\s-]{2,25}+$"),

    NONE ("");

    private String pattern;

    Regex(String pattern){
        this.pattern = pattern;
    };

    public String getPattern() {
        return this.pattern;
    }
}
