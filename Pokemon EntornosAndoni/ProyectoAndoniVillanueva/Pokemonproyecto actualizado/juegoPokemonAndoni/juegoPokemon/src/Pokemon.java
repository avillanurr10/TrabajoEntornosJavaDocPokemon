/**
 * @author Andoni Villanueva
 * esta clase nos crea a los pokemons Rival y el nuestro y nos añade los tipos, rangos y estadisticas
 */

public class Pokemon {
 // Atributos
    private String nombre;
    private String tipo;
    private int nivel;
    private int aguante;

    // Constructor para jugador
    public Pokemon(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = 1; //(nivel inicial es 1)
        this.actualizarStats();
    }

    // Constructor para rivales (nivel especificado)
    public Pokemon(String nombre, String tipo, int nivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.actualizarStats();
    }
// Accesores y mutadores
    public int getAguante() {
        return aguante;
    }

    public void setAguante(int aguante) {
        this.aguante = aguante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        this.actualizarStats(); // Actualizar aguante al cambiar el nivel
    }
 // metodo calcular poder, Los rangos  de los pokemons
    public int calcularPoder(Pokemon contrincante) {
        int poder = 0;
        int rangoMin = 0;
        int rangoMax = 0;

        if (this.nivel == 1) {
            rangoMin = 3;
            rangoMax = 10;
        } else if (this.nivel == 2) {
            rangoMin = 6;
            rangoMax = 20;
        } else if (this.nivel == 3) {
            rangoMin = 9;
            rangoMax = 30;
        } else if (this.nivel == 4) {
            rangoMin = 12;
            rangoMax = 40;
        }

        int poderBase = (int) (Math.random() * (rangoMax - rangoMin + 1)) + rangoMin;
       // luego todo este bloque de condiciones para asignar las ventajas y desventajas del los tipos
        // depende el tiPo del pokemon tendra ventaja sobre el rival
        int modificador = 0;
        if (this.tipo=="Agua") {
            if (contrincante.tipo=="Fuego") {
                modificador = 2 * this.nivel;
            } else if (contrincante.tipo=="Tierra") {
                modificador = -2 * contrincante.nivel;
            }
        } else if (this.tipo=="Fuego") {
            if (contrincante.tipo=="Agua") {
                modificador = -2 * contrincante.nivel;
            } else if (contrincante.tipo=="Tierra") {
                modificador = 2 * this.nivel;
            }
        } else if (this.tipo.equals("Tierra")) {
            if (contrincante.tipo=="Agua") {
                modificador = 2 * this.nivel;
            } else if (contrincante.tipo=="Fuego") {
                modificador = -2 * contrincante.nivel;
            }
        }

        int poderFinal = poderBase + modificador;

        if (poderFinal < 3) {
            poderFinal = 3;
        }

        return poderFinal;
    }
 // incrementar el nivel en 1
    public void subirNivel() {
        this.nivel++;
        this.actualizarStats(); // Actualizar aguante al subir nivel
    }
// actualiza las estadisticas del pokemon
    public void actualizarStats() {

        this.aguante = (int) Math.round(this.nivel * 2.5);
    }
  // disminuye el aguante del pokemon por cada combate
    public void disminuirAguante() {
        this.aguante--;
    }
  // metodo to String que luego lo llamamos en la interfaz con saltos de linea para que no salga todo en horizontal
    public String toString() {
        return "Nombre: " + this.nombre +
                "\n tipo: " + this.tipo +
                "\n nivel: " + this.nivel +
                "\n aguante: " + this.aguante;
    }
}
