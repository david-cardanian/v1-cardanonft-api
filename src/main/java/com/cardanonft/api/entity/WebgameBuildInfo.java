package com.cardanonft.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "webgame_build_info", schema = "cardano", catalog = "")
public class WebgameBuildInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String gameName;

    private String loaderUrl;

    private String dataUrl;

    private String frameworkUrl;

    private String codeUrl;

    private boolean enabled = true;

    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;


}
