package com.zjyx.vote.api.service;

import java.util.List;

import com.zjyx.vote.api.model.persistence.Carousel;

public interface ICarouselService {
		
	 public List<Carousel> listCarousel(Carousel carousel);
		
	 public Carousel selectById(Long id);
		
	 public int deleteById(Long id);
		
	 public int saveOrUpdateCarousel(Carousel carousel);
}
