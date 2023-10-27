public class RegresionLinealMultiple {


    //listas datos x1, x2, x3 e y
    public double x1[]={};
    public double x2[]={};
    public double x3[]={};
    public double y[]={};

//tamaño
    public int n;

    public RegresionLinealMultiple(double[] x1,double[] x2, double[] y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
        this.n= x1.length;
    }
    public RegresionLinealMultiple(double[] x1,double[] x2,double[] x3, double[] y) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y = y;
        this.n= x1.length;
    }


    //tres variables


    public void RegresionLinMult3() {

        //definir sumatorias


        double sumX1 = 0;
        double sumX2 = 0;

        double sumX1X2 = 0;

        double sumXX1 = 0;
        double sumXX2 = 0;

        double sumY = 0;
        double sumX1Y = 0;
        double sumX2Y = 0;


        //sumatorias


        for (int i = 0; i < n; i++) {
            sumX1 += x1[i];
            sumX2 += x2[i];

            sumX1X2 += x1[i] * x2[i];

            sumXX1 += x1[i] * x1[i];
            sumXX2 += x2[i] * x2[i];

            sumY += y[i];
            sumX1Y += x1[i] * y[i];
            sumX2Y += x2[i] * y[i];

        }


        //matriz3x3

        double matriz3 [][]={{ n, sumX1, sumX2},{ sumX1, sumXX1, sumX1X2},{ sumX2, sumX1X2, sumXX2}};


        //matriz respuesta

        double matrizY3 []={sumY,sumX1Y,sumX2Y};


        // valores de la regresión lineal múltiple

        int n = matriz3.length;
        int m = matriz3[0].length;


        // matriz transpuesta X

        double[][] XTranspuesta = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                XTranspuesta[j][i] = matriz3[i][j];
            }
        }

        // calcula X^T * X

        double[][] XTranspuestaX = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += XTranspuesta[i][k] * matriz3[k][j];
                }
                XTranspuestaX[i][j] = sum;
            }
        }


        // calcula X^T * Y

        double[] XTranspuestaY = new double[m];
        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += XTranspuesta[i][j] * matrizY3[j];
            }
            XTranspuestaY[i] = sum;
        }


        // resolver la formula ((X^T *X)^ -1) * (X^T *Y)

        double[] coeficientes = resolverSistemaEcuaciones(XTranspuestaX, XTranspuestaY);



        // Separación
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();



        //enseñar la ecuacion

        System.out.println("tras tener los datos y hacer operaciones tenemos que: ");
        System.out.println("el valor de b0 es: "+ coeficientes[0]);
        System.out.println("el valor de b1 es: "+ coeficientes[1]);
        System.out.println("el valor de b2 es: "+ coeficientes[2]);
        System.out.println("la ecuacion es: y=" + coeficientes[0] +" + "+ coeficientes[1] + "x1 + " + coeficientes[2] + "x2 ");



        // Separación

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();



        // Simulación

        double[] nuevoPuntoX1 = {36, 74, 85, 90, 21};
        double[] nuevoPuntoX2 = {11, 22, 33, 44, 55};
        double nuevoPuntoY;

        System.out.println("Realicemos una simulación con 5 puntos:");
        for (int j = 0; j < nuevoPuntoX1.length; j++) {

            nuevoPuntoY = (coeficientes[1] * nuevoPuntoX1[j]) + (coeficientes[2] * nuevoPuntoX2[j]) + coeficientes[0];
            System.out.println("Usaremos los puntos, x1: " + nuevoPuntoX1[j] + ", x2: " + nuevoPuntoX2[j] + " para hacer una simulación.");
            System.out.println("La fórmula quedaría como y = " + coeficientes[0] + " + " + coeficientes[1] + " * " + nuevoPuntoX1[j] + " + " + coeficientes[2] + " * " + nuevoPuntoX2[j]);
            System.out.println("Esto nos da como resultado que Y = " + nuevoPuntoY);


        }

    }


    //cuatro variables

    public void RegresionLinMult4(){

        //definir sumatorias

        double sumX1 = 0;
        double sumX2 = 0;
        double sumX3 = 0;

        double sumX1X2 = 0;
        double sumX1X3 = 0;
        double sumX2X3 = 0;

        double sumXX1 = 0;
        double sumXX2 = 0;
        double sumXX3 = 0;

        double sumY = 0;
        double sumX1Y = 0;
        double sumX2Y = 0;
        double sumX3Y = 0;


        //sumatorias

        for (int i = 0; i < n; i++) {
            sumX1 += x1[i];
            sumX2 += x2[i];
            sumX3 += x3[i];

            sumX1X2 += x1[i] * x2[i];
            sumX1X3 += x1[i] * x3[i];
            sumX2X3 += x2[i] * x3[i];

            sumXX1 += x1[i] * x1[i];
            sumXX2 += x2[i] * x2[i];
            sumXX3 += x3[i] * x3[i];

            sumY += y[i];
            sumX1Y += x1[i] * y[i];
            sumX2Y += x2[i] * y[i];
            sumX3Y += x3[i] * y[i];

        }

        //matriz4x4

        double matriz4 [][]={{ n, sumX1, sumX2, sumX3},{ sumX1, sumXX1, sumX1X2, sumX1X3},{ sumX2, sumX1X2, sumXX2, sumX2X3},{sumX3, sumX1X3, sumX2X3, sumXX3}};


        //matriz respuesta

        double matrizY4 []={sumY,sumX1Y,sumX2Y,sumX3Y};



        //valores de regresion lineal multiple

        int n = matriz4.length;
        int m = matriz4[0].length;


        // matriz X transpuesta

        double[][] XTranspuesta = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                XTranspuesta[j][i] = matriz4[i][j];
            }
        }


        // calcula X^T * X

        double[][] XTranspuestaX = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += XTranspuesta[i][k] * matriz4[k][j];
                }
                XTranspuestaX[i][j] = sum;
            }
        }


        // calcula X^T * Y

        double[] XTranspuestaY = new double[m];
        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += XTranspuesta[i][j] * matrizY4[j];
            }
            XTranspuestaY[i] = sum;
        }


        // resolver la formula ((X^T *X)^ -1) * (X^T *Y)

        double[] coeficientes = resolverSistemaEcuaciones(XTranspuestaX, XTranspuestaY);


        //separacion

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();


        //enseñar la ecuacion

        System.out.println("tras tener los datos y hacer operaciones tenemos que: ");
        System.out.println("el valor de b0 es: "+ coeficientes[0]);
        System.out.println("el valor de b1 es: "+ coeficientes[1]);
        System.out.println("el valor de b2 es: "+ coeficientes[2]);
        System.out.println("el valor de b3 es: "+ coeficientes[3]);
        System.out.println("la ecuacion es: y=" + coeficientes[0] +" + "+ coeficientes[1] + "x1 + " + coeficientes[2] + "x2 + " + coeficientes[3] + "x3");



        //separacion

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();


        //simulacion


        double nuevoPuntoX1 []= { 36, 74, 85, 90, 21};
        double nuevoPuntoX2 []= { 11, 22, 33, 44, 55};
        double nuevoPuntoX3 []= { 13, 23, 33, 43, 53};
        double nuevoPuntoY;


        System.out.println("hagamos una simulacioncon 5 puntos");
        for (int j = 0; j < nuevoPuntoX1.length; j++) {

            nuevoPuntoY = (coeficientes[1] * nuevoPuntoX1[j]) + (coeficientes[2] * nuevoPuntoX2[j]) + (coeficientes[3] * nuevoPuntoX3[j]) + coeficientes[0];


            System.out.println("usaremos los puntos, x1: " + nuevoPuntoX1[j] +", x2: "+nuevoPuntoX2[j]+ ", x3: "+nuevoPuntoX3[j] +" para hacer una simulacion");
            System.out.println("la formula quedaria como y = " + coeficientes[3] + " * " + nuevoPuntoX3[j] + " + " + coeficientes[2] + " * "+ nuevoPuntoX2[j] + " + "+ coeficientes[1] + " * " + nuevoPuntoX1[j] + " + " + coeficientes[0]);
            System.out.println("esto nos da como resultado, que Y = " + nuevoPuntoY);
            System.out.println();


        }
    }


    //resolver ecuacion((X^T *X)^ -1) * (X^T *Y)

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
