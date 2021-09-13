package com.cardanonft.api.vo.message;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by choi on 2017. 7. 13..
 */
@Alias("PostingReads")
public class PostingReads {

    private long posting_read_id;
    private long user_id;
    private long posting_id;
    private Date created_at;
    private int is_enabled;
    private long start_read;
    private long end_read;

    public PostingReads() {
    }

    public PostingReads(long posting_read_id, long user_id, long posting_id, Date created_at, int is_enabled, long start_read, long end_read) {
        this.posting_read_id = posting_read_id;
        this.user_id = user_id;
        this.posting_id = posting_id;
        this.created_at = created_at;
        this.is_enabled = is_enabled;
        this.start_read = start_read;
        this.end_read = end_read;
    }

    public long getPosting_read_id() {
        return posting_read_id;
    }

    public void setPosting_read_id(long posting_read_id) {
        this.posting_read_id = posting_read_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getPosting_id() {
        return posting_id;
    }

    public void setPosting_id(long posting_id) {
        this.posting_id = posting_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(int is_enabled) {
        this.is_enabled = is_enabled;
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

    @Override
    public String toString() {
        return "com.albam.api.vo.store.PostingReadsVO{" +
                "posting_read_id=" + posting_read_id +
                ", user_id=" + user_id +
                ", posting_id=" + posting_id +
                ", created_at=" + created_at +
                ", is_enabled=" + is_enabled +
                ", start_read=" + start_read +
                ", end_read=" + end_read +
                '}';
    }
}
