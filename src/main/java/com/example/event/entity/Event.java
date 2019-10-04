package com.example.event.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "eventType")
	private String eventType;

	@Column(name = "eventDescription")
	private String eventDescription;

	@Column(name = "eventDate")
	private Date eventDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public static class Builder {
		private Long id;
		private String eventType;
		private String eventDescription;
		private Date eventDate;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder eventType(String eventType) {
			this.eventType = eventType;
			return this;
		}

		public Builder eventDescription(String eventDescription) {
			this.eventDescription = eventDescription;
			return this;
		}

		public Builder eventDate(Date eventDate) {
			this.eventDate = eventDate;
			return this;
		}

		public Event build() {
			return new Event(this);
		}
	}

	private Event(Builder builder) {
		this.id = builder.id;
		this.eventType = builder.eventType;
		this.eventDescription = builder.eventDescription;
		this.eventDate = builder.eventDate;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventType=" + eventType + ", eventDescription=" + eventDescription
				+ ", eventDate=" + eventDate + "]";
	}
}
