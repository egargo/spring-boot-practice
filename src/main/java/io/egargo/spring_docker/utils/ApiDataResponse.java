package io.egargo.spring_docker.utils;

import org.springframework.http.HttpStatus;

public class ApiDataResponse<T> {
	private HttpStatus status;
	private String message;
	private T data;

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	private ApiDataResponse(HttpStatus status, T data, String message) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static class ApiDataResponseBuilder<T> {
		private HttpStatus status;
		private String message;
		private T data;

		public ApiDataResponseBuilder() {
		}

		public ApiDataResponseBuilder<T> setStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		public ApiDataResponseBuilder<T> setMessage(String message) {
			this.message = message;
			return this;
		}

		public ApiDataResponseBuilder<T> setData(T data) {
			this.data = data;
			return this;
		}

		public ApiDataResponse<T> build() {
			return new ApiDataResponse<>(status, data, message);
		}
	}
}
