package rules;

import error.SqlError;
import parser.ClauseAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class SelectColumnRule {

    public List<SqlError> validate(List<String> tokens, ClauseAnalyzer analyzer) {

        List<SqlError> errors = new ArrayList<>();

        // SELECT or FROM missing → skip this rule
        if (!analyzer.hasClause("SELECT") || !analyzer.hasClause("FROM")) {
            return errors;
        }

        int selectPos = analyzer.getClausePosition("SELECT");
        int fromPos = analyzer.getClausePosition("FROM");

        // Extract tokens between SELECT and FROM
        List<String> selectTokens = tokens.subList(selectPos + 1, fromPos);

        // Less than 2 tokens → no comma issue possible
        if (selectTokens.size() < 2) {
            return errors;
        }

        // Check for missing commas
        for (int i = 0; i < selectTokens.size() - 1; i++) {

            String current = selectTokens.get(i);
            String next = selectTokens.get(i + 1);

            // Ignore commas and functions
            if (current.equals(",") || next.equals(",")) {
                continue;
            }

            // If two identifiers appear consecutively → missing comma
            if (isIdentifier(current) && isIdentifier(next)) {

                SqlError error = new SqlError(
                        "SELECT Clause Error",
                        "Missing comma between columns '" + current + "' and '" + next + "'",
                        "Near '" + next + "'",
                        "Multiple columns in SELECT clause must be separated by commas"
                );

                errors.add(error);
                break; // one clear error is enough
            }
        }

        return errors;
    }

    // Simple identifier check (not keyword or symbol)
    private boolean isIdentifier(String token) {
        return token.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    // Build suggested SELECT clause with commas
    private String buildSuggestion(List<String> selectTokens) {

        StringBuilder sb = new StringBuilder("SELECT ");

        for (int i = 0; i < selectTokens.size(); i++) {
            sb.append(selectTokens.get(i));

            if (i < selectTokens.size() - 1 &&
                !selectTokens.get(i).equals(",") &&
                !selectTokens.get(i + 1).equals(",")) {
                sb.append(", ");
            } else {
                sb.append(" ");
            }
        }

        return sb.toString().trim() + " FROM table_name;";
    }
}
