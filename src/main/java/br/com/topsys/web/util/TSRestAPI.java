package br.com.topsys.web.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.topsys.web.exception.TSRestResponseException;



@Component
public class TSRestAPI<T extends Serializable> {

	
	private String baseURL;

	public String getBaseURL() {
		return this.baseURL;
	}

	private RestTemplate restTemplate = null;

	public TSRestAPI() {

		this.restTemplate = new RestTemplate();
		this.restTemplate.setErrorHandler(new TSRestResponseException());

	} 
	
	public TSRestAPI(String baseURL) {
        this.baseURL = baseURL; 
		this.restTemplate = new RestTemplate();
		this.restTemplate.setErrorHandler(new TSRestResponseException());

	}

	public T postReturnObject(Class<T> classe, String url, T object) {
		
		T retorno = null;

		HttpEntity<T> entity = new HttpEntity<T>(object);

		retorno = (T) restTemplate.postForObject(this.getBaseURL() + url, entity, classe);

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<T> postReturnList(String url, T object) {

		List<T> retorno = null;

		HttpEntity<T> entity = new HttpEntity<T>(object);

		retorno = restTemplate.postForObject(this.getBaseURL() + url, entity, List.class);

		return retorno;

	}

	public List<T> postReturnList(String url) {

		return this.postReturnList(url, null);

	}

}
