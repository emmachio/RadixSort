import java.util.*;
import java.io.*;

public class Radix{

  public static int nth(int n, int col) {
    n = n / ((int)Math.pow(10,col));
    return Math.abs(n % 10);
  }

  public static int length(int n) {
    int length;
    if(n == 0) {
      length = 1;
    }
    else {
      n = Math.abs(n);
      length = (int)Math.log10(n) + 1;
    }
    return length;
  }

  public static void merge(SortableLinkedList original, SortableLinkedList[] buckets) {
    for(int i = 0; i < buckets.length; i++) {
      original.extend(buckets[i]);
    }
  }

  public static String radixSortSimple(SortableLinkedList data) {
    int maxlength = 0;
    SortableLinkedList[] buckets = new SortableLinkedList[10];
    for (int i = 0; i < 10; i++) {
      buckets[i] = new SortableLinkedList();
    }
    for(int i = 0; i < data.size(); i++) {
      maxlength = Math.max(maxlength, Math.abs(data.get(0)));
      data.add(data.remove(0));
    }
    maxlength = length(maxlength);
    for(int i = 0; i < maxlength; i++) {
      for(int j = 0; j < data.size(); j++) {
        int value = nth(data.get(0), i);
        buckets[value].add(data.get(0));
        data.remove(0);
      }
      merge(data, buckets);
    }
    return data.toString();
  }

  // public static void radixSort(SortableLinkedList data) {
  //   int maxlength = 1;
  //   SortableLinkedList[] buckets = new SortableLinkedList[20];
  //   for (int i = 0; i < 20; i++) {
  //     buckets[i] = new SortableLinkedList();
  //   }
  //   for(int i = 0; i < maxlength; i++) {
  //     for(int j = 0; j < data.size(); j++) {
  //       if(length(data.get(j)) > maxlength) {
  //         maxlength = length(data.get(j));
  //       }
  //       if(data.get(j) < 0) {
  //         int value = (nth(data.get(j), i));
  //         buckets[9 - value].add(data.get(j));
  //       }
  //       else{
  //         int value2 = nth(data.get(j), i) + 10;
  //         buckets[value2].add(data.get(j));
  //       }
  //     }
  //     data = new SortableLinkedList();
  //     merge(data, buckets);
  //   }
  // }

  public static void main(String[] args) {
    SortableLinkedList test = new SortableLinkedList();
    test.add(2); test.add(300); test.add(112);  test.add(100);
    System.out.println((radixSortSimple(test)));
    System.out.println(nth(112, 0));
  }
}
