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
@Table(name = "user_game_history", schema = "cardano", catalog = "")
public class UserGameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private String historyId;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "room_name")
    private String roomName;
    @Basic
    @Column(name = "team")
    private String team;
    @Basic
    @Column(name = "join_payed")
    private String joinPayed;
    @Basic
    @Column(name = "win_earned")
    private String winEarned;
    @Basic
    @Column(name = "is_enabled")
    private String isEnabled="1";
    @Basic
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = true)
    private Date updatedAt;
}
