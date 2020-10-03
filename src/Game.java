import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int WAIT_TIME_4 = 4000;
    public static final int WAIT_TIME_3 = 3000;
    public static final int WAIT_TIME_2_5 = 2500;
    public static final int WAIT_TIME_2 = 2000;
    public static final int WAIT_TIME_1_5 = 1500;

    public static void main(String[] args) {
        List<Integer> userNumbers = new ArrayList<>();
        List<Integer> lottoNumbers = new ArrayList<>();
        FileMethods fileMethods = new FileMethods();

        boolean exit = false;
        do {
        Menu.printOption();
            switch (Menu.getUserChoice()) {
                case 1 -> userNumbers = Menu.getNumbers();
                case 2 -> {
                    lottoNumbers = LottoNumbers.chooseSix();
                    fileMethods.saveToFile(lottoNumbers);
                    LottoNumbers.showMustGoOn();
                }
                case 3 -> Menu.showResults(userNumbers, lottoNumbers);
                case 4 -> fileMethods.printHistory();
                case 5 -> fileMethods.deleteHistory();
                case 6 -> RealLotto.downloadHistory();
                case 7 -> RealLotto.realLottoMenu();
                case 8 -> RealLottoCompare.compare(userNumbers);
                case 9 -> exit = true;
                default -> {
                    System.out.println("Wybrano niewłaściwą opcję");
                    RealLotto.pressToContinue();
                }
            }
        } while (!exit);
    }
}