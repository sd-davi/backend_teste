package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posicao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_posicao")
    private Integer id;
    
    @Column(name="valor")
    private Double valor;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date data;

    @ManyToOne
    @JoinColumn(name="investimento_id")
    Investimento investimento;
}
