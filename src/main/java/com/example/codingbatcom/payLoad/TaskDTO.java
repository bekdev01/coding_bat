package com.example.codingbatcom.payLoad;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDTO {

     Integer id;

     String name;

     boolean done;

     String question;

     String solution;

     Integer categoryId;
}
