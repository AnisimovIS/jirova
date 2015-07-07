package com.springapp.services.layer;

import com.springapp.entity.Layer;

import java.util.Date;
import java.util.List;

public interface LayerService {
    Layer get(long id);
    List<Layer> getByDate(Date date);

    List<Layer> getAll();

    void delete(long id);
    void add(Layer layer);
}
