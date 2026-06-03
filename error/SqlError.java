package error;

public class SqlError {

    private String errorType;
    private String message;
    private String location;
    private String explanation;
    private String suggestion;

    // Constructor without suggestion
    public SqlError(String errorType, String message,
                    String location, String explanation) {
        this.errorType = errorType;
        this.message = message;
        this.location = location;
        this.explanation = explanation;
        this.suggestion = null;
    }

    // Constructor with suggestion
    public SqlError(String errorType, String message,
                    String location, String explanation,
                    String suggestion) {
        this.errorType = errorType;
        this.message = message;
        this.location = location;
        this.explanation = explanation;
        this.suggestion = suggestion;
    }

    // Getters
    public String getErrorType() {
        return errorType;
    }

    public String getMessage() {
        return message;
    }

    public String getLocation() {
        return location;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getSuggestion() {
        return suggestion;
    }

    // Pretty print error (for console output)
    public void printError() {
        System.out.println("Error Type   : " + errorType);
        System.out.println("Error        : " + message);
        System.out.println("Location     : " + location);
        System.out.println("Explanation  : " + explanation);

        if (suggestion != null) {
            System.out.println("Suggested Fix: " + suggestion);
        }

        System.out.println("--------------------------------------");
    }
}
