package com.zjyx.authority.api.service;

public interface IEsService {

	public <T> String save(T t,String index,String type);
}
