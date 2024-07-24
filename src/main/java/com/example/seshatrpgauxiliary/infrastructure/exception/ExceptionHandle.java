package com.example.seshatrpgauxiliary.infrastructure.exception;

import com.example.seshatrpgauxiliary.domain.exceptions.custom.SeshatBadRequestException;
import com.example.seshatrpgauxiliary.domain.exceptions.model.PadraoErro;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(SeshatBadRequestException.class)
    public ResponseEntity<PadraoErro> SeshatBadRequestException(SeshatBadRequestException exception, HttpServletRequest request){
        return erroPadronizado(HttpStatus.BAD_REQUEST, "Nenhum resultado retornado!", exception, request);
    }


    private ResponseEntity<PadraoErro> erroPadronizado(HttpStatus httpStatus, String mensagemGenericaErro,
                                                       Exception exception, HttpServletRequest request) {

        return ResponseEntity.status(httpStatus).body(new PadraoErro(System.currentTimeMillis(), httpStatus.value(),
                mensagemGenericaErro, exception.getMessage(), request.getRequestURI()));
    }
}
