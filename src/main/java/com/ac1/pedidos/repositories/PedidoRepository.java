package com.ac1.pedidos.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.ac1.pedidos.models.Pedido;

import org.springframework.stereotype.Component;

@Component
public class PedidoRepository {

    @PostConstruct
    private void init() {
        pedidos = new ArrayList<Pedido>();

        Pedido pedido = new Pedido();
        pedido.setCliente("teste");
        pedido.setCodigo(1);
        pedido.setDataDoPedido("asd");
        pedido.setDescricao("asdasdasdasd");
        pedido.setValor(100.00);
        pedidos.add(pedido);
    }
    
    private long lastCodigo = 0;

    private List<Pedido> pedidos;

    public List<Pedido> getAllPedidos() {
        return pedidos;
    }

    public Pedido getPedidoByCodigo(long codigo) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo() == codigo) {
                return pedido;
            }
        }
        return null;
    }

    public Pedido save(Pedido pedido) {
        pedido.setCodigo(lastCodigo++);
        pedidos.add(pedido);
        return pedido;
    }

    public void remove(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public Pedido update(Pedido pedido) {
        Pedido attPedido = getPedidoByCodigo(pedido.getCodigo());

        if (attPedido != null) {
            attPedido.setCliente(pedido.getCliente());
            attPedido.setDataDoPedido(pedido.getDataDoPedido());
            attPedido.setDescricao(pedido.getDescricao());
            attPedido.setValor(pedido.getValor());
        }

        return attPedido;
    }
}
