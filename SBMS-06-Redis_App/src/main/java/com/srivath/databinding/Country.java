package com.srivath.databinding;

import java.io.Serializable;

import lombok.Data;

@Data
public class Country implements Serializable {

	private Integer sno;
	private String countryName;
	private String countryCode;
}
