import lt.itakademija.exam.Exercises;
import lt.itakademija.exam.IntegerGenerator;
import lt.itakademija.exam.NumberFilter;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExerciseSolution implements Exercises {
  @Override
  public Integer findSmallest(List<Integer> list) {
    return list.stream().min(Comparator.comparing(Integer::intValue)).orElse(null);
  }

  @Override
  public Integer findLargest(List<Integer> list) {
    return list.stream().max(Comparator.comparing(Integer::intValue)).orElse(null);

  }

  @Override
  public boolean isEqual(Object o, Object o1) {
    return o.equals(o1);
  }

  @Override
  public boolean isSameObject(Object o, Object o1) {
    return o == o1;
  }

  @Override
  public List<Integer> consume(Iterator<Integer> iterator) {
    List<Integer> numbers = new ArrayList<>();
    while (iterator.hasNext()) {
      numbers.add(iterator.next());
    }
    return numbers;
  }

  @Override
  public int computeSumOfNumbers(int i) {
    return IntStream.rangeClosed(0, i).sum();
  }

  @Override
  public int computeSumOfNumbers(int i, NumberFilter numberFilter) {
    return IntStream.rangeClosed(0, i).filter(numberFilter::accept).sum();
  }

  @Override
  public List<Integer> computeNumbersUpTo(int i) {
    return IntStream.range(1, i).boxed().toList();

  }

  @Override
  public Map<Integer, Integer> countOccurrences(List<Integer> list) {
    return list.stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.summingInt(x -> 1)));
  }

  @Override
  public IntegerGenerator createIntegerGenerator(int i, int i1) {
    NUm
  }

  @Override
  public IntegerGenerator createFilteredIntegerGenerator(IntegerGenerator integerGenerator, NumberFilter numberFilter) {
    return null;
  }
}
