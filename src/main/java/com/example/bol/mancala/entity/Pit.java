package com.example.bol.mancala.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "pit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private int number;
    private int stones;

    public Pit(int number, int stones) {
        this.number = number;
        this.stones = stones;
    }

    public static Pit createHousePit(int number) {
        return new Pit(number, 0);
    }
}
