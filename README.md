This is Spring Boot Application that serves as Library Management System. It has 3 user models: admin, librarian and student. Each type of users are allowed to/can perform certain actions.

Student can request, reserve, return books, access their history & active book reservations, manage their fines, send message to librarians and so on.

Librarians can issue or return book, manage all records of students. 

Admins are only alllowed to manipulate over students' and librarians data.

There are several points to note about the application:

  1. Once students requests a book, system checks several things: whether student already has the requested book, reached a max number of reservations, has 
  fee from previous records or not. For all particular cases, system responds a particular message. If this book is not available, system stores student's 
  request in the database, and once the book is available later, it automatically reserves a book to this student and notifies them through email. 
  
  2. If student does not meet deadline to return book, system automatically adjusts fine to this student & notifies them through email.
 
There are other features available too, which boost the productivity of librarians keeping them away from managing data on tons of paper.

