package java.text;

public class Format {
  public String format(Object obj) {
    return obj == null ? "" : obj.toString();
  }

  public Object parseObject(String source) {
    return source;
  }
}
