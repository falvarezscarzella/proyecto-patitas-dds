package Servicios.Hogares;

import Mascota.DatosMascotaPerdida;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObtenedorServicioHogares {
  static List<Hogar> listaHogares = new ArrayList<>();
  static ServicioHogares servicio = ServicioHogares.getInstance();
  //List<ListaDeHogares> ListaDeListaDeHogares = new ArrayList<>();

  public static void setAllHogares() throws IOException {

    for(int i=1;i<=4;i++) {
      ListaDeHogares listaVariable = servicio.listadoDeHogares(i);
      //System.out.println(listaVariable.getTotal().toString() + listaVariable.getOffset().toString() + listaVariable.getListaHogares().get(0).getNombre());
      agregarHogares(servicio.listadoDeHogares(i).getListaHogares());
    }
  }

  public static void cambiarServicio(ServicioHogares nuevoServ) {
    servicio = nuevoServ;
  }

  public static void agregarHogares(List<Hogar> listaHogares) {
    ObtenedorServicioHogares.listaHogares.addAll(listaHogares);
  }

  public static List<Hogar> hogaresQueCumplan(double radio, DatosMascotaPerdida datosMascota) throws IOException {
    setAllHogares();
    return listaHogares.stream()
        .filter(h -> h.estaDentroRadio(radio, datosMascota))
        .filter(h -> h.tieneDisponibilidad())
        .filter(h -> h.aceptaAnimal(datosMascota))
        .filter(h -> h.aceptaSegunPatio(datosMascota))
        .filter(h -> h.cumpleCaracteristicaPuntual(datosMascota))
        .collect(Collectors.toList());
  }
}