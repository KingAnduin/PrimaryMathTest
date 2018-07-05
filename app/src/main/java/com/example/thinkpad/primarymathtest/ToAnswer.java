package com.example.thinkpad.primarymathtest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Thinkpad on 2018/7/5.
 */

public class ToAnswer {
    String problemstring;
    public ToAnswer(String string) {
        this.problemstring = string;
        getAnswer();
    }

    public String getAnswer() {

        List<Object> list = trans(problemstring);

        Stack<CalculatorNum> result = new Stack<CalculatorNum>();
        CalculatorNum res = f(list, result);

        int isPlus = 1;
        int isInteger = 0;
        String answer = "";
        if(res.getnumerator()<0 || res.getdenominator()<0) {isPlus = 0;}
        if (res.getdenominator() == 1) {isInteger = 1;}

        if (isPlus == 1) {
            if(isInteger == 0) {
                answer = Math.abs(res.getnumerator())+"/"+Math.abs(res.getdenominator());
            }
            else{
                answer = Math.abs(res.getnumerator())+"";
            }
        }
        else{
            if(isInteger == 0){
                answer = "-"+Math.abs(res.getnumerator())+"/"+Math.abs(res.getdenominator());
            }
            else{
                answer = "-"+Math.abs(res.getnumerator());
            }
        }
        return answer;

    }

    private static CalculatorNum f(List<Object> list, Stack<CalculatorNum> result) {
        // TODO Auto-generated method stub
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String m = it.next().toString();
            if (m.equals("+") || m.equals("-") || m.equals("*") || m.equals("/")) {
                CalculatorNum b = result.pop();

                CalculatorNum a = result.pop();
                CalculatorNum v = g(a, b, m);
                result.push(v);
            } else {
                result.push(new CalculatorNum(Integer.parseInt(m), 1));
            }
        }
        return (result.pop());
    }

    private static CalculatorNum g(CalculatorNum a, CalculatorNum b, String m) {
        // TODO Auto-generated method stub

        CalculatorNum v = null;
        switch (m) {
            case "+":
                v = a.add(b);
                break;
            case "-":
                v = a.reduce(b);
                break;
            case "*":
                v = a.muti(b);
                break;
            case "/":
                v = a.divide(b);
                break;
        }
        return v;

    }

    private static List<Object> trans(String s) {
        // TODO Auto-generated method stub
        Stack<Character> op = new Stack<Character>();

        ArrayList<Object> list = new ArrayList<Object>();
        Pattern P = Pattern.compile("[0-9]+(\\.[0-9]+)?"); // 正则表达式来处理带小数点的数字
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                String s1 = s.substring(i);
                Matcher m = P.matcher(s1);
                if (m.find()) { // 取匹配到的第一个数字
                    s1 = m.group();

                    list.add(s1);
                }
                i = i + s1.length();
                continue;
            } else if (c == '(') {
                op.push(c);
            } else if (c == ')') {
                char p = op.pop();
                while (p != '(') {
                    list.add(p);
                    p = op.pop();
                }
            } else if (c == '+' || c == '-') {
                while (!op.isEmpty()
                        && (op.peek() == '+' || op.peek() == '-' || op.peek() == '*' || op.peek() == '/')) {

                    list.add(op.pop());
                }
                op.push(c);
            } else if (c == '*' || c == '/') {
                while (!op.isEmpty() && (op.peek() == '*' || op.peek() == '/')) {
                    list.add(op.pop());
                }
                op.push(c);
            }
            i++;
        }

        while (!op.isEmpty()) {
            list.add(op.pop());
        }
        return list;
    }

}
