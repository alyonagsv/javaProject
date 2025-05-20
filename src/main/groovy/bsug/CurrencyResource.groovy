package bsug

import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import bsu.ConversionResult

@Path("/convert")
class CurrencyResource {

    @Inject
    CurrencyService service

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response convert(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("amount") double amount) {

        if (from == null || to == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Parameters 'from' and 'to' are required")
                    .build()
        }

        Optional<Double> result = service.convert(from.toUpperCase(), to.toUpperCase(), amount)
        return result
                .map { convertedAmount -> new ConversionResult(from, to, amount, convertedAmount) }
                .map { conversionResult -> Response.ok(conversionResult).build() }
                .orElse(Response.status(Response.Status.BAD_REQUEST)
                        .entity("Unsupported currency pair or conversion error")
                        .build())
    }
}
