package com.company;

public class Domino
{
    private static int s1;
    private static int s2;

    public Domino(int s1, int s2)
    {
        this.s1 = s1;
        this.s2 = s2;
    }

    public static int getS1()
    {
        return s1;
    }

    public void setS1(int ns1)
    {
        this.s1 = ns1;
    }

    public static int getS2()
    {
        return s2;
    }

    public void setS2(int ns2)
    {
        this.s2 = ns2;
    }

}
