public class RegresionPolinomial {

    //datos  x , y

    public double[] datosx;
    public double[] datosy;


    //tamaño

    public int n;


    //constructor

    public RegresionPolinomial(double[] datosx, double[] datosy) {
        this.datosx = datosx;
        this.datosy = datosy;
        this.n = datosx.length;
    }



    //metodo

    public void regrePolinomialCubo(){

        //datos de sumatoria

        double sumX = 0.0;
        double sumX2 = 0;
        double sumX3 = 0;
        double sumX4 = 0;
        double sumX5 = 0;
        double sumX6 = 0;

        double sumY = 0;
        double sumXY = 0;
        double sumX2Y = 0;
        double sumX3Y = 0;


        //calcular sumatorias

        for (int i = 0; i < n; i++) {

            sumX += datosx[i];
            sumX2 += datosx[i] * datosx[i];
            sumX3 += datosx[i] * datosx[i] * datosx[i];
            sumX4 += datosx[i] * datosx[i] * datosx[i] * datosx[i];
            sumX5 += datosx[i] * datosx[i] * datosx[i] * datosx[i] * datosx[i];
            sumX6 += datosx[i] * datosx[i] * datosx[i] * datosx[i] * datosx[i] * datosx[i];

            sumY += datosy[i];
            sumXY += datosx[i] * datosy[i];
            sumX2Y += datosx[i] * datosx[i] * datosy[i];
            sumX3Y += datosx[i] * datosx[i] * datosx[i] * datosy[i];
        }


        //matrices

        double matrizX [][] = {{n, sumX,sumX2,sumX3}, {sumX,sumX2,sumX3,sumX4}, {sumX2,sumX3,sumX4,sumX5}, {sumX3,sumX4,sumX5,sumX6}};
        double matrizY [] = {sumY, sumXY, sumX2Y,sumX3Y};



        // valores de la regresión

        int n = matrizX.length;
        int m = matrizX[0].length;



        // matriz transpuesta X

        double[][] XTranspuesta = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                XTranspuesta[j][i] = matrizX[i][j];
            }
        }



        // calcula X^T * X

        double[][] XTranspuestaX = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += XTranspuesta[i][k] * matrizX[k][j];
                }
                XTranspuestaX[i][j] = sum;
            }
        }


        // calcula X^T * Y

        double[] XTranspuestaY = new double[m];
        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += XTranspuesta[i][j] * matrizY[j];
            }
            XTranspuestaY[i] = sum;
        }


        double[] coeficientes = resolverSistemaEcuaciones(XTranspuestaX, XTranspuestaY);

        //separacion

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();



        //enseñar valores de b3, b2, b1 y b0

        System.out.println("tras tener los datos y hacer operaciones tenemos que: ");
        System.out.println("el valor de B3 es : "+ coeficientes[3]);
        System.out.println("el valor de B2 es : "+ coeficientes[2]);
        System.out.println("el valor de B1 es: "+ coeficientes[1]);
        System.out.println("el valor de B0 es: "+ coeficientes[0]);
        System.out.println("la ecuacion es: y=" + coeficientes[3]+"xxx +" + coeficientes[2] +"xx +"+ coeficientes[1] + "x +" + coeficientes[0] );



        //separacion

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();



        //simulacion


        double nuevoPuntoX [] = { 108, 74, 85, 90, 21 };
        double nuevoPuntoY;

        System.out.println("hagamos una simulacioncon 5 puntos");
        for (int j = 0; j < nuevoPuntoX.length; j++) {

            nuevoPuntoY = (coeficientes[3] * (nuevoPuntoX[j]*nuevoPuntoX[j]*nuevoPuntoX[j]))+(coeficientes[2] * (nuevoPuntoX[j]*nuevoPuntoX[j])) + (coeficientes[1] * nuevoPuntoX[j]) + coeficientes[0];


            System.out.println("usaremos este punto: " + nuevoPuntoX[j]+ " para hacer una simulacion");
            System.out.println("la formula quedaria como y=" + coeficientes[3]+"xxx +" + coeficientes[2] +"xx +"+ coeficientes[1] + "x +" + coeficientes[0] );
            System.out.println("esto nos da como resultado, que Y = " + nuevoPuntoY);
            System.out.println();

        }


    }



    //resolver ecuacion ((X^T *X)^ -1) * (X^T *Y)

    public static double[] resolverSistemaEcuaciones(double[][] transpuestaX, double[] transpuestaY) {
        int n = transpuestaX.length;
        double[] x = new double[n];

        // gauss
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double factor = transpuestaX[j][i] / transpuestaX[i][i];
                transpuestaY[j] -= factor * transpuestaY[i];
                for (int k = i; k < n; k++) {
                    transpuestaX[j][k] -= factor * transpuestaX[i][k];
                }
            }
        }

        // operacion final

        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += transpuestaX[i][j] * x[j];
            }
            x[i] = (transpuestaY[i] - sum) / transpuestaX[i][i];
        }

        return x;
    }


}
