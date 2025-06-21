package br.com.alura.fipesearch.main;

import br.com.alura.fipesearch.models.*;
import br.com.alura.fipesearch.services.APIConsumption;
import br.com.alura.fipesearch.services.ConvertData;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
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
        try{
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

            List<Model> models = convertData.getData(json, ModelsFipeAPI.class).modelos()
                    .stream()
                    .map(Model::new)
                    .toList();

            System.out.println("Qual modelo de veículo você deseja consultar?");
            models.stream().sorted(Comparator.comparing(Model::getId)).forEach(System.out::println);
            System.out.print("Digite a sua escolha: ");
            var tempModel = sc.nextLine();

            System.out.println("Escolha o modelo");
            models = models.stream()
                    .filter(m -> m.getName().toLowerCase().contains(tempModel.toLowerCase()))
                    .sorted(Comparator.comparing(Model::getId))
                    .toList();
            if (models.isEmpty()) throw new InvalidOptionException("Nenhum modelo encontrado!");
            models.forEach(System.out::println);

            System.out.print("Escolha: ");
            var model = sc.nextInt();
            sc.nextLine();

            json = api.getData(URI + type + "/marcas/" + brand + "/modelos/"+model+"/anos");
            List<CarYearFipeAPI> carYearFipeAPIS = convertData.getListData(json, CarYearFipeAPI.class);

            List<Car> cars = new ArrayList<>();

            for (CarYearFipeAPI c : carYearFipeAPIS) {
                try{
                    json = api.getData(URI + type + "/marcas/" + brand + "/modelos/"+model+"/anos/"+c.codigo());
                    CarFipeAPI carFipeAPI =  convertData.getData(json, CarFipeAPI.class);
                    cars.add(new Car(carFipeAPI));
                }catch(NumberFormatException e){
                    System.out.println("Erro ao consultar o carro "+c.codigo());
                }
            }
            System.out.println("Valores da FIP: ");
            cars.forEach(System.out::println);
        } catch (InvalidOptionException e){
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println("Nenhum retorno com o valor informado!");
        }
        catch (JsonSyntaxException e){
            System.out.println("Não foi possível coletar os dados, tente novamente mais tarde!");
        }
    }
}
