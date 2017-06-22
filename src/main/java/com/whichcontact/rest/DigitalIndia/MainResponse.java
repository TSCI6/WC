package com.whichcontact.rest.DigitalIndia;

/**
 * @author TS001127
 *
 */
public class MainResponse {

	public String count;

	public Records[] records;

	public String success;

	public String total_records;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Records[] getRecords() {
		return records;
	}

	public void setRecords(Records[] records) {
		this.records = records;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getTotal_records() {
		return total_records;
	}

	public void setTotal_records(String total_records) {
		this.total_records = total_records;
	}

}