package said.steffen.Del3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Encode {
  public static void main(String[] args) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(args[0]);
    BitInputStream bitInputStream = new BitInputStream(fileInputStream);
    System.out.println(bitInputStream.readBit());
  }



}
