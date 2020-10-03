import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class RealLotto {
    public static final String FILE_NAME = "Lotto.txt";
    public static void realLottoMenu() {
        printOption();
        switch (Menu.getUserChoice()) {
            case 1:
                String userDate = getDateFromUser();
                boolean lotteryInUserDateExists = printResultsFromThisDate(userDate);
                if (!lotteryInUserDateExists) {
                    System.out.println("W podanym dniu nie odbyło się losowanie");
                    LottoNumbers.stopProgram(Game.WAIT_TIME_2);
                    boolean closestDateWithLotteryFound;
                    do {
                        closestDateWithLotteryFound = findNextResult(userDate);
                    } while (!closestDateWithLotteryFound);
                }
                break;
            case 2:
                CountNumbers.countNumbers();
                break;
            case 3:
        }
    }

    private static void printOption() {
        System.out.println("Menu:\n1. Sprawdź wyniki z konkretnego dnia." +
                                "\n2. Sprawdź, które liczby wypadały ile razy." +
                                "\n3. Powrót");
    }

    private static String getDateFromUser() {

        System.out.println("Historia dotyczy gier z zakresu: 27.01.1957 - " + getLastLotteryDate());
        String day = convertNumberToString(getNumberFromUser(0, 32, "dzień"));
        String month = convertNumberToString(getNumberFromUser(0, 12, "miesiąc"));
        String year = convertNumberToString(getNumberFromUser(1957, 2020, "rok"));
        return connectStrings(day, month, year);
    }

    private static String getLastLotteryDate() {
        try (
                var fileReader = new FileReader(RealLotto.FILE_NAME);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
            int lastLine = lastLineNumber();
            int i = 1;
            while ((nextLine = reader.readLine()) != null) {
                if (lastLine == i) {
                    return nextLine.substring(nextLine.indexOf(" ") + 1, nextLine.lastIndexOf(" "));
                } else {
                    i++;
                }
            }
        } catch(IOException e) {
            System.out.println("Nie znaleziono pliku");
        }
        return "";
    }

    public static int lastLineNumber() {
        File file = new File(FILE_NAME);
        LineNumberReader lineNumberReader;
        try {
            lineNumberReader = new LineNumberReader(new FileReader(file));
            lineNumberReader.skip(Long.MAX_VALUE);
            int lines = lineNumberReader.getLineNumber();
            lineNumberReader.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int getNumberFromUser(int intMin, int intMax, String text) {
        Scanner sc = new Scanner(System.in);
        boolean error = true;
        int num;
        do {
            System.out.println("Podaj " + text);
            num = sc.nextInt();
            if (num >= intMin && num <= intMax) {
                error = false;
            } else {
                System.out.println("Podaj właściwy " + text);
            }
        } while(error);
        return num;
    }

    private static String convertNumberToString(int numInt) {
        String num = Integer.toString(numInt);
        if (num.length() == 1) {
            num = "0" + num;
        }
        return num;
    }

    private static String connectStrings(String s1, String s2, String s3){
        return s1 + "." + s2 + "." + s3;
    }

    private static boolean printResultsFromThisDate(String userDate) {

        try (
                var fileReader = new FileReader(RealLotto.FILE_NAME);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    if (nextLine.contains(userDate)) {
                        System.out.println("Wyniki losowania z wybranego dnia: " + userDate + " to:" +
                                nextLine.substring(nextLine.lastIndexOf(" ")));
                        LottoNumbers.stopProgram(Game.WAIT_TIME_2);
                        System.out.println("\nByło to " + nextLine.substring(0, nextLine.indexOf(".")) + " losowanie w historii.");
                        LottoNumbers.stopProgram(Game.WAIT_TIME_2);
                        return true;
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean findNextResult(String fullDate) {
        boolean resultIsFound;
        int i = 1;
        do {
            String newDate = getNextDate(fullDate, i);
            resultIsFound = printResultFromNextFoundDate(newDate);
            i++;
            } while (!resultIsFound);
        return true;
    }

    private static String getNextDate(String userDate, int i) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(userDate));
        } catch(ParseException e){
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, i);
        return sdf.format(c.getTime());
    }

    private static boolean printResultFromNextFoundDate (String newDate) {

        try (
                var fileReader = new FileReader(RealLotto.FILE_NAME);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    if (nextLine.contains(newDate)) {
                        System.out.println("Najbliższe losowanie odbyło się " + newDate + " i padły w nim następujące liczby:" +
                                nextLine.substring(nextLine.lastIndexOf(" ")));
                        LottoNumbers.stopProgram(Game.WAIT_TIME_2);
                        System.out.println("Było to " + nextLine.substring(0, nextLine.indexOf(".")) + " losowanie w historii.");
                        LottoNumbers.stopProgram(Game.WAIT_TIME_2);
                        RealLotto.pressToContinue();
                        return true;
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void downloadHistory() {
        try {
            download();
            System.out.println("Pomyślnie pobrano wyniki losowań");
        } catch (IOException e) {
            System.out.println("Wystąpił problem przy pobieraniu pliku");
        }
        RealLotto.pressToContinue();
    }

    public static void download() throws IOException {
        URL website = new URL("http://www.mbnet.com.pl/dl.txt");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("Lotto.txt");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    public static void pressToContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Naciśnij dowolny klawisz aby kontynuować.");
        sc.nextLine();
    }
}