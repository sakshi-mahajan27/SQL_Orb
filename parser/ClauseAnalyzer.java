package parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClauseAnalyzer {

    // Stores clause name and its starting index in token list
    private Map<String, Integer> clausePositions;

    public ClauseAnalyzer() {
        clausePositions = new HashMap<>();
    }

    // Analyze tokens and find clause positions
    public void analyze(List<String> tokens) {

        for (int i = 0; i < tokens.size(); i++) {

            String token = tokens.get(i).toUpperCase();

            // Handle single-word clauses
            if (token.equals("SELECT") || token.equals("FROM")
                    || token.equals("WHERE") || token.equals("HAVING")) {

                clausePositions.put(token, i);
            }

            // Handle multi-word clauses
            if (token.equals("GROUP") && i + 1 < tokens.size()
                    && tokens.get(i + 1).equalsIgnoreCase("BY")) {

                clausePositions.put("GROUP BY", i);
            }

            if (token.equals("ORDER") && i + 1 < tokens.size()
                    && tokens.get(i + 1).equalsIgnoreCase("BY")) {

                clausePositions.put("ORDER BY", i);
            }
        }
    }

    // Check if clause exists
    public boolean hasClause(String clause) {
        return clausePositions.containsKey(clause);
    }

    // Get starting position of clause
    public Integer getClausePosition(String clause) {
        return clausePositions.get(clause);
    }

    // Get all detected clauses
    public Map<String, Integer> getAllClauses() {
        return clausePositions;
    }
}
