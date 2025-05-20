package bsu;

public class ConversionResult {
    private String from;
    private String to;
    private double amount;
    private double result;

    public ConversionResult(String from, String to, double amount, double result) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.result = result;
    }

    // Геттеры
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public double getResult() {
        return result;
    }

    // Сеттеры
    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
