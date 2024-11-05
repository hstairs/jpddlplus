package com.hstairs.ppmajal.heuristics.novelty.objects;

import java.util.ArrayList;
import java.util.List;

// for boundary width heuristics
public class Interval {
  final double center;
  List<Double> above;
  List<Double> under;

  public Interval(double center) {
    this.center = center;
    above = new ArrayList<>();
    under = new ArrayList<>();
  }

  // use binary search on one of the two lists of indices
  public int getInterval(double value) {
    if (value > center) {
      if (above.isEmpty() || value > above.get(above.size() - 1)) {
        // new interval
        above.add(value);
        return above.size();
      }

      int left = 0;
      int right = above.size() - 1;

      int index = 0;

      while (left <= right) {
        index = left + (right - left) / 2;

        double loBoundary = index > 0 ? above.get(index - 1) : center;
        double hiBoundary = above.get(index);

        if (loBoundary < value && value <= hiBoundary) {
          break;
        } else if (value <= loBoundary) {
          right = index - 1; // Search in the left half
        } else {
          assert (value > hiBoundary);
          left = index + 1; // Search in the right half
        }
      }

      return 1 + index;
    } else if (value < center) {
      if (under.isEmpty() || value < under.get(under.size() - 1)) {
        // new interval
        under.add(value);
        return -under.size();
      }

      int left = 0;
      int right = under.size() - 1;

      int index = 0;

      while (left <= right) {
        index = left + (right - left) / 2;

        double hiBoundary = index > 0 ? under.get(index - 1) : center;
        double loBoundary = under.get(index);

        if (loBoundary <= value && value < hiBoundary) {
          break;
        } else if (value < loBoundary) {
          left = index + 1; // Search in the right half
        } else {
          assert (value >= hiBoundary);
          right = index - 1; // Search in the left half
        }
      }


      return -1 - index;
    } else {  // value == center
      return 0;
    }
  }

  public int getNumIntervals() {
    return above.size() + under.size();
  }
}
