package com.cardanonft.api.vo.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by choi on 2017. 7. 13..
 */
@Alias("Postings")
@ApiModel
public class Postings {

    @ApiModelProperty(notes = "개시물 아이디")
    private long postings_id;
    private long store_id;
    @ApiModelProperty(notes = "보내는 사람 아이디")
    private long from_user_id;
    @ApiModelProperty(notes = "받는사람 아이디")
    private long to_user_id;
    @JsonIgnore
    private long parent_postings_id;
    @ApiModelProperty(notes = "내용")
    private String contents;
    @JsonIgnore
    private int is_enabled;
    @ApiModelProperty(notes = "기본 : 1  //추가 예정")
    private int message_type;
    @ApiModelProperty(notes = "작성 시간")
    private Date created_at;
    @JsonIgnore
    private Date updated_at;
    @JsonIgnore
    private long original_id;
    @ApiModelProperty(notes = "읽지 않음 0 보다 큰수 , 읽음 0 ")
    private int is_read;
    @ApiModelProperty(notes = "프로필 이미지 Path")
    private String profile_img;
    @ApiModelProperty(notes = "유저 이름")
    private String username;
    @JsonIgnore
    private long start_read;
    @JsonIgnore
    private long end_read;
    @JsonIgnore
    private int role_id;

    public Postings() {
    }

    public Postings(long postings_id, long store_id, long from_user_id, long to_user_id, long parent_postings_id, String contents, int is_enabled, int message_type, Date created_at, Date updated_at, long original_id, int is_read, String profile_img, String username, long start_read, long end_read) {
        this.postings_id = postings_id;
        this.store_id = store_id;
        this.from_user_id = from_user_id;
        this.to_user_id = to_user_id;
        this.parent_postings_id = parent_postings_id;
        this.contents = contents;
        this.is_enabled = is_enabled;
        this.message_type = message_type;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.original_id = original_id;
        this.is_read = is_read;
        this.profile_img = profile_img;
        this.username = username;
        this.start_read = start_read;
        this.end_read = end_read;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public long getStart_read() {
        return start_read;
    }

    public void setStart_read(long start_read) {
        this.start_read = start_read;
    }

    public long getEnd_read() {
        return end_read;
    }

    public void setEnd_read(long end_read) {
        this.end_read = end_read;
    }

    public long getPostings_id() {
        return postings_id;
    }

    public void setPostings_id(long postings_id) {
        this.postings_id = postings_id;
    }

    public long getStore_id() {
        return store_id;
    }

    public void setStore_id(long store_id) {
        this.store_id = store_id;
    }

    public long getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(long from_user_id) {
        this.from_user_id = from_user_id;
    }

    public long getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(long to_user_id) {
        this.to_user_id = to_user_id;
    }

    public long getParent_postings_id() {
        return parent_postings_id;
    }

    public void setParent_postings_id(long parent_postings_id) {
        this.parent_postings_id = parent_postings_id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(int is_enabled) {
        this.is_enabled = is_enabled;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public long getOriginal_id() {
        return original_id;
    }

    public void setOriginal_id(long original_id) {
        this.original_id = original_id;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "com.albam.api.vo.store.PostingsVO{" +
                "postings_id=" + postings_id +
                ", store_id=" + store_id +
                ", from_user_id=" + from_user_id +
                ", to_user_id=" + to_user_id +
                ", parent_postings_id=" + parent_postings_id +
                ", contents='" + contents + '\'' +
                ", is_enabled=" + is_enabled +
                ", message_type=" + message_type +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", original_id=" + original_id +
                ", is_read=" + is_read +
                ", profile_img='" + profile_img + '\'' +
                ", username='" + username + '\'' +
                ", start_read=" + start_read +
                ", end_read=" + end_read +
                ", role_id=" + role_id +
                '}';
    }
}
