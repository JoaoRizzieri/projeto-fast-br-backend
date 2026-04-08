package com.fast.br.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogo")
@CrossOrigin(origins = "*")
public class MaterialCatalogoController {

    // Simulação de catálogo de materiais (em produção, viria do banco)
    private final List<Map<String, Object>> catalogo = Arrays.asList(
        createMaterial("Fio 2.5mm", "Elétrico", 2.50),
        createMaterial("Fio 4.0mm", "Elétrico", 3.50),
        createMaterial("Fio 6.0mm", "Elétrico", 5.00),
        createMaterial("Disjuntor 20A", "Elétrico", 15.00),
        createMaterial("Disjuntor 30A", "Elétrico", 18.00),
        createMaterial("Tomada 10A", "Elétrico", 8.00),
        createMaterial("Interruptor", "Elétrico", 7.00),
        createMaterial("Condulete", "Elétrico", 12.00),
        createMaterial("Bucha 3/4", "Elétrico", 0.50),
        createMaterial("Braçadeira", "Elétrico", 1.00),
        createMaterial("Rolamento 6204", "Mecânico", 25.00),
        createMaterial("Rolamento 6205", "Mecânico", 28.00),
        createMaterial("Retentor", "Mecânico", 8.00),
        createMaterial("Óleo lubrificante", "Mecânico", 15.00),
        createMaterial("Graxa", "Mecânico", 12.00),
        createMaterial("Parafuso M8", "Geral", 0.50),
        createMaterial("Porca M8", "Geral", 0.30),
        createMaterial("Arruela M8", "Geral", 0.20),
        createMaterial("Fita isolante", "Elétrico", 3.00),
        createMaterial("Fita veda rosca", "Geral", 5.00)
    );

    private static Map<String, Object> createMaterial(String nome, String categoria, Double valor) {
        Map<String, Object> material = new HashMap<>();
        material.put("idMaterial", nome.hashCode());
        material.put("nomeMaterial", nome);
        material.put("categoria", categoria);
        material.put("valorUnitario", valor);
        return material;
    }

    @GetMapping
    public List<Map<String, Object>> listarMateriais(
            @RequestParam(required = false) String categoria) {
        
        if (categoria == null || categoria.isEmpty()) {
            return catalogo;
        }
        
        return catalogo.stream()
                .filter(m -> m.get("categoria").toString().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }
}
