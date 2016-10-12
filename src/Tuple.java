class Tuple<S, A, T, I, F> {
  private S first;
  private A second;
  private T third;
  private I fourth;
  private F fifth;

  Tuple(S first, A second, T third, I fourth, F fifth) {
    this.first = first;
    this.second = second;
    this.third = third;
    this.fourth = fourth;
    this.fifth = fifth;
  }

  A getSecond() {
    return second;
  }

  T getThird() {
    return third;
  }

  I getFourth() {
    return fourth;
  }

  F getFifth() {
    return fifth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tuple<?, ?, ?, ?, ?> tuple = (Tuple<?, ?, ?, ?, ?>) o;

    if (first != null ? !first.equals(tuple.first) : tuple.first != null) return false;
    if (second != null ? !second.equals(tuple.second) : tuple.second != null) return false;
    if (third != null ? !third.equals(tuple.third) : tuple.third != null) return false;
    if (fourth != null ? !fourth.equals(tuple.fourth) : tuple.fourth != null) return false;
    return fifth != null ? fifth.equals(tuple.fifth) : tuple.fifth == null;

  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + (second != null ? second.hashCode() : 0);
    result = 31 * result + (third != null ? third.hashCode() : 0);
    result = 31 * result + (fourth != null ? fourth.hashCode() : 0);
    result = 31 * result + (fifth != null ? fifth.hashCode() : 0);
    return result;
  }
}
