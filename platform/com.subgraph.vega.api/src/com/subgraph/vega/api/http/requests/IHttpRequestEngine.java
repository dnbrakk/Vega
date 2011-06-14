package com.subgraph.vega.api.http.requests;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public interface IHttpRequestEngine {
	/**
	 * Get the configuration for this request engine.
	 * 
	 * @return IHttpRequestEngineConfig
	 */
	IHttpRequestEngineConfig getRequestEngineConfig();
	
	/**
	 * Instantiate a HttpRequestBuilder.
	 * 
	 * @return HttpRequestBuilder instance.
	 */
	IHttpRequestBuilder createRequestBuilder();

	/**
	 * Instantiate a HttpResponseBuilder.
	 *
	 * @return HttpResponseBuilder instance.
	 */
	IHttpResponseBuilder createResponseBuilder();

	/**
	 * Send a request, specifying the HTTP context. 
	 * 
	 * The following variables cannot be set in context and will be overwritten:
	 * 	- ClientContext.COOKIE_STORE (set in the IHttpRequestEngineConfig this request engine was instantiated with)
	 * 
	 * @param request Request to send.
	 * @param context HTTP context.
	 * @return IHttpResponse
	 * @throws RequestEngineException
	 */
	IHttpResponse sendRequest(HttpUriRequest request, HttpContext context) throws RequestEngineException;

	/**
	 * Send a request, specifying the HTTP context.
	 * 
	 * @param request Request to send.
	 * @return IHttpResponse
	 * @throws RequestEngineException
	 */
	IHttpResponse sendRequest(HttpUriRequest request) throws RequestEngineException;
}
