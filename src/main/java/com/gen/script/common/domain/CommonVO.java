package com.gen.script.common.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class CommonVO {
	private int no;
	private String status;
	private String chkbox;
	private String pop;
	private String insDt;
	private String updDt;
	private String insId;
	private String updId;
	private Integer page;
	private Integer size;
	private String searchTxt;
	
	public CommonVO() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		insDt = sdf.format(new Date());
		updDt = insDt;
	}
}
