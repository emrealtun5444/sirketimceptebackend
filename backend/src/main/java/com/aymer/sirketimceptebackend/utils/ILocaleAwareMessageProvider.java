package com.aymer.sirketimceptebackend.utils;

public interface ILocaleAwareMessageProvider {
	public String getMessage(String key);
	public String getMessage(String key, Object[] args);
}
