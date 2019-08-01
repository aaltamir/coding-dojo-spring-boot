package com.assignment.spring.weather;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "weather")
@Getter
@Setter
@Builder
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    private Double temperature;
}
