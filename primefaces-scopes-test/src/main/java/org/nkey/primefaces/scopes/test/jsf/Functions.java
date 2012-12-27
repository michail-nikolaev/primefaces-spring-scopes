package org.nkey.primefaces.scopes.test.jsf;

/**
 * @author m.nikolaev Date: 11.12.12 Time: 22:12
 */
public final class Functions {
    private Functions() {}

    public static String concat2(String s1, String s2) {
        return concatBr(s1, s2);
    }

    public static String concatBr(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string).append("<br/>");
        }
        return builder.toString().substring(0, builder.lastIndexOf("<br/>"));
    }
}
