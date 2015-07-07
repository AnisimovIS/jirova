package com.springapp.services.layer;

import com.springapp.entity.Layer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LayerServiceImpl implements LayerService {
    private List<Layer> layers = new ArrayList<>();

    @Override
    public Layer get(long id) {
        return null;
    }

    @Override
    public List<Layer> getByDate(Date date) {
        return null;
    }

    @Override
    public List<Layer> getAll() {
        return this.layers;
    }


    @Override
    public void delete(long id) {
    }

    @Override
    public void add(Layer layer) {
        layers.add(layer);
    }
}
