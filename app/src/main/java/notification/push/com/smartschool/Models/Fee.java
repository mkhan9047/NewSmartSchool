package notification.push.com.smartschool.Models;

/**
 * Created by Mujahid on 7/5/2018.
 */

public class Fee {

  private int year;
  private int jan;
  private int feb;
  private int mar;
  private int apr;
  private int may;
  private int jun;
  private int jul;
  private int aug;
  private int sep;
  private int oct;
  private int nov;
  private int dec;

    public Fee(int year, int jan, int feb, int mar, int apr, int may, int jun, int jul, int aug, int sep, int oct, int nov, int dec) {
        this.year = year;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
    }

    public int getYear() {
        return year;
    }

    public int getJan() {
        return jan;
    }

    public int getFeb() {
        return feb;
    }

    public int getMar() {
        return mar;
    }

    public int getArp() {
        return apr;
    }

    public int getMay() {
        return may;
    }

    public int getJun() {
        return jun;
    }

    public int getJul() {
        return jul;
    }

    public int getAug() {
        return aug;
    }

    public int getSep() {
        return sep;
    }

    public int getOct() {
        return oct;
    }

    public int getNov() {
        return nov;
    }

    public int getDec() {
        return dec;
    }
}
