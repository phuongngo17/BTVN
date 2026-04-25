package org.example.bai1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nội dung không được để trống")
    @Column(name = "content")
    private String content;

    @FutureOrPresent(message = "Ngày phải từ hiện tại trở đi")
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "status")
    private String status;
    @Column(name = "priority")
    private String priority;

}
