package br.com.alura.fipesearch.main;

import br.com.alura.fipesearch.models.*;
import br.com.alura.fipesearch.services.APIConsumption;
import br.com.alura.fipesearch.services.ConvertData;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final Scanner sc = new Scanner(System.in);
    private final APIConsumption api = new APIConsumption();
    private final ConvertData convertData = new ConvertData();

    private final String URI = "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu() {
        System.out.println("Qual tipo de veículo você deseja consultar?");
        System.out.println("Carros");
        System.out.println("Motos");
        System.out.println("Caminhões");
        System.out.print("Digite: ");
        var type = sc.nextLine();
        if (("carros").contains(type.toLowerCase())) type = "carros";
        else if (("motos").contains(type.toLowerCase())) type = "motos";
        else if (("caminhões").contains(type.toLowerCase())) type = "caminhoes";
        else throw new InvalidOptionException("Tipo de veículo inválido!");

        var json = api.getData(URI + type + "/marcas");

        List<Brand> brands = convertData.getListData(json, BrandFipeAPI.class).stream().map(Brand::new).toList();
        System.out.println("Escolha o modelo do veículo: ");
        brands.stream().sorted(Comparator.comparing(Brand::getId)).forEach(System.out::println);
        System.out.print("Digite a sua escolha: ");
        var brand = sc.nextInt();
        sc.nextLine();

        json = api.getData(URI + type + "/marcas/" + brand + "/modelos");

        System.out.println(json);

        List<Model> models = convertData.getData(json, ModelsFipeAPI.class).modelos()
                .stream()
                .map(Model::new)
                .toList();

        models.forEach(System.out::println);



    }
}
