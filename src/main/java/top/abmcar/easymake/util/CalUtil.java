package top.abmcar.easymake.util;

public class CalUtil {
    public static String getCalAns(String oriString, Integer nowLevel) {
        oriString = oriString.replace("<nowLevel>", nowLevel.toString());
        oriString = oriString + '#';
        StringBuilder nowNum = new StringBuilder();
        int ans = 0;
        char nowOpt = '+';
        for (int i = 0; i < oriString.length(); i++) {
            if (oriString.charAt(i) >= '0' && oriString.charAt(i) <= '9')
                nowNum.append(oriString.charAt(i));
            else {
                if (nowOpt == '+')
                    ans = ans + Integer.parseInt(nowNum.toString());
                else if (nowOpt == '-')
                    ans = ans - Integer.parseInt(nowNum.toString());
                else if (nowOpt == '*')
                    ans = ans * Integer.parseInt(nowNum.toString());
                else if (nowOpt == '/')
                    ans = ans / (Integer.parseInt(nowNum.toString()) == 0 ? 1 : Integer.parseInt(nowNum.toString()));
                else if (nowOpt == '^')
                    ans = (int) Math.pow(ans, Integer.parseInt(nowNum.toString()));
                nowOpt = oriString.charAt(i);
            }
        }
        return Integer.toString(ans);
    }
}
