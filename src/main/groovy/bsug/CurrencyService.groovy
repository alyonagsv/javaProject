package bsug;

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
public class CurrencyService {

    private final Map<String, Map<String, Double>> conversionRates = [:]

    CurrencyService() {
        // инициализация курсов валют
        conversionRates["USD"] = [
                "BYN": 3.0113,
                "EUR": 0.8869,
                "RUB": 81.0295
        ]

        conversionRates["BYN"] = [
                "USD": 0.3321,
                "EUR": 0.2945,
                "RUB": 26.9085
        ]

        conversionRates["EUR"] = [
                "USD": 1.12750,
                "BYN": 3.3953,
                "RUB": 91.3624
        ]

        conversionRates["RUB"] = [
                "USD": 0.0109,
                "BYN": 0.0372,
                "EUR": 0.0109
        ]
    }

    Optional<Double> convert(String from, String to, double amount) {
        try {
            if (from == to) {
                return Optional.of(amount)
            }

            if (conversionRates.containsKey(from) && conversionRates[from].containsKey(to)) {
                def rate = conversionRates[from][to]
                return Optional.of(amount * rate)
            }

            return Optional.empty()
        } catch (Exception ignored) {
            return Optional.empty()
        }
    }
}
