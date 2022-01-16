package auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * GlobalDefaultExceptionHandler
 *
 * @author datdv
 */
@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handleCityNotFoundException
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            CustomException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        ex.getExtensions().put("timestamp", LocalDateTime.now());
        HttpStatus httpStatusResponse = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getHttpStatus() != null) {
            httpStatusResponse = ex.getHttpStatus();
        }

        return new ResponseEntity<>(ex.getExtensions(), httpStatusResponse);
    }

    /**
     * handleMultipartException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException ex) {
        String maxFileSize = getMaxUploadFileSize(ex);
        Map<String, Object> error = new HashMap<>();
        if (maxFileSize != null) {
            error.put("message", "Uploaded file is too large.  File size cannot exceed 20MB.");
        } else {
            error.put("message", ex.getMessage());
        }
        Map<String , Object> body = new HashMap<>();
        body.put("errors" , error);
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getMaxUploadFileSize(MultipartException ex) {
        if (ex instanceof MaxUploadSizeExceededException) {
            return asReadableFileSize(((MaxUploadSizeExceededException) ex).getMaxUploadSize());
        }
        String msg = ex.getMessage();
        if (msg.contains("SizeLimitExceededException")) {
            String maxFileSize = msg.substring(msg.indexOf("maximum")).replaceAll("\\D+", "");
            if (StringUtils.isNumeric(maxFileSize)) {
                return asReadableFileSize(Long.valueOf(maxFileSize));
            }
        }
        return null;
    }

    private static String asReadableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    /**
     * handleMethodArgumentNotValid
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("validate failed");
        Map<String, Object> body = new LinkedHashMap<>();
        List<ObjectError> errors = ex.getAllErrors();
        List<Map<String, Object>> errList = new ArrayList<>();
        for (ObjectError objectError: errors) {
            Map<String, Object> error = new HashMap<>();
            error.put(Constants.ERROR_ITEM, Objects.requireNonNull(objectError.getDefaultMessage()).split(" ")[0]);
            error.put(Constants.ERROR_MESSAGE, objectError.getDefaultMessage());
            errList.add(error);
        }
        body.put("code", status.value());
        body.put("errors", errList);
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, status);
    }
}