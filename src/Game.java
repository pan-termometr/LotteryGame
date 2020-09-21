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
                case 1:
                    userNumbers = Menu.getNumbers();
                    break;
                case 2:
                    lottoNumbers = LottoNumbers.chooseSix();
                    fileMethods.saveToFile(lottoNumbers);
                    LottoNumbers.showMustGoOn();
                    break;
                case 3:
                    System.out.println("Twoje liczby: " + userNumbers);
                    LottoNumbers.stopProgram(WAIT_TIME_3);
                    System.out.println("Wylosowane liczby: " + lottoNumbers);
                    LottoNumbers.stopProgram(WAIT_TIME_3);
                    Menu.showResults(userNumbers, lottoNumbers);
                    LottoNumbers.stopProgram(WAIT_TIME_3);
                    break;
                case 4:
                    fileMethods.printHistory();
                    LottoNumbers.stopProgram(WAIT_TIME_3);
                    break;
                case 5:
                    fileMethods.deleteHistory();
                    LottoNumbers.stopProgram(WAIT_TIME_3);
                    break;
                case 6:
                    RealLotto.realLottoMenu();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Wybrano niewłaściwą opcję");
                    LottoNumbers.stopProgram(WAIT_TIME_3);
            }
        } while (!exit);
    }
}