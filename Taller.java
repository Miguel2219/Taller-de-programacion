import java.util.Scanner;
public class Taller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hola");
        //se inicializa el programa, pidiendo los datos de los estudiantes del grupo A
        System.out.println(" GESTION DE NOTAS DEL GRUPO A ");

                //Defino Constantes
                final double LIMIT_APPROBATION = 3.0; 
                final int MIN_STUDENTS=5;
                final int NUMBER_NOTES=3;

                //Pido entrada al usuario
                System.out.println("Ingrese el numero de estudiantes, deben ser almenos 5 estudiantes");
                int numberStudents=scanner.nextInt();
                scanner.nextLine();

                //Se evalua si el numero de estudiantes ingresados, sea mayor a 4 
                while (numberStudents<MIN_STUDENTS) {
                    System.out.println("Debe Ingresar al menos 5 estudiantes");
                    numberStudents=scanner.nextInt();
                    scanner.nextLine(); // se utiliza para consumir el salto de línea pendiente. y asi captura la siguiente entrada de manera correcta.
                }

                //Defino Arrays,Matrices; con la longitud ingresada por el usuario, igual al numero de estudiantes
                String[] nameStudents=new String[numberStudents];
                int[] idstudents = new int[numberStudents];
                double[] promedio = new double[numberStudents];
                double[][] grades= new double[numberStudents][NUMBER_NOTES];
                

                //Inicio un bucle con el numero de estudiantes que se requiere ingresar (almenos 5 estudiantes), y lo implemento en arrays donde i es el numero de estudiantes
                for (int i = 0; i < numberStudents; i++) {
                    System.out.print("Nombre completo del estudiante " + (i+1) + ": ");
                    nameStudents[i]=scanner.nextLine();
                    //dentro del for, luego de pedir por consola el nombre, pedimos la identificacion del estudiante.
                    System.out.print("Identificacion: ");
                    int id_student=scanner.nextInt();

                    //Se va a crear una variable booleana dentro del for, para que se reinicia en cada vuelta.
                    boolean bandera=true;

                    //Se realiza un ciclo while para evaluar dos condiciones, una que la identificacion sea de 4 digitos, y la otra es que no pueden haber dos estudiantes con la misma identifiacion
                    while (bandera==true){
                        //Primero, para evaluar si una identificacion esta repetida, deben haber por lo menos dos identificaciones registradas.
                        //Por eso, el indice i de estudiante, debe ser mayor a 0. Eso asegura que ya hay un estudiante ingresado, y se puede evaluar
                        //si hay otra identificacion igual.
                        if(i>0){
                            //Este ciclo, va a pasar por cada elemento de la lista idstudents, para evaluar si la identificacion actual es igual a alguna de las anteriores
                            for (int h = 0; h < idstudents.length; h++) {
                                while (id_student==idstudents[h]) {
                                    System.out.println("Ya hay un estudiante con la misma identificacion");
                                    System.out.println("Ingrese nuevamente la identificacion");
                                    id_student=scanner.nextInt();
                                    h=0;
                                }
                                
                            }
                        }
                        
                        //Segundo, se evalua que, si la identificacion esta fuera del rango entre 1000 y 9999, es invalida
                        //ya que, no seria de 4 digitos
                        if(id_student<1000 || id_student>9999){
                            System.out.println("identificacion incorrecta (Debe ser de 4 digitos)");
                            System.out.print("Ingrese nuevamente la identificacion: ");
                            id_student=scanner.nextInt();
                        //Por ultimo, una vez que ya se haya evaluado las dos condiciones anteriores, y la identificacion este correcta, se cambia de valor logico
                        // de la bandera para cerra el ciclo while.
                        }else{
                            bandera=false;
                        }

                    }
                    
                    //Se agrega la identificacion ingresada a la lista idstudents.
                    idstudents[i]=id_student;
                    //Inicializamos la variable suma en 0; para luego irle sumando las notas que se vayan registando con la matriz
                    float suma=0;

                    // Pedimos por comsola que se digite las tres notas del estudiante 
                    System.out.println(" Digite las tres notas: ");

                    //se inicializa otro for para realizar la matriz que va a guardar las notas de cada uno de los estudiantes
                      for (int j = 0; j < NUMBER_NOTES; j++){
                        System.out.print("nota " + (j+1) + ": ");
                        grades[i][j] = scanner.nextDouble();

                        //Se creó un while para verificar que las notas que se esten ingresando esten en un rango entre 0.0 y 5.0
                        while (grades[i][j] < 0 || grades[i][j] > 5){
                            System.out.println("nota incorrecta");
                            System.out.println("ingrese nuevamente la nota entre el rango de 0.0 y 5.0");

                            //Hasta que no se ingrese la nota en el rango establecido, no pasara el while
                            grades[i][j] = scanner.nextDouble();
                        }
                        //se realiza la suma de las notas con la matriz, de la posicion del estudiante con sus tres notas
                        suma += grades[i][j];
                      }
                      //Hacemos un salto de linea, para pedir caracteres por consola, ya que se venia pidiento doubles
                      scanner.nextLine();

                      //Se guarda en el array de promedio, el promedio con la posicion de cada estudiante
                      promedio[i] = suma/NUMBER_NOTES;

                }

                //Se genera un reporte general
                String status = "";
                System.out.println("***REPORTE GENERAL***");
                System.out.println("----------------------");

                //Inicializamos for para recorrer todos los arrays, y generar el reporte de los estudiantes 
                for (int i = 0; i < numberStudents; i++) {
                    //esto se conoce como ternario, y cumple una funcion parecida al if, que segun lo que de la validacion, arroja uno u otro resultado
                    //se puede utilizar cualquiera de las dos formas
                    status=(promedio[i] >= LIMIT_APPROBATION)? "APROBADO" : "REPROBADO";

                    //se hace la impresion de los arrays en posicion i para generar el reporte
                    // se utiliza el  "%.3f" para limitar el número de decimales a tres, y asi concretar el promedio
                    System.out.println("Estudiante " +nameStudents[i]+ " (ID): " +idstudents[i] + " - PROMEDIO: " + String.format("%.3f", promedio[i]) + " fue: " +status );
                        

                }
                //se cierra el scanner
            scanner.close();
    }
    
}