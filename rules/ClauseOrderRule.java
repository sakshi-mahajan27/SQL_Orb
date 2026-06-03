package rules;

import error.SqlError;
import parser.ClauseAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClauseOrderRule {

    // Defines correct SQL clause order
    private static final String[] CLAUSE_ORDER = {
            "SELECT", "FROM", "WHERE", "GROUP BY", "HAVING", "ORDER BY"
    };

    // Validate clause order and return list of errors
    public List<SqlError> validate(ClauseAnalyzer analyzer) {

        List<SqlError> errors = new ArrayList<>();
        Map<String, Integer> clauses = analyzer.getAllClauses();

        for (int i = 0; i < CLAUSE_ORDER.length - 1; i++) {
            String currentClause = CLAUSE_ORDER[i];
            String nextClause = CLAUSE_ORDER[i + 1];

            if (clauses.containsKey(currentClause)
                    && clauses.containsKey(nextClause)) {

                int currentPos = clauses.get(currentClause);
                int nextPos = clauses.get(nextClause);

                // Order violation
                if (currentPos > nextPos) {

                    SqlError error = new SqlError(
                            "Clause Order Error",
                            nextClause + " clause appears before " + currentClause + " clause",
                            "Near '" + nextClause + "'",
                            "SQL clauses must follow the order: " +
                                    "SELECT -> FROM -> WHERE -> GROUP BY -> HAVING -> ORDER BY"
                    );

                    errors.add(error);
                }
            }
        }

        return errors;
    }
}
