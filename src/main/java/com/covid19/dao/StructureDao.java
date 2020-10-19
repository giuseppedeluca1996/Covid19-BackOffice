package com.covid19.dao;

import com.covid19.model.Structure;

import java.util.Map;

public abstract class StructureDao implements AbstractDao<Structure> {

    @Override
    public abstract void save(Structure entity);

    @Override
    public abstract Structure update(Structure newEntity, Integer id);

    @Override
    public abstract void delete(Integer id);

    @Override
    public abstract Structure getById(Integer id);

    @Override
    public abstract Map<String, Object> getAll(Integer page, Integer size);

    public abstract Map<String, Object> getAllHotel(Integer page, Integer size);

    public abstract Map<String, Object> getAllRestaurant(Integer page, Integer size);

    public abstract Map<String, Object> getAllAttraction(Integer page, Integer size);

    public abstract  Map<String, Object> getAllHotelByText(Integer page, Integer size, String text);

    public abstract Map<String, Object> getAllRestaurantByText(Integer page, Integer size, String text);

    public abstract Map<String, Object> getAllAttractionByText(Integer page, Integer size, String text);


}
