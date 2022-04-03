package com.weat.weat.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.weat.weat.common.exception.CoreException;
import com.weat.weat.data.dto.UserSession;
import com.weat.weat.response.ApiResponse;

public class CoreUtil {

	private CoreUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean isNonEmpty(Collection<?> collection) {
		return collection != null && !collection.isEmpty();
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNonEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNonEmpty(Object object) {
		return object != null;
	}

	public static boolean isEmpty(Object object) {
		return object == null;
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isNonEmpty(Map<?, ?> map) {
		return map != null && !map.isEmpty();
	}

	public static Object getPrincipal() {
		Object principal = null;
		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null) {
			principal = SecurityContextHolder.getContext().getAuthentication();
		}
		return principal;
	}

	public static String getCurrentUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = null;
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	public static UserSession getCurrentUser() {
		UserSession userSession = null;
		if (CoreUtil.isNonEmpty(SecurityContextHolder.getContext().getAuthentication())) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserSession) {
				userSession = ((UserSession) principal);
			}
		}
		return userSession;
	}

	public static List<String> getCurrentUserRoleNames() {
		UserSession userSession = getCurrentUser();
		if (userSession != null)
			return userSession.getAuthorities().stream().map(athority -> athority.getAuthority())
					.collect(Collectors.toList());
		return Collections.emptyList();
	}

	public static Long getCurrentUserId() {
		Long id = 1L;
		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserSession) {
				id = ((UserSession) principal).getId();
			}
		}
		return id;
	}

	public static String convertObjectToJson(Object object) {
		if (object == null) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat(Constants.UI_DATE_FORMAT);
			mapper.setDateFormat(df);
			JavaTimeModule javaTimeModule = new JavaTimeModule();
			javaTimeModule.addSerializer(LocalDateTime.class,
					new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constants.UI_DATE_TIME_FORMAT)));
			mapper.registerModule(javaTimeModule);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			if (object instanceof String) {
				String str = object.toString().replace("’", "'");
				return mapper.writeValueAsString(str);
			}
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new CoreException("Object Parsing Exception");
		}
	}

	@SuppressWarnings("rawtypes")
	public static ResponseEntity<ApiResponse> buildApiResponse(Object obj, HttpServletRequest req, String message,
			int http_Sucess, int http_failure, HttpStatus ok, HttpStatus nOk) {
		ResponseEntity<ApiResponse> responseEntity;
		ApiResponse response = new ApiResponse();
		response.setUri(req.getRequestURI());
		Collection colllection = null;
		if (obj instanceof Collection) {
			colllection = (Collection) obj;
			if (CoreUtil.isNonEmpty(colllection)) {
				response.setStatusCode(http_Sucess);
				responseEntity = new ResponseEntity<ApiResponse>(response, ok);
			} else {
				response.setStatusCode(http_failure);
				response.setError(true);
				response.setMessage(message);
				responseEntity = new ResponseEntity<ApiResponse>(response, nOk);
			}
		} else {
			if (CoreUtil.isNonEmpty(obj)) {

				response.setStatusCode(http_Sucess);
				responseEntity = new ResponseEntity<ApiResponse>(response, ok);
			} else {
				response.setStatusCode(http_failure);
				response.setError(true);
				response.setMessage(message);
				responseEntity = new ResponseEntity<ApiResponse>(response, nOk);
			}
		}
		response.setResponseBody(obj);
		return responseEntity;
	}

	public static void validateFileType(String fileName) {
		String extension = "";//FilenameUtils.getExtension(fileName);
		if ("exe".equalsIgnoreCase(extension) || "msi".equalsIgnoreCase(extension) || "cmd".equalsIgnoreCase(extension)
				|| "bat".equalsIgnoreCase(extension)) {
			throw new CoreException(HttpStatus.BAD_REQUEST.value(), "Invalid File Format");
		}
	}

	public static void validateFileNameFormat(String fileName) {
		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		if (pattern.matcher(fileName).matches()) {
			throw new CoreException(HttpStatus.CONFLICT.value(), "Invalid File name Format, it should be Alphanumeric");
		}
	}

	public static void validateFileSize(long filesize) {
		if (filesize > 5242880) {
			throw new CoreException(HttpStatus.CONFLICT.value(), "File size is exceeds (max size:5MB)");
		}
	}
}