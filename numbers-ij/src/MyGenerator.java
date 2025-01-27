import lt.itakademija.exam.IntegerGenerator;

public class MyGenerator implements IntegerGenerator {

  private int fromNum;
  private int toNum;

  public MyGenerator(int fromNum, int toNum) {
    this.fromNum = fromNum;
    this.toNum = toNum;
  }

  @Override
  public Integer getNext() {
    while (toNum >= fromNum) {
      return fromNum++;
    }
    return null;
  }
}

