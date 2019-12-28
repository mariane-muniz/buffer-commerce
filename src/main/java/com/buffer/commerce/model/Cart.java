package com.buffer.commerce.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "carts")
public class Cart extends AbstractAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1000
    )
    private Long id;
}
