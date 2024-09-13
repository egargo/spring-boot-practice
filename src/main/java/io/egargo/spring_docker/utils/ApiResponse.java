package io.egargo.spring_docker.utils;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
	private HttpStatus status;
	private String message;

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	private ApiResponse(HttpStatus status, T data, String message) {
		this.status = status;
		this.message = message;
	}

	private ApiResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public static class ApiResponseBuilder<T> {
		private HttpStatus status;
		private String message;

		public ApiResponseBuilder() {
		}

		public ApiResponseBuilder<T> setStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		public ApiResponseBuilder<T> setMessage(String message) {
			this.message = message;
			return this;
		}

		public ApiResponse<T> build() {
			return new ApiResponse<>(status, message);
		}
	}
}
