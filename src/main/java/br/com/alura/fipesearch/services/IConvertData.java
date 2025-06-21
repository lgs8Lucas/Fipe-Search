package br.com.alura.fipesearch.services;

public interface IConvertData {
    <T> T getData(String json, Class<T> clazz);
}
