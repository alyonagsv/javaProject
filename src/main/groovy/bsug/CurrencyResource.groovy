package bsug;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import bsu.ConversionResult

@Path("/convert")
public class CurrencyResource {

    @Inject
    CurrencyService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convert(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("amount") double amount) {

        if (from == null || to == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Parameters 'from' and 'to' are required")
                    .build();
        }

        Optional<Double> result = service.convert(from.toUpperCase(), to.toUpperCase(), amount);
        return result
                .map(convertedAmount -> new ConversionResult(from, to, amount, convertedAmount))
                .map(conversionResult -> Response.ok(conversionResult).build())
                .orElse(Response.status(Response.Status.BAD_REQUEST)
                        .entity("Unsupported currency pair or conversion error")
                        .build());
    }
}


//package bsug;
//
//import jakarta.inject.Inject;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import com.example.CurrencyService;
//import java.io.IOException;
//
//@WebServlet("/convert")
//public class CurrencyConverterServlet extends HttpServlet {
//
//    @Inject
//    private CurrencyService currencyService;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        // Перенаправляем на JSP страницу с формой
//        req.getRequestDispatcher("/converter.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        try {
//            double amount = Double.parseDouble(req.getParameter("amount"));
//            String fromCurrency = req.getParameter("fromCurrency");
//            String toCurrency = req.getParameter("toCurrency");
//
//            Optional<Double> result = currencyService.convert(
//                    fromCurrency.toUpperCase(),
//                    toCurrency.toUpperCase(),
//                    amount
//            );
//
//            if (result.isPresent()) {
//                req.setAttribute("result", String.format("%.2f", result.get()));
//            } else {
//                req.setAttribute("error", "Не удалось выполнить конвертацию");
//            }
//        } catch (NumberFormatException e) {
//            req.setAttribute("error", "Введите корректную сумму");
//        }
//
//        // Устанавливаем атрибуты для отображения введенных значений
//        req.setAttribute("amount", req.getParameter("amount"));
//        req.setAttribute("fromCurrency", req.getParameter("fromCurrency"));
//        req.setAttribute("toCurrency", req.getParameter("toCurrency"));
//
//        // Возвращаемся на ту же страницу
//        req.getRequestDispatcher("/converter.jsp").forward(req, resp);
//    }
//}