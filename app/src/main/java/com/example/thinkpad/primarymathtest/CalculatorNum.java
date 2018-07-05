package com.example.thinkpad.primarymathtest;

/**
 * Created by Thinkpad on 2018/7/5.
 */

/**
 *
 * @author lenovo
 * numerator 分子
 * denominator 分母
 */
public class CalculatorNum {
    private int numerator;
    private int denominator;
    public CalculatorNum(int numerator,int denominator)
    {
        this.numerator=numerator;
        this.denominator=denominator;
    }
    public int getnumerator()
    {
        return numerator;
    }
    public int getdenominator()
    {
        return denominator;
    }
    public int gcd(int numerator,int denominator)

    {
        int a=numerator;
        int b=denominator;
        int s;
        if(a<b)
        {
            int temp=b;
            b=a;
            a=temp;
        }
        while(b!=0)
        {
            s=a%b;
            a=b;
            b=s;
        }
        if(b==0)
            return a;
        else
            return gcd(a,b);
    }
    public void reduction()
    {
        int a=gcd(numerator, denominator);
        numerator/=a;
        denominator/=a;
    }
    public CalculatorNum add(CalculatorNum num)
    {
        CalculatorNum result=new CalculatorNum(1, 1);
        result.numerator=(numerator*num.getdenominator())+(num.getnumerator()*denominator);
        result.denominator=denominator*(num.getdenominator());
        result.reduction();
        return result;
    }
    public CalculatorNum reduce(CalculatorNum num)
    {
        CalculatorNum result=new CalculatorNum(1, 1);
        result.numerator=numerator*num.getdenominator()-num.getnumerator()*denominator;
        result.denominator=denominator*num.getdenominator();
        result.reduction();
        return result;
    }
    public CalculatorNum muti(CalculatorNum num)
    {
        CalculatorNum result=new CalculatorNum(1, 1);
        result.numerator=numerator*num.getnumerator();
        result.denominator=denominator*num.getdenominator();
        result.reduction();
        return result;
    }
    public CalculatorNum divide(CalculatorNum num)
    {
        CalculatorNum result=new CalculatorNum(1, 1);
        result.numerator=numerator*num.getdenominator();
        result.denominator=denominator*num.getnumerator();
        result.reduction();
        return result;
    }

}

