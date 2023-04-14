
package com.ayseozcan.cardealer.main;

import com.ayseozcan.cardealer.entity.Car;
import com.ayseozcan.cardealer.repository.CarRepository;
import com.ayseozcan.cardealer.repository.DealershipRepository;
import com.ayseozcan.cardealer.util.Constant;
import com.ayseozcan.cardealer.util.FileUtils;
import java.util.Scanner;


public class Manager {

    Scanner scanner = new Scanner(System.in);
    private final CarRepository carRepository = new CarRepository();
    private final DealershipRepository dealershipRepository = new DealershipRepository();

    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.menu();

    }

    public void menu() {

        int input = 0;
        do {
            System.out.println("0-cikis");
            System.out.println("1-Cars lari dosyadan veritabanina kaydet");
            System.out.println("2-Araba Ekle");
            System.out.println("3-Araba Guncelle");
            System.out.println("4-Araba Sil");
            System.out.println("5-Arabalari listele");
            System.out.println("6-DealerShips leri dosyadan veritabanina kaydet");
            input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    if (carRepository.databaseControl()) {
                        System.out.println("Veri tabanina veriler zaten kaydedilmis durumda");
                    } else {
                        carRepository.saveAll(FileUtils.getCarList(FileUtils.readFile(Constant.carFile)));
                        System.out.println("Veriler basariyla kaydedildi");
                    }
                    break;
                default:
                    System.out.println("sayi girin");
                case 2:
                    carRepository.save(getCarInformation());
                case 3:
                    System.err.println("guncellemek istediginiz arabanin id sini girin");
                    long updateId = Long.parseLong(scanner.nextLine());
                    carRepository.update(getCarInformation(), updateId);
                case 4:
                    System.err.println("silmek istediginiz arabanin id sini girin");
                    long deletedetId = Long.parseLong(scanner.nextLine());
                    carRepository.delete(deletedetId);
                case 5:
                    carRepository.findAll().forEach(x -> System.out.println(x.toString()));
                case 6:
                    if (dealershipRepository.databaseControl()) {
                        System.out.println("Veri tabanina veriler zaten kaydedilmis durumda");
                    } else {
                        dealershipRepository.saveAll(FileUtils.getDealershipList(FileUtils.readFile(Constant.dealershipFile)));
                        System.out.println("Veriler basariyla kaydedildi");
                    }
                    break;
            }
        } while (true);
    }

    public Car getCarInformation() {
        System.out.println("Lutfen Araba Markasi Girin");
        String brand = scanner.nextLine();

        System.out.println("Lutfen Araba Modelini Girin");
        String carModel = scanner.nextLine();

        System.out.println("Lutfen Araba Yilini Girin");
        String modelYear = scanner.nextLine();

        System.out.println("Lutfen Arabanin dealer id sini girin");
        Long dealershipId = Long.parseLong(scanner.nextLine());

        return new Car(brand, carModel, modelYear, dealershipId);
    }
}
