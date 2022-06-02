package utils;

import java.util.*;


/**
 * @author JIADE
 * @description ReadKeyboard
 * @date 2021/6/5 16:43
 */
public class ReadKeyboard {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * @description 读取键盘，如果用户键入’0’-’7’中的任意字符，则方法返回。返回值为用户键入字符。
     * @return
     * @author JIADE
     * @date 2021/6/5 16:48
     */
	public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4'&& c != '5'&& c != '6'&& c != '7'&& c != '8'&& c != '0') {
                System.out.print("选择错误，请重新输入：");
            } else break;
        }
        return c;
    }

    /**
     * @description 提示并等待，直到用户按回车键后返回。
     * @return
     * @author JIADE
     * @date 2021/6/5 16:48
     */
    public static void readReturn() {
        System.out.print("按回车键继续...\n");
        readKeyBoard(100, true);
    }

    /**
     * @description 从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值。
     * @return
     * @author JIADE
     * @date 2021/6/5 16:47
     */
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }

    /**
     * @description  从键盘读取‘Y’或’N’，并将其作为方法的返回值。
     * @return
     * @author JIADE
     * @date 2021/6/5 16:47
     */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }

    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }

        return line;
    }
}

