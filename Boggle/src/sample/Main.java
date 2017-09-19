package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
  ArrayList words = new ArrayList();
  public static void main(String [] args)
  {
    Scanner sc = new Scanner(System.in);
    System.out.println("What word are you checking the dictionary for?");
    String playword = sc.next();
    ArrayList words = new ArrayList();
    String filename = "Dictonary.txt";
    String line = null;
    try
    {
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null)
      {
        words.add(line);
      }
      bufferedReader.close();
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Unable to open file " + filename + " ");
    }
    catch (IOException ex)
    {
      System.out.println("Error reading file " + filename + " ");
    }

    if(words.contains(playword))
    {
      System.out.println("The word '" + playword + "' does exsist in the dictonary");

    }
    else
    {
      System.out.println("The word '" + playword + "' is not a word in the dictonary");
    }
  }
}
