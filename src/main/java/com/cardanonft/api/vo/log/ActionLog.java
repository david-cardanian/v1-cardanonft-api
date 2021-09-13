package com.cardanonft.api.vo.log;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;

/**
 * Created by jaren on 2016-03-28.
 */
@Data
@TypeAlias("actionLog")
public class ActionLog {
    private int log_id;

    private String hostname;
    private String auth_key;
    private String access_token;
    private String app_version;

    private String issued_ip;
    private String issued_client;
    private String issued_platform;
    private Integer user_id;
    private String http_method;
    private String http_query;
    private String req_header;
    private String req_payload;
    private String res_payload;
    private Date created_at;

}
