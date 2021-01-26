package com.aymer.sirketimceptebackend.report.model;


public interface ReportBaseEnum<E>  {
	public ColumnDataType getColumnDataType();
	public E getValue();	
	public String getLabel();
}
