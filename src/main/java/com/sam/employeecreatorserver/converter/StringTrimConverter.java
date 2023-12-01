package com.sam.employeecreatorserver.converter;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class StringTrimConverter implements Converter<String, String> {

	public String convert(MappingContext<String, String> context) {
		
		if(context.getSource() == null) {
			return null;
		}
		
		return context.getSource().trim();
		
	} 

}
