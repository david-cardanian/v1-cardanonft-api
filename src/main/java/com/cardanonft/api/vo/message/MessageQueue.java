package com.cardanonft.api.vo.message;


import com.cardanonft.api.vo.account.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.data.annotation.TypeAlias;

import java.util.Date;
import java.util.List;

@TypeAlias("messageQueue")
public class MessageQueue {
	public MessageQueue() {

	}

	public MessageQueue(String title, String message, int push_id, List<User> _receivers) {
		message_type = "PUSH";
		title_text = title;
		content_text = message;

		JsonObject data_map_object = new JsonObject();
		data_map_object.addProperty("message", message);
		data_map_object.addProperty("push_id", push_id);
		data_map = data_map_object.toString();

		JsonArray receivers_array = new JsonArray();
        if (_receivers != null) {
            for (User elem : _receivers) {
                if(elem.getPush_token_owner() != null)
                    receivers_array.add(new JsonPrimitive(elem.getPush_token_owner()));
            }
        }
        receivers = receivers_array.toString();

	}

	public int getMessage_queue_id() {
		return message_queue_id;
	}
	public void setMessage_queue_id(int message_queue_id) {
		this.message_queue_id = message_queue_id;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getTitle_text() {
		return title_text;
	}
	public void setTitle_text(String title_text) {
		this.title_text = title_text;
	}
	public String getContent_text() {
		return content_text;
	}
	public void setContent_text(String content_text) {
		this.content_text = content_text;
	}
	public String getData_map() {
		return data_map;
	}
	public void setData_map(String data_map) {
		this.data_map = data_map;
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
	public String getSend_status() {
		return send_status;
	}
	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}
	public String getReceivers() {
		return receivers;
	}
	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}
	private int message_queue_id;
	private String message_type;
	private String title_text;
	private String  content_text;
	private String data_map;
	private Date created_at;
	private Date updated_at;
	private String send_status;
	private String receivers;
}
