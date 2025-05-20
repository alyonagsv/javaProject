package bsu;

public class ConversionResult {
    public String from;
    public String to;
    public double amount;
    public double result;

    public ConversionResult(String from, String to, double amount, double result) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.result = result;
    }
}
