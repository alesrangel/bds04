package com.devsuperior.bds04.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;


@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepository;

	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();
		City city = cityRepository.getOne(dto.getCityId());
		entity.setCity(city);
		entity.setDate(dto.getDate());
		entity.setName(dto.getName());
		entity.setUrl(dto.getUrl());
		entity = repository.save(entity);
		return new EventDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(PageRequest pageRequest) {
		Page<Event> page = repository.findAll(pageRequest);
		return page.map(event -> new EventDTO(event));
	}
}
