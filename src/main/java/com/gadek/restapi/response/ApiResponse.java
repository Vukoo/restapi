package com.gadek.restapi.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class ApiResponse {
    @ApiModelProperty(value = "Response code from api")
    private Integer code;
    @ApiModelProperty(value = "Message description")
    private String message;
    @ApiModelProperty(value = "Response data messag", dataType = "date", example = "2021-12-14T12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime localDateTime = LocalDateTime.now();

    private String apiVersion = "1.0.0";
}
