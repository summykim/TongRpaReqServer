package com.skcc.tongrpa.sample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.skcc.tongrpa.sample.MapDto;

@Mapper
public interface MapMapper {
	public List<MapDto> listmap();
}
