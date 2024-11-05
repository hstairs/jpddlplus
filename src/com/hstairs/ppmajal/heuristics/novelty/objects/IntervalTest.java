package com.hstairs.ppmajal.heuristics.novelty.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntervalTest {

  @Test
  public void testPaperExample() {
    Interval interval = new Interval(-1.5);
    assertEquals(0, interval.getNumIntervals());
    int index;
    index = interval.getInterval(-1.5);
    assertEquals(0, index);
    assertEquals(0, interval.getNumIntervals());
    index = interval.getInterval(2.3);
    assertEquals(1, index);
    assertEquals(1, interval.getNumIntervals());
    index = interval.getInterval(-5.3);
    assertEquals(-1, index);
    assertEquals(2, interval.getNumIntervals());
    index = interval.getInterval(0.7);
    assertEquals(1, index);
    assertEquals(2, interval.getNumIntervals());
    index = interval.getInterval(4.6);
    assertEquals(2, index);
    assertEquals(3, interval.getNumIntervals());
  }

  @Test
  public void test2() {
    Interval interval = new Interval(-1.5);
    assertEquals(0, interval.getNumIntervals());
    int index;
    index = interval.getInterval(-1.5);
    assertEquals(0, index);
    assertEquals(0, interval.getNumIntervals());
    index = interval.getInterval(2.3);
    assertEquals(1, index);
    assertEquals(1, interval.getNumIntervals());
    index = interval.getInterval(2.3);
    assertEquals(1, index);
    assertEquals(1, interval.getNumIntervals());
    index = interval.getInterval(-5.3);
    assertEquals(-1, index);
    assertEquals(2, interval.getNumIntervals());
    index = interval.getInterval(-5.3);
    assertEquals(-1, index);
    assertEquals(2, interval.getNumIntervals());
    index = interval.getInterval(0.7);
    assertEquals(1, index);
    assertEquals(2, interval.getNumIntervals());
    index = interval.getInterval(0.7);
    assertEquals(1, index);
    assertEquals(2, interval.getNumIntervals());
    index = interval.getInterval(4.6);
    assertEquals(2, index);
    assertEquals(3, interval.getNumIntervals());
    index = interval.getInterval(4.6);
    assertEquals(2, index);
    assertEquals(3, interval.getNumIntervals());
  }

  @Test
  public void test3() {
    Interval interval = new Interval(0);
    assertEquals(0, interval.getNumIntervals());
    int index;
    for (int i = 1; i < 100; i++) {
      index = interval.getInterval(i);
      assertEquals(i, index);
      assertEquals(2*(i-1)+1, interval.getNumIntervals());
      index = interval.getInterval(-i);
      assertEquals(-i, index);
      assertEquals(2*i, interval.getNumIntervals());

      index = interval.getInterval(-i);
      assertEquals(-i, index);
      assertEquals(2*i, interval.getNumIntervals());
      index = interval.getInterval(i);
      assertEquals(i, index);
      assertEquals(2*i, interval.getNumIntervals());
    }
    for (int i = 1; i < 100; i++) {
      index = interval.getInterval(i);
      assertEquals(i, index);

      index = interval.getInterval(-i);
      assertEquals(-i, index);

      assertEquals(198, interval.getNumIntervals());
    }
  }
}
