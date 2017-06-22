package com.whichcontact.rest.DigitalIndia;

/**
 * @author TS001127
 *
 */
public class COMPANYNAME {
	private String description;

	private String length;

	private String type;

	private String size;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ClassPojo [description = " + description + ", length = " + length + ", not null = " + ", type = " + type
				+ ", size = " + size + "]";
	}
}
