package rules;

import error.SqlError;
import parser.ClauseAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class MandatoryClauseRule {

    // Validate presence of mandatory clauses
    public List<SqlError> validate(ClauseAnalyzer analyzer) {

        List<SqlError> errors = new ArrayList<>();

        // Rule: SELECT must exist
        if (!analyzer.hasClause("SELECT")) {
            SqlError error = new SqlError(
                    "Missing Clause Error",
                    "SELECT clause is missing",
                    "Beginning of query",
                    "Every SQL query must start with a SELECT clause",
                    "SELECT column_name FROM table_name;"
            );
            errors.add(error);
        }

        // Rule: FROM must exist
        if (!analyzer.hasClause("FROM")) {
            SqlError error = new SqlError(
                    "Missing Clause Error",
                    "FROM clause is missing",
                    "After SELECT clause",
                    "SELECT clause must specify a source table using FROM",
                    "SELECT column_name FROM table_name;"
            );
            errors.add(error);
        }

        return errors;
    }
}
