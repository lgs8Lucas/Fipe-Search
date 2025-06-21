package br.com.alura.fipesearch.models;

public class Brand {
    private final String brand;
    private final int id;

    public Brand(BrandFipeAPI b) {
        this.brand = b.nome();
        this.id = Integer.parseInt(b.codigo());
    }

    @Override
    public String toString() {
        return id+") "+brand;
    }

    public int getId() {
        return id;
    }
}
