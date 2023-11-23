package domain;

import java.time.LocalDateTime;

public class Pedido {
    private Cliente cliente;
    private Coche coche;
    private LocalDateTime fechaCompra;
    private double precioTotal;

    public Pedido(Cliente cliente, Coche coche, LocalDateTime fechaCompra, double precioTotal) {
        this.cliente = cliente;
        this.coche = coche;
        this.fechaCompra = fechaCompra;
        this.precioTotal = precioTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente=" + cliente +
                ", coche=" + coche +
                ", fechaCompra=" + fechaCompra +
                ", precioTotal=" + precioTotal +
                '}';
    }
}