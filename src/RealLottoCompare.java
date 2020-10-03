import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RealLottoCompare {

    static List<List<Integer>> list = allNumbersToArrays();

    public static void compare(List<Integer> userNumber) {
        if (userNumber.isEmpty()) {
            System.out.println("Najpierw musisz wybrać liczby");
            RealLotto.pressToContinue();
        } else {
            int num0 = 0;
            int num1 = 0;
            int num2 = 0;
            int num3 = 0;
            int num4 = 0;
            int num5 = 0;
            int num6 = 0;

            for (int i = 0; i < RealLottoCompare.list.size(); i++) {
                if (showResults(RealLottoCompare.list.get(i), userNumber) == 0)
                    num0++;
                if (showResults(RealLottoCompare.list.get(i), userNumber) == 1)
                    num1++;
                if (showResults(RealLottoCompare.list.get(i), userNumber) == 2)
                    num2++;
                if (showResults(RealLottoCompare.list.get(i), userNumber) == 3)
                    num3++;
                if (showResults(RealLottoCompare.list.get(i), userNumber) == 4)
                    num4++;
                if (showResults(RealLottoCompare.list.get(i), userNumber) == 5)
                    num5++;
                if (showResults(RealLottoCompare.list.get(i), userNumber) == 6)
                    num6++;
            }
            System.out.println("Trafiłeś:" +
                    "\nszóstkę " + num6 + " razy" +
                    "\npiątkę " + num5 + " razy" +
                    "\nczwórkę " + num4 + " razy" +
                    "\ntrójkę " + num3 + " razy" +
                    "\ndwójkę " + num2 + " razy" +
                    "\njedynkę " + num1 + " razy" +
                    "\nżadną z liczb " + num0 + " razy");

            if (num6 > 0) {
                printDateWhenSix(userNumberToString(userNumber));
            }
            RealLotto.pressToContinue();
        }
    }

    private static List<List<Integer>> allNumbersToArrays() {
        List<List<Integer>> allArrays = new ArrayList<>();
        try (
                var fileReader = new FileReader(RealLotto.FILE_NAME);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
            for (int i = 0; i < RealLotto.lastLineNumber(); i++){
            while ((nextLine = reader.readLine()) != null) {
                    allArrays.add(i, getNumbers(nextLine));
                }
            }
            return allArrays;
        } catch (IOException e) {
            System.out.println("Błąd");
        }
        return null;
    }

    private static List<Integer> getNumbers(String text) {
        String pureNumbers = text.substring(text.lastIndexOf(" ")).replace(",", " ").trim();
        List<Integer> sixArrays = new ArrayList<>();
        int j;
        for (int i = 0; i < 6; i++) {
            if (i < 5) {
                j = 0;
                String temp = pureNumbers.substring(0, pureNumbers.indexOf(" "));
                j += temp.length() + 1;
                pureNumbers = pureNumbers.substring(j);
                sixArrays.add(Integer.parseInt(temp));
            } else {
                sixArrays.add(Integer.parseInt(pureNumbers));
            }
        }
        return sixArrays;
    }

    public static long showResults(List<Integer> lottoNumbers, List<Integer> userNumbers) {
        if(lottoNumbers.size() < 6) {
            System.out.println("Obstaw najpierw liczby!");
        } else {
            return lottoNumbers.stream().filter(userNumbers::contains).count();
        }
        return 0;
    }

    private static String userNumberToString(List<Integer> userNumber) {
        StringBuilder sb  = new StringBuilder();
        Iterator<Integer> iterator = userNumber.iterator();
        while(iterator.hasNext())
        {
            sb.append(iterator.next());
            if(iterator.hasNext()){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static void printDateWhenSix(String userNumber) {
        try (
                var fileReader = new FileReader(RealLotto.FILE_NAME);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
            for (int i = 0; i < RealLotto.lastLineNumber(); i++) {
                while ((nextLine = reader.readLine()) != null) {
                    if (userNumber.equals(nextLine.substring(nextLine.lastIndexOf(" ") + 1))) {
                        System.out.println("Trafiłbyś szóstkę w dniu: " +
                                nextLine.substring(nextLine.indexOf(" ") + 1, nextLine.lastIndexOf(" ")) +
                                " podczas " + nextLine.substring(0, nextLine.indexOf(" ")) + " losowania w historii");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Wystąpił nieoczekiwany błąd");
        }
    }
}
