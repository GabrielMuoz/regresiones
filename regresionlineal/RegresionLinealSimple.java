public class RegresionLinealSimple {
    //datos x , y
    public double[] datosx;
    public double[] datosy;

    //tamaño
    public int n;

    public RegresionLinealSimple(double[] datosx, double[] datosy) {
        this.datosx = datosx;
        this.datosy = datosy;
        this.n = datosx.length;
  }


    //calcular sumas y cuadrados

    public void regresionLinSim() {

        //datos de regresion lineal simple


       double sumXY = 0;
       double sumX = 0;
       double sumY = 0;
       double sumX2 = 0;

       double m; //pendiente B1
       double promX;
       double promY;
       double b;  //punto de interseccion B0

        //sumatorias
       for (int i = 0; i < n; i++) {
            sumXY += datosx[i] * datosy[i];
            sumX += datosx[i];
            sumY += datosy[i];
            sumX2 += datosx[i] * datosx[i];
       }

       //pruebas


        //System.out.println(datosx.length);
        //System.out.println(datosy.length);
        //System.out.println(sumX);
        //System.out.println(sumX2);
        //System.out.println(sumY);
        //System.out.println(sumXY);



       //formula


       m = (sumXY-((sumX * sumY) / n)) / (sumX2-((sumX * sumX) / n));
       promX = sumX / n;
       promY = sumY / n;
       b = promY - (m * promX);



       //separacion

       System.out.println();
       System.out.println("-------------------------------------");
       System.out.println();



       //enseñar pendiente e interceccion

        System.out.println("tras tener los datos y hacer operaciones tenemos que: ");
       System.out.println("la pendiente es (B1): "+ m);
       System.out.println("el punto de intersección en el eje Y (B0): "+ b);
       System.out.println("la ecuacion es: y=" + m +"x +"+ b );



       //separacion

       System.out.println();
       System.out.println("-------------------------------------");
       System.out.println();



         //simulacion


        double nuevoPuntoX []= {36, 74, 85, 90, 21};
        double nuevoPuntoY;


        System.out.println("hagamos una simulacioncon 5 puntos");
        for (int j = 0; j < nuevoPuntoX.length; j++) {

            nuevoPuntoY = (m * nuevoPuntoX[j]) + b;


            System.out.println("usaremos este punto: " + nuevoPuntoX[j] + " para hacer una simulacion");
            System.out.println("la formula quedaria como y = " + m + " * " + nuevoPuntoX[j] + " + " + b);
            System.out.println("esto nos da como resultado, que Y = " + nuevoPuntoY);


        }
    }



}
