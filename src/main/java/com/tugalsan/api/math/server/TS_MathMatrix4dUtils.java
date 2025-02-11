package com.tugalsan.api.math.server;

public class TS_MathMatrix4dUtils {
//    public static void main(String[] s) {
//        double[][] x = createEmptyMatrix();
//        double[][] y = createEmptyMatrix();
//        double[][] z = createEmptyMatrix();
//
//        x[3][0] = 1;
//        x[3][1] = 2;
//        x[3][2] = 3;
//        y[3][0] = 1;
//        y[3][1] = 2;
//        y[3][2] = 3;
//
//        printMatrix("x", x);
//        printMatrix("y", y);
//        multiply(x, y, z);
//        printMatrix("x*y", z);
//        multiplySimplfied(x, y, z);
//        printMatrix("x*y", z);
//    }

    public static void printMatrix(String label, double[][] M) {
        System.out.println(label + ":");
        for (var i = 0; i < 4; i++) {
            for (var j = 0; j < 4; j++) {
                System.out.print(M[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static double[][] createEmptyMatrix() {
        return new double[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
    }

    public static void setMatrixAsEmpty(double[][] M) {
        for (var i = 0; i < 4; i++) {
            for (var j = 0; j < 4; j++) {
                M[i][j] = 0;
            }
        }
    }

    public static void setMatrixAsIdentity(double[][] M) {
        setMatrixAsEmpty(M);
        M[0][0] = M[1][1] = M[2][2] = 1f;
    }

    public static void multiply(double[][] M1, double[][] M2, double[][] MO) {
        MO[0][0] = M1[0][0] * M2[0][0] + M1[0][1] * M2[1][0] + M1[0][2] * M2[2][0] + M1[0][3] * M2[3][0];
        MO[0][1] = M1[0][0] * M2[0][1] + M1[0][1] * M2[1][1] + M1[0][2] * M2[2][1] + M1[0][3] * M2[3][1];
        MO[0][2] = M1[0][0] * M2[0][2] + M1[0][1] * M2[1][2] + M1[0][2] * M2[2][2] + M1[0][3] * M2[3][2];
        MO[0][3] = M1[0][0] * M2[0][3] + M1[0][1] * M2[1][3] + M1[0][2] * M2[2][3] + M1[0][3] * M2[3][3];
        MO[1][0] = M1[1][0] * M2[0][0] + M1[1][1] * M2[1][0] + M1[1][2] * M2[2][0] + M1[1][3] * M2[3][0];
        MO[1][1] = M1[1][0] * M2[0][1] + M1[1][1] * M2[1][1] + M1[1][2] * M2[2][1] + M1[1][3] * M2[3][1];
        MO[1][2] = M1[1][0] * M2[0][2] + M1[1][1] * M2[1][2] + M1[1][2] * M2[2][2] + M1[1][3] * M2[3][2];
        MO[1][3] = M1[1][0] * M2[0][3] + M1[1][1] * M2[1][3] + M1[1][2] * M2[2][3] + M1[1][3] * M2[3][3];
        MO[2][0] = M1[2][0] * M2[0][0] + M1[2][1] * M2[1][0] + M1[2][2] * M2[2][0] + M1[2][3] * M2[3][0];
        MO[2][1] = M1[2][0] * M2[0][1] + M1[2][1] * M2[1][1] + M1[2][2] * M2[2][1] + M1[2][3] * M2[3][1];
        MO[2][2] = M1[2][0] * M2[0][2] + M1[2][1] * M2[1][2] + M1[2][2] * M2[2][2] + M1[2][3] * M2[3][2];
        MO[2][3] = M1[2][0] * M2[0][3] + M1[2][1] * M2[1][3] + M1[2][2] * M2[2][3] + M1[2][3] * M2[3][3];
        MO[3][0] = M1[3][0] * M2[0][0] + M1[3][1] * M2[1][0] + M1[3][2] * M2[2][0] + M1[3][3] * M2[3][0];
        MO[3][1] = M1[3][0] * M2[0][1] + M1[3][1] * M2[1][1] + M1[3][2] * M2[2][1] + M1[3][3] * M2[3][1];
        MO[3][2] = M1[3][0] * M2[0][2] + M1[3][1] * M2[1][2] + M1[3][2] * M2[2][2] + M1[3][3] * M2[3][2];
        MO[3][3] = M1[3][0] * M2[0][3] + M1[3][1] * M2[1][3] + M1[3][2] * M2[2][3] + M1[3][3] * M2[3][3];
    }

    public static void multiplySimplfied(double[][] M1, double[][] M2, double[][] MO) {
        setMatrixAsEmpty(MO);
        for (var k = 0; k < 4; k++) {
            for (var j = 0; j < 4; j++) {
                for (var i = 0; i < 4; i++) {
                    MO[k][j] += M1[k][i] * M2[i][j];
                }
            }
        }
    }

    public static double determinant(double[][] M) {
        return 0f
                + M[0][0] * M[1][1] * M[2][2] * M[3][3] + M[0][0] * M[1][2] * M[2][3] * M[3][1] + M[0][0] * M[1][3] * M[2][1] * M[3][2]
                + M[0][1] * M[1][0] * M[2][3] * M[3][2] + M[0][1] * M[1][2] * M[2][0] * M[3][3] + M[0][1] * M[1][3] * M[2][2] * M[3][0]
                + M[0][2] * M[1][0] * M[2][1] * M[3][3] + M[0][2] * M[1][1] * M[2][3] * M[3][0] + M[0][2] * M[1][3] * M[2][0] * M[3][1]
                + M[0][3] * M[1][0] * M[2][2] * M[3][1] + M[0][3] * M[1][1] * M[2][0] * M[3][2] + M[0][3] * M[1][2] * M[2][1] * M[3][0]
                - M[0][0] * M[1][1] * M[2][3] * M[3][2] - M[0][0] * M[1][2] * M[2][1] * M[3][3] - M[0][0] * M[1][3] * M[2][2] * M[3][1]
                - M[0][1] * M[1][0] * M[2][2] * M[3][3] - M[0][1] * M[1][2] * M[2][3] * M[3][0] - M[0][1] * M[1][3] * M[2][0] * M[3][2]
                - M[0][2] * M[1][0] * M[2][3] * M[3][1] - M[0][2] * M[1][1] * M[2][0] * M[3][3] - M[0][2] * M[1][3] * M[2][1] * M[3][0]
                - M[0][3] * M[1][0] * M[2][1] * M[3][2] - M[0][3] * M[1][1] * M[2][2] * M[3][0] - M[0][3] * M[1][2] * M[2][0] * M[3][1];
    }

    //TODO inverse study: http://www.cg.info.hiroshima-cu.ac.jp/~miyazaki/knowledge/teche23.html
    public static void inverse(double[][] M, double[][] MI) {
        MI[0][0] = M[1][1] * M[2][2] * M[3][3] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[0][1] = M[0][1] * M[2][3] * M[3][2] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[0][2] = M[0][1] * M[1][2] * M[3][3] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[0][3] = M[0][1] * M[1][3] * M[2][2] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[1][0] = M[1][0] * M[2][3] * M[3][2] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[1][1] = M[0][0] * M[2][2] * M[3][3] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[1][2] = M[0][0] * M[1][3] * M[3][2] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[1][3] = M[0][0] * M[1][2] * M[2][3] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[2][0] = M[1][0] * M[2][1] * M[3][3] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[2][1] = M[0][0] * M[2][3] * M[3][1] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[2][2] = M[0][0] * M[1][1] * M[3][3] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[2][3] = M[0][0] * M[1][3] * M[2][1] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[3][0] = M[1][0] * M[2][2] * M[3][1] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[3][1] = M[0][0] * M[2][1] * M[3][2] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[3][2] = M[0][0] * M[1][2] * M[3][1] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];
        MI[3][3] = M[0][0] * M[1][1] * M[2][2] + M[1][2] * M[2][3] * M[3][1] + M[1][3] * M[2][1] * M[3][2] - M[1][1] * M[2][3] * M[3][2] - M[1][2] * M[2][1] * M[3][3] - M[1][3] * M[2][2] * M[3][1];

        var det = determinant(M);
        for (var i = 0; i < 4; i++) {
            for (var j = 0; j < 4; j++) {
                MI[i][j] /= det;
            }
        }
    }
}
