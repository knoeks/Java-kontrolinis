import lt.itakademija.exam.IntegerGenerator;
import lt.itakademija.exam.NumberFilter;

import java.util.ArrayList;
import java.util.Iterator;

public class FilteredGenerator implements IntegerGenerator {
  private IntegerGenerator generator;
  private NumberFilter filter;
  ArrayList<Integer> numbers = new ArrayList<>();


  public FilteredGenerator(IntegerGenerator generator, NumberFilter filter) {
    this.generator = generator;
    this.filter = filter;

  }


  @Override
  public Integer getNext() {
    Integer num = generator.getNext();
    while (num != null) {
      if (filter.accept(num)) {
        return num;
      }
      num = generator.getNext();
    }
    return null;
  }
}
