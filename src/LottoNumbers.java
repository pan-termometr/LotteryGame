import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbers {

    static List<Integer> lottoNumbers = new ArrayList<>();
    //ThreadLocalRandom.current().nextInt(0, 50)
    public static List<Integer> chooseSix() {
        List<Integer> lottoNumbers =
                IntStream.range(1, 49)
                        .boxed()
                        .collect(Collectors.toList());
        Collections.shuffle(lottoNumbers);
        List<Integer> sixLottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            sixLottoNumbers.add(lottoNumbers.get(i));
        }
        LottoNumbers.lottoNumbers = lottoNumbers;
        Collections.sort(sixLottoNumbers);
        return sixLottoNumbers;
    }

    public static void showMustGoOn() {
        System.out.println("Dzień dobry. Witam Ciebie Piotrulo i zapraszam na losowanie Dużego Lotka.");
        stopProgram(Game.WAIT_TIME_3);
        System.out.println("Nad poprawnym przebiegiem losowania będzie czuwać komisja kontroli gier i zakładów:");
        stopProgram(Game.WAIT_TIME_4);
        System.out.println("\n(͡° ͜ ͡°)    ¯|_(ツ)_/¯    ┏(‘▀_▀’)ノ\n");
        stopProgram(Game.WAIT_TIME_3);
        System.out.println("Komora maszyny losującej jest pusta:");
        stopProgram(Game.WAIT_TIME_1_5);
        System.out.println("\n[          ]    (1)  (2)  (3)  (4)  (5)  (6)  (7)" +
                           "\n|          |    (8)  (9) (10) (11) (12) (13) (14)" +
                           "\n|          |   (15) (16) (17) (18) (19) (20) (21)" +
                           "\n|          |   (22) (23) (24) (25) (26) (27) (28)" +
                           "\n|          |   (29) (30) (31) (32) (33) (34) (35)" +
                           "\n|          |   (36) (37) (38) (39) (40) (41) (42)" +
                           "\n|__________|   (43) (44) (45) (46) (47) (48) (49)");
        stopProgram(Game.WAIT_TIME_2);
        System.out.println("\nNastępuje zwolnienie blokady:\n");
        stopProgram(Game.WAIT_TIME_1_5);
        System.out.println("[          ]    " +
                         "\n|          |    " +
                         "\n|          |    " +
                         "\n|          |    " +
                         "\n|  ()()()  |    " +
                         "\n|()()()()()|    " +
                         "\n|()()()()()|    ");
        stopProgram(Game.WAIT_TIME_3);
        System.out.println("\nRozpoczynamy losowanie 6 liczb:\n");
        stopProgram(Game.WAIT_TIME_3);
        System.out.print("[");
        for (int i = 0; i < 6; i++) {
            if (i < 5) {
                System.out.print("(" + lottoNumbers.get(i) + ")");
                stopProgram(Game.WAIT_TIME_2_5);
            } else {
                System.out.print("(" + lottoNumbers.get(i) + ")]");
            }
        }
        stopProgram(Game.WAIT_TIME_3);
        System.out.println("\n\nDziękujemy za udział w losowaniu. Życzymy dobrej nocy.");
        stopProgram(Game.WAIT_TIME_3);
    }

    public static void stopProgram(int milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}