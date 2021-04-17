package com.company;

public class Main {

    public static void main(String[] args) {
        double rub = 1;
        double usd = 0.014;

        Neuron neuron = new Neuron();

        //Запускаем обучение
        int i = 0;
        do {
            i++;
            neuron.Train(rub, usd);
            System.out.println("Иттерация: " + i + "; ошибка: " + neuron.lastError);
        } while (neuron.lastError > neuron.step || neuron.lastError < -neuron.step);
        System.out.println("Обучение завершено!");
        //Тест
        System.out.println(neuron.processInputData(10) + " долларов в 10 рублях");
        System.out.println(neuron.processInputData(5) + " долларов в 5 рублях");
        System.out.println(neuron.restoreInputData(50) + " рублей в 50 долларов");
    }
}

class Neuron {
    private double weight = 0.5;
    double lastError; //ошибка
    double step = 0.0001;

    //конвертация рубли в доллары
    public double processInputData(double input){
     return input * weight;
    }
    //конвертация доллары в рубли
    public double restoreInputData(double output){
     return output / weight;
    }
    //обучение
    public void Train (double input, double expectedResult){
         double actualResult = input * weight; //текущий результат
         lastError = expectedResult - actualResult;
         //корректировка веса высчитывается вычитанием из ошибки результата, который выдает сеть
         double correction = (lastError - actualResult) * step; //умножение на шаг необходимо для более точного обучения нейрона
         weight += correction;
    }
}