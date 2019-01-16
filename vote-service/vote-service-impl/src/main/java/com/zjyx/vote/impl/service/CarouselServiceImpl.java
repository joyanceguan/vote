package com.zjyx.vote.impl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjyx.vote.api.model.persistence.Carousel;
import com.zjyx.vote.api.service.ICarouselService;
import com.zjyx.vote.impl.mapper.CarouselMapper;

@Service
public class CarouselServiceImpl implements ICarouselService{

	@Autowired
	CarouselMapper carouselMapper;
	
	@Override
	public List<Carousel> listCarousel(Carousel carousel) {
		return carouselMapper.listCarousel(carousel);
	}

	@Override
	public Carousel selectById(Long id) {
		return carouselMapper.selectById(id);
	}

	@Override
	public int deleteById(Long id) {
		return carouselMapper.deleteById(id);
	}
	
	@Override
	public int saveOrUpdateCarousel(Carousel carousel) {
		int flag=0;
		if(carousel.getId()!=null){
			flag=carouselMapper.updateCarousel(carousel);
		}else{
			flag=carouselMapper.saveCarousel(carousel);
		}
		return flag;
	}

}
