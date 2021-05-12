package com.revature.p0.util;

public enum Regex {

    /*This pattern for username requires it to start with a letter, only allow letter, numbers, and Underscorse,
    and at a minimum is 3 characters. Following this recomendation for pattern:
    https://javadeveloperzone.com/java-8/java-regular-expression-for-username/
    */
    USERNAME_PATTERN("^([a-zA-z])+([\\w@]{2,})+$"),
    NONE ("");

    private String pattern;

    Regex(String pattern){
        this.pattern = pattern;
    };

    public String getPattern() {
        return this.pattern;
    }
}
