package br.com.alura.fipesearch.models;

public class Car {
    private String id;
    private double value;
    private String brand;
    private String model;
    private int year;
    private String monthReference;
    private String fuel;

    public Car(CarFipeAPI carFipeAPI) {
        this.value = Double.parseDouble(carFipeAPI.Valor()
                .replace("R$", "")
                .replace(".", "")
                .replace(",", "."));
        this.brand = carFipeAPI.Marca();
        this.model = carFipeAPI.Modelo();
        this.year = carFipeAPI.AnoModelo();
        this.monthReference = carFipeAPI.MesReferencia();
        this.fuel = carFipeAPI.Combustivel();
        this.id = carFipeAPI.CodigoFipe();
    }

    @Override
    public String toString() {
        return id+") "+model+", "+year+" | R$ "+value;
    }
}
