import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CountNumbers {
    public static void countNumbers() {
        List<Integer> allNumbers = new ArrayList<>();
        try (
                var fileReader = new FileReader(RealLotto.FILE_NAME);
                var reader = new BufferedReader(fileReader)
        ) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                Integer[] sixArray = getNumbers(nextLine);
                allNumbers.addAll(Arrays.asList(sixArray));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        allNumbers.sort(Integer::compareTo);
        Map<Integer, Integer> map = new TreeMap<>();


        for (int i = 1; i < 49; i++) {
            int j = 0;
            for (int x :
                    allNumbers) {
                if (x == i) {
                    j++;
                }

            }
            map.put(i, j);
        }
        System.out.println("Oto liczby, które padały najczęściej w historii:");
        map = sortingMapByDescendingValues(map);
        map.forEach((key, value) -> System.out.println(key + " = " + value + "x"));
        LottoNumbers.stopProgram(Game.WAIT_TIME_2);
        RealLotto.pressToContinue();
    }

    public static Integer[] getNumbers(String text) {
        String pureNumbers = text.substring(text.lastIndexOf(" ")).replace(",", " ").trim();
        Integer[] sixArrays = new Integer[6];
        int j;
        for (int i = 0; i < 6; i++) {
            if (i < 5) {
                j = 0;
                String temp = pureNumbers.substring(0, pureNumbers.indexOf(" "));
                j += temp.length() + 1;
                pureNumbers = pureNumbers.substring(j);
                sixArrays[i] = Integer.parseInt(temp);
            } else {
                sixArrays[i] = Integer.parseInt(pureNumbers);
            }
        }
        return sixArrays;
    }

    private static Map<Integer,Integer> sortingMapByDescendingValues(Map<Integer,Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((Map.Entry.<Integer, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}