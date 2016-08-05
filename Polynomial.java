import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Polynomial {
    private double[] coef;
    private int deg;

    public Polynomial(int a, int b) {
        coef = new double[b + 1];
        coef[b] = a;
        deg = degree();
    }

    public int degree() {
        int d = 0;
        for (int i = 0; i < coef.length; i++)
            if (coef[i] != 0)
                d = i;
        return d;
    }

    public Polynomial addition(Polynomial b) {
        Polynomial a = this;
        Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++)
            c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++)
            c.coef[i] += b.coef[i];
        c.deg = c.degree();
        return c;
    }

    public Polynomial subtraction(Polynomial b) {
        Polynomial a = this;
        Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++)
            c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++)
            c.coef[i] -= b.coef[i];
        c.deg = c.degree();
        return c;
    }

    public Polynomial multiplication(Polynomial b) {
        Polynomial a = this;
        Polynomial c = new Polynomial(0, a.deg + b.deg);
        for (int i = 0; i <= a.deg; i++)
            for (int j = 0; j <= b.deg; j++)
                c.coef[i + j] += (a.coef[i] * b.coef[j]);
        c.deg = c.degree();
        return c;
    }

    public String toString() {
        if (deg == 0)
            return "" + coef[0];
        if (deg == 1)
            return coef[1] + "x + " + coef[0];
        String polyString = coef[deg] + "x^" + deg;
        for (int i = deg - 1; i >= 0; i--) {
            if (coef[i] == 0)
                continue;
            else if (coef[i] > 0)
                polyString = polyString + " + " + (coef[i]);
            else if (coef[i] < 0)
                polyString = polyString + " - " + (-coef[i]);
            if (i == 1)
                polyString = polyString + "x";
            else if (i > 1)
                polyString = polyString + "x^" + i;
        }
        return polyString;
    }

    public String toLatex() {
        String header = "\\documentclass{article}" + "\\usepackage[utf8]{inputenc}" + "\\begin{document}" + "\n ";
        String body = this.toString() + "\n";
        String footer = "\\end{document}";

        printToFile(header+body+footer,".tex");

        return header + body + footer;
    }

    public String toHtml() {
        String header = "<!doctype html>" + "\n<html lang=\"en\">" + "\n<body>" + "\n<p>";
        String body = this.toString() + "\n";
        String footer = "</p>" + "\n</body>" + "\n</html>";

        printToFile(header+body+footer,".html");

        return header + body + footer;
    }

    public void printToFile(String content, String ext){
        try {
            File file = new File("C:/Users/vikarthikeyan/workspace/out"+ext);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Polynomial zero = new Polynomial(0, 0);

        Polynomial p1 = new Polynomial(4, 3);
        Polynomial p2 = new Polynomial(3, 1);
        Polynomial p3 = new Polynomial(1, 2);
        Polynomial p4 = new Polynomial(2, 1);
        Polynomial p = p1.addition(p2).addition(p3).addition(p4);

        Polynomial q1 = new Polynomial(3, 2);
        Polynomial q2 = new Polynomial(5, 0);
        Polynomial q = q1.addition(q2);

        Polynomial r = p.addition(q);
        Polynomial s = p.multiplication(q);

        // System.out.println("zero(x) = " + zero);
         System.out.println("p(x) in latex = \n" + p.toLatex());
         System.out.println("p(x) in html = \n" + p.toHtml());
        // System.out.println("q(x) = " + q);
        // System.out.println("p(x) + q(x) = " + r);
        // System.out.println("p(x) * q(x) = " + s);
        // System.out.println("0 - p(x) = " + zero.subtraction(p));

    }

}
