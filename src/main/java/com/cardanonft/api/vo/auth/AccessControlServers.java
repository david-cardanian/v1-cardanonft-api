package com.cardanonft.api.vo.auth;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.sql.Date;

@TypeAlias("accessControlServers")
@Data
public class AccessControlServers {
    private int access_control_servers_id;
    private String server_name;
    private String server_host;
    private int server_port;
    private int is_enabled;
    private Date created_at;
    private Date updated_at;
}
