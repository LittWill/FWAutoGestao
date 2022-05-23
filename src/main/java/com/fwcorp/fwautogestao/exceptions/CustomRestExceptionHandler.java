package com.fwcorp.fwautogestao.exceptions;

import com.fwcorp.fwautogestao.util.GeradorRespostaServidor;
import com.fwcorp.fwautogestao.util.RespostaServidor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({FWGestaoException.class})
    public ResponseEntity<RespostaServidor> handleFWGestaoException(FWGestaoException ex, WebRequest request){
        return GeradorRespostaServidor.gerarRespostaServidor(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<RespostaServidor> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
        return GeradorRespostaServidor.gerarRespostaServidor(HttpStatus.NOT_FOUND, ex.getMessage());
    }

}
