package br.com.alura.fipesearch.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ConvertData implements IConvertData {
    private Gson gson = new Gson();

    @Override
    public <T> T getData(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public <T> List<T> getListData(String json, Class<T> clazz) {
        Type typeOfList = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, typeOfList);
    }
}
