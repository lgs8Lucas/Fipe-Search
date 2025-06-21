package br.com.alura.fipesearch.models;

public class Model {
    int id;
    String name;

    public Model(ModelFipeAPI m) {
        this.id = m.codigo();
        this.name = m.nome();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.id + ") " + this.name;
    }
}
