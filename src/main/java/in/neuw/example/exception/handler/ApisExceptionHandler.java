package in.neuw.example.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.neuw.example.exception.AppRuntimeException;
import in.neuw.example.model.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author Karanbir Singh on 05/07/2020
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Order(-2)
public class ApisExceptionHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof AppRuntimeException) {
            var appRuntimeException = (AppRuntimeException) ex;

            log.info("errors:" + appRuntimeException.getMessage());
            var errorResponse = new ErrorResponse()
                    .setMessage(appRuntimeException.getMessage())
                    .setStatus(appRuntimeException.getErrorCodes().getCode());

            if (appRuntimeException.getException() != null) {
                errorResponse.setException(appRuntimeException.getException().getClass().getSimpleName());
            }

            try {
                exchange.getResponse().setStatusCode(appRuntimeException.getErrorCodes().getStatus());
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

                var db = new DefaultDataBufferFactory().wrap(objectMapper.writeValueAsBytes(errorResponse));

                // write the given data buffer to the response
                // and return a Mono that signals when it's done
                return exchange.getResponse().writeWith(Mono.just(db));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Mono.empty();
            }
        } else if(ex instanceof WebExchangeBindException) {
            var errorResponse = new ErrorResponse()
                    .setMessage("Field '"+((WebExchangeBindException) ex).getFieldErrors().get(0).getField()+"' has some issues!")
                    .setStatus(HttpStatus.BAD_REQUEST.value());
            try {
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

                var db = new DefaultDataBufferFactory().wrap(objectMapper.writeValueAsBytes(errorResponse));

                return exchange.getResponse().writeWith(Mono.just(db));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Mono.empty();
            }
        }
        return Mono.error(ex);
    }

}
