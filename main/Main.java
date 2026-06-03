package main;

import lexer.Tokenizer;
import parser.ClauseAnalyzer;
import rules.ClauseOrderRule;
import rules.MandatoryClauseRule;
import rules.SelectColumnRule;
import error.SqlError;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==== SQL Syntax Checker ====");
        System.out.println("Enter SQL Query:");
        String query = scanner.nextLine();

        // Step 1: Tokenization
        List<String> tokens = Tokenizer.tokenize(query);

        // Step 2: Clause Analysis
        ClauseAnalyzer analyzer = new ClauseAnalyzer();
        analyzer.analyze(tokens);

        // Step 3: Apply Rules
        List<SqlError> errors = new ArrayList<>();

        MandatoryClauseRule mandatoryRule = new MandatoryClauseRule();
        errors.addAll(mandatoryRule.validate(analyzer));

        ClauseOrderRule orderRule = new ClauseOrderRule();
        errors.addAll(orderRule.validate(analyzer));

        SelectColumnRule selectRule = new SelectColumnRule();
        errors.addAll(selectRule.validate(tokens, analyzer));

        // Step 4: Display Results
        if (errors.isEmpty()) {
            System.out.println("\n✅ No syntax errors found.");
        } else {
            System.out.println("\n❌ Syntax Errors Detected:\n");
            for (SqlError error : errors) {
                error.printError();
            }
        }

        scanner.close();
    }
}
