// Calculator Engine – handles all the math for the calculator, including basic operations. Uses java.lang.Math for the heavy lifting.
public class CalculatorEngine {
    private long currentValue;
    private long storedValue;
    private String currentOperation;
    private boolean startNewNumber;
    private String currentInput;
    private boolean hasDecimal;

    public CalculatorEngine() {
        currentValue = 0;
        storedValue = 0;
        currentOperation = "";
        startNewNumber = true;
        currentInput = "0";
        hasDecimal = false;
    }

    public String processInput(String input) {
        switch (input) {
            case "C":
                clear();
                return "0";
            case "⌫":
                return backspace();
            case "+":
            case "-":
            case "×":
            case "÷":
            case "%":
                return handleOperation(input);
            case "=":
                return handleEquals();
            case "<<":
                return handleLeftShift();
            case ">>":
                return handleRightShift();
            case "±":
                return handleNegate();
            case ".":
                return handleDecimal();
            case "(":
            case ")":
                // Parentheses aren’t supported yet, but maybe later.
                return currentInput;
            default:
                // Digits (0-9, A-F for hex)
                if (isValidDigit(input)) {
                    return handleDigitInput(input);
                }
                return currentInput;
        }
    }

    private boolean isValidDigit(String input) {
        return input.matches("[0-9A-F]");
    }

    private String handleDigitInput(String digit) {
        if (startNewNumber) {
            currentInput = digit;
            try {
                currentValue = Long.parseLong(digit);
            } catch (NumberFormatException e) {
                currentValue = 0;
            }
            startNewNumber = false;
            hasDecimal = false;
        } else {
            // Add the digit to the current input
            if (currentInput.equals("0") && !digit.equals("0")) {
                currentInput = digit;
            } else {
                currentInput += digit;
            }
            try {
                currentValue = Long.parseLong(currentInput);
            } catch (NumberFormatException e) {
                // Ignore it—just keep the previous value.
            }
        }
        return currentInput;
    }

    private String handleOperation(String operation) {
        if (!currentOperation.isEmpty() && !startNewNumber) {
            // Finish the previous operation first
            performCalculation();
        } else {
            storedValue = currentValue;
        }
        currentOperation = operation;
        startNewNumber = true;
        hasDecimal = false;
        currentInput = String.valueOf(currentValue);
        return currentInput;
    }

    private String handleEquals() {
        if (!currentOperation.isEmpty()) {
            performCalculation();
            currentOperation = "";
            startNewNumber = true;
            hasDecimal = false;
        }
        currentInput = String.valueOf(currentValue);
        return currentInput;
    }

    private void performCalculation() {
        try {
            switch (currentOperation) {
                case "+":
                    // Catch overflow
                    currentValue = Math.addExact(storedValue, currentValue);
                    break;
                case "-":
                    currentValue = Math.subtractExact(storedValue, currentValue);
                    break;
                case "×":
                    currentValue = Math.multiplyExact(storedValue, currentValue);
                    break;
                case "÷":
                    if (currentValue != 0) {
                        currentValue = storedValue / currentValue;
                    } else {
                        // Oops, divided by zero. Start over.
                        currentValue = 0;
                    }
                    break;
                case "%":
                    if (currentValue != 0) {
                        currentValue = storedValue % currentValue;
                    } else {
                        currentValue = 0;
                    }
                    break;
            }
            storedValue = currentValue;
        } catch (ArithmeticException e) {
            // Something went wrong (overflow, etc.)—reset everything.
            currentValue = 0;
            storedValue = 0;
        }
    }

    private String handleLeftShift() {
        // Binary left shift operation

        currentValue = currentValue << 1;
        currentInput = String.valueOf(currentValue);
        startNewNumber = true;
        hasDecimal = false;
        return currentInput;
    }

    private String handleRightShift() {
        // Shift the value right in binary
        currentValue = currentValue >> 1;
        currentInput = String.valueOf(currentValue);
        startNewNumber = true;
        hasDecimal = false;
        return currentInput;
    }

    private String handleNegate() {
        // Flip the sign of the current value
        try {
            currentValue = Math.negateExact(currentValue);
            currentInput = String.valueOf(currentValue);
        } catch (ArithmeticException e) {
            // If we overflow, just reset to zero
            currentValue = 0;
            currentInput = "0";
        }
        return currentInput;
    }

    private String handleDecimal() {
        if (!hasDecimal) {
            if (startNewNumber) {
                currentInput = "0.";
                startNewNumber = false;
            } else {
                currentInput += ".";
            }
            hasDecimal = true;
        }
        return currentInput;
    }

    private void clear() {
        currentValue = 0;
        storedValue = 0;
        currentOperation = "";
        startNewNumber = true;
        currentInput = "0";
        hasDecimal = false;
    }

    private String backspace() {
        if (currentInput.length() > 1) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);

            // If we deleted the decimal, update the flag
            if (!currentInput.contains(".")) {
                hasDecimal = false;
            }

            try {
                currentValue = Long.parseLong(currentInput.replace(".", ""));
            } catch (NumberFormatException e) {
                currentValue = 0;
                currentInput = "0";
            }
        } else {
            currentInput = "0";
            currentValue = 0;
            hasDecimal = false;
        }
        return currentInput;
    }

    public long getCurrentValue() {
        return currentValue;
    }

    public String getCurrentInput() {
        return currentInput;
    }
}