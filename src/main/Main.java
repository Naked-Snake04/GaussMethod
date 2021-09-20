package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /**
         * Ввод данных
         */

        Scanner s = new Scanner(System.in);
        System.out.print("Введите n: ");
        int n = s.nextInt();
        System.out.print("Введите m: ");
        int m = s.nextInt();

        double[][]A = new double [100][100];
        double[] b = new double[100];
        for (int i = 0; i<n; i++){
            A[i] = new double[100];
            System.out.print("Введите коэффициенты: ");
            for (int j = 0; j<m; j++){
                A[i][j] = s.nextDouble();
            }
            System.out.print("Введите коэффициент b: ");
            b[i] = s.nextDouble();
        }
        System.out.println("\nВаша матрица:");
        for (int i = 0; i<n; i++){
            for (int j = 0; j < m; j++) {
                System.out.print(A[i][j] + " ");
            }

            System.out.println("|" + b[i]);
        }
        /**
         * Метод Гаусса - Прямой ход
         */

        int N = n;
        for (int p =0; p<N; p++){
            int max = p;
            for (int i = p +1; i<N; i++){
                if (Math.abs(A[i][p])>Math.abs(A[max][p])){
                    max = i;
                }
            }
            double[]temp = A[p];
            A[p] = A[max];
            A[max] = temp;

            double t = b[p]; b[p] = b[max]; b[max] =t;

            if (Math.abs(A[p][p])<= 1e-10){
                System.out.println("Решения не существует");
                return;
            }

            for (int i = p+1; i<N; i++){
                double a = A[i][p] / A[p][p];
                b[i] -= a *b[p];
                for (int j =p; j<N; j++){
                    A[i][j] = -a*A[p][j];
                }
            }
        }

        /**
         * Обратный ход
         */
        double[]x = new double[N];
        for (int i = N-1; i>=0; i--){
            double sum = 0.0;
            for (int j = i+1; j<N; j++){
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum)/ A[i][i];
        }

        /**
         * Вывод результатов
         */
        if (n<m){
            System.out.println("Бесконечное множество решений");
        }
        else {
            System.out.println("\nРешение системы:");
            for (int i = 0; i<N;i++){
                System.out.println(x[i] + " ");
            }
        }
    }
}
