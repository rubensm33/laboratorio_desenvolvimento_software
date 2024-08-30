package com.sistema_matriculas.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "secretaria")
public class Secretaria extends Usuario {

}
