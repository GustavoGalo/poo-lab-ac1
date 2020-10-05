package com.ac1.pedidos.controllers;

import java.net.URI;
import java.util.List;

import com.ac1.pedidos.models.Pedido;
import com.ac1.pedidos.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {
    
    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("/pedidos")
    public List<Pedido> getPedidos() {
        return pedidoRepository.getAllPedidos();
    }

    @GetMapping("/pedido/{codigo}")
    public ResponseEntity<Pedido> getPedido(@PathVariable long codigo) {
        Pedido pedido = pedidoRepository.getPedidoByCodigo(codigo);

        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pedido")
    public ResponseEntity<Void> salvar(@RequestBody Pedido pedido) {
        pedido = pedidoRepository.save(pedido);
        URI uri = URI.create("http://localhost:8080/pedido/" + pedido.getCodigo());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("pedido/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable long codigo){   
        Pedido pedido = pedidoRepository.getPedidoByCodigo(codigo);
        
        if(pedido != null){
            pedidoRepository.remove(pedido);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("pedido/{codigo}")
    public ResponseEntity<Pedido> atualizar(@PathVariable long codigo, @RequestBody Pedido pedido){   
        
        if(pedidoRepository.getPedidoByCodigo(codigo) != null){
            pedido.setCodigo(codigo);
            pedido = pedidoRepository.update(pedido);
            return ResponseEntity.ok(pedido);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
