import java.util.*;

public class Menu {

    private Menu() {
    }

    public static void printOption() {
        System.out.println("Menu:\n1. Obstaw liczby." +
                                "\n2. Przeprowadź losowanie." +
                                "\n3. Sprawdź wyniki" +
                                "\n4. Pokaż historię losowań tej gry" +
                                "\n5. Usuń historię losowań tej gry" +
                                "\n6. Pobierz najnowszą historię losowań" +
                                "\n7. Sprawdź prawdziwe wyniki Lotto" +
                                "\n8. Sprawdź czy trafiłbyś kiedykolwiek w historii" +
                                "\n9. Wyjście.");
    }

    public static int getUserChoice() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static ArrayList<Integer> getNumbersFromUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj 6 liczb od 1 do 49 (oddziel je spacjami)");
        String userNumbersToSplit = sc.nextLine();
        String[] userNumbers = userNumbersToSplit.split(" ");
        ArrayList<Integer> userNumbersAsIntegers = new ArrayList<>(6);
        for (String s : userNumbers) userNumbersAsIntegers.add(Integer.valueOf(s));
        Collections.sort(userNumbersAsIntegers);
        System.out.println("Dziękujemy za podanie 6 liczb");
        RealLotto.pressToContinue();
        return userNumbersAsIntegers;
    }

    public static void printOptionToChooseNumbers() {
        System.out.println("Menu:\n1. Wybieram liczby sam." +
                                "\n2. Chybił trafił.");
    }

    public static List<Integer> getRandomNumbers() {
        System.out.println("Liczby na chybił trafił zostały wybrane.");
        LottoNumbers.stopProgram(Game.WAIT_TIME_2);
        RealLotto.pressToContinue();
        return LottoNumbers.chooseSix();
    }

    public static List<Integer> getNumbers() {
        List<Integer> choosenNumbers = new ArrayList<>();
        printOptionToChooseNumbers();
        switch (getUserChoice()) {
            case 1 -> choosenNumbers = getNumbersFromUser();
            case 2 -> choosenNumbers = getRandomNumbers();
            default -> System.out.println("Wybrano niewłaściwą opcję");
        }
        return choosenNumbers;
    }

    public static void showResults(List<Integer> lottoNumbers, List<Integer> userNumbers) {
        if (lottoNumbers.size() < 6) {
            System.out.println("Najpierw musisz obstawić liczby");
            RealLotto.pressToContinue();
        } else if (userNumbers.isEmpty()) {
            System.out.println("Najpierw musisz przeprowadzić losowanie");
            RealLotto.pressToContinue();
        } else {
            System.out.println("Twoje liczby: " + userNumbers);
            LottoNumbers.stopProgram(Game.WAIT_TIME_2_5);
            System.out.println("Wylosowane liczby: " + lottoNumbers);
            LottoNumbers.stopProgram(Game.WAIT_TIME_2_5);
            System.out.println("Trafiłeś " + lottoNumbers.stream().filter(userNumbers::contains).count() + " liczb");
        }

    }
}
