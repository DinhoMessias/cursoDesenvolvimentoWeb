package com.example.algamoney.Event.Listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.Event.CadastrarEvent;

@Component
public class CadastrarListener implements ApplicationListener<CadastrarEvent> {

	@Override
	public void onApplicationEvent(CadastrarEvent event) {
		HttpServletResponse response = event.getResponse();
		Long id = event.getId();

		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
