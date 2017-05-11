public class Solution {
    private static final double epsilon = 0.000001;
    private static double from;
    private static double to;
    private static double aprior;

    public static void main(String[] args) {
        double xcur;
        double xnext = calculateApproximateX();
        int iterations = 0;
        System.out.println("Выполнение условий теоремы: " + checkTheoremConditions(xnext));
        System.out.println("Начальное приближение: " + xnext);
        do {
            xcur = xnext;
            xnext = calculateFi(xcur);
            iterations++;
        } while (Math.abs(xnext - xcur) >= epsilon);
        System.out.println("Решение: " + xnext);
        System.out.println("Невязка: " + Math.abs(calculateF(xnext)));
        System.out.println("Количество итераций: " + iterations);
        System.out.println("Априорное количество итераций: " + aprior);
    }

    private static double calculateApproximateX() {
        double left = 1;
        double right = 6.5;
        double tmp;
        while (right - left >= 10) {
            tmp = (left + right) / 2;
            if (calculateF(left) * calculateF(tmp) < 0) {
                right = tmp;
            } else {
                left = tmp;
            }
        }
        from = left;
        to = right;
        return (left + right) / 2;
    }

    private static double calculateF(double x) {
        return Math.pow(Math.E, x) + Math.log(x) - 10 * x;
    }

    private static double calculateFi(double x) {
        return Math.log(10 * x - Math.log(x));
    }

    private static double calculateFiDeriv(double x) {
        return (10 - 1 / x) / (10 * x - Math.log(x));
    }

    private static boolean checkTheoremConditions(double xApprox) {
        double delta = (to - from) / 2;
        double m = Math.abs(xApprox - calculateFi(xApprox));
        double q = Math.max(calculateFiDeriv(from), calculateFiDeriv(to));
        System.out.println("q: " + q);
        System.out.println("m: " + m);
        System.out.println("delta: " + delta);
        aprior = Math.log(epsilon * (1 - q) / m) / Math.log(q);
        return (m / (1 - q)) <= delta;
    }
}
