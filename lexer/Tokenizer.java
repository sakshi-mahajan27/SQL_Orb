package lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    // List of supported SQL keywords
    private static final List<String> KEYWORDS = Arrays.asList(
            "SELECT", "FROM", "WHERE", "GROUP", "BY",
            "HAVING", "ORDER", "INSERT", "UPDATE",
            "DELETE", "INTO", "VALUES"
    );

    // Entry method for tokenization
    public static List<String> tokenize(String query) {

        List<String> tokens = new ArrayList<>();

        // Normalize input
        query = query.trim();

        // Add spaces around special symbols to isolate them
        query = query.replaceAll("([(),;])", " $1 ");
        query = query.replaceAll("\\s+", " ");

        // Split by space
        String[] parts = query.split(" ");

        for (String part : parts) {
            if (!part.isEmpty()) {
                tokens.add(part);
            }
        }

        return tokens;
    }

    // Utility method: check if token is keyword
    public static boolean isKeyword(String token) {
        return KEYWORDS.contains(token.toUpperCase());
    }

    // Utility method: check if token is operator
    public static boolean isOperator(String token) {
        return token.equals("=") || token.equals(">") || token.equals("<")
                || token.equals(">=") || token.equals("<=") || token.equals("!=");
    }

    // Utility method: check if token is symbol
    public static boolean isSymbol(String token) {
        return token.equals(",") || token.equals("(")
                || token.equals(")") || token.equals(";");
    }
}
