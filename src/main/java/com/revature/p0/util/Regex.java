package com.revature.p0.util;

public enum Regex {

    /*This pattern for username requires it to start with a letter, only allow letter, numbers, and Underscorse,
    and at a minimum is 3 characters and no white spaces. Following this recomendation for pattern:
    https://javadeveloperzone.com/java-8/java-regular-expression-for-username/
    */
    USERNAME_PATTERN("^([a-zA-z])+([\\w@]{2,})+$"),

    /* This is the pattern for password where it must have a Capital letter, a Number, and be at least
    4 characters long but no longer than 50 characters, and no whiteSpaces, and limited special characters.
    *
    * */
    PASSWORD_PATTERN("^(?=[a-zA-Z0-9#@$?_-]{4,50}$)(?=.*?[A-Z])(?=.*?[0-9]).*"),

    NONE ("");

    private String pattern;

    Regex(String pattern){
        this.pattern = pattern;
    };

    public String getPattern() {
        return this.pattern;
    }
}
