package com.example.algamoney.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgaMoneyExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagem = messageSource.getMessage("mensagem.erro", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<mensagemErro> erros = Arrays.asList(new mensagemErro(mensagem, mensagemDesenvolvedor));

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<mensagemErro> erros = criarListaErro(ex);
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {

		String mensagem = messageSource.getMessage("recurso.naoEncontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getMessage().toString();
		List<mensagemErro> erros = Arrays.asList(new mensagemErro(mensagem, mensagemDesenvolvedor));

		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	private List<mensagemErro> criarListaErro(BindingResult bindingResult) {
		List<mensagemErro> erros = new ArrayList<>();

		for (FieldError fieldErro : bindingResult.getFieldErrors()) {
			String mensagemUser = messageSource.getMessage(fieldErro, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldErro.toString();
			erros.add(new mensagemErro(mensagemUser, mensagemDesenvolvedor));
		}

		return erros;
	}

	public static class mensagemErro {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public mensagemErro(String mensagemUsuario, String mensagemDesenvolvedor) {
			super();
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

	}

}
